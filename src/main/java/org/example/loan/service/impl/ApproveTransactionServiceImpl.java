package org.example.loan.service.impl;

import lombok.AllArgsConstructor;
import org.example.loan.dto.request.ApproveTransactionRequest;
import org.example.loan.dto.response.ApproveTransactionResponse;
import org.example.loan.dto.response.LoanTransactionDetailResponse;
import org.example.loan.dto.response.RequestLoanResponse;
import org.example.loan.entity.Enum.ApprovalStatus;
import org.example.loan.entity.Enum.LoanStatus;
import org.example.loan.entity.LoanTransaction;
import org.example.loan.entity.LoanTransactionDetail;
import org.example.loan.repository.ApproveTransactionRepository;
import org.example.loan.repository.LoanTransactionDetailRepository;
import org.example.loan.repository.RequestLoanRepository;
import org.example.loan.service.ApproveTransactionService;
import org.example.loan.service.LoanTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ApproveTransactionServiceImpl implements ApproveTransactionService {

    private ApproveTransactionRepository approveTransactionRepository;
    private LoanTypeService loanTypeService;
    private RequestLoanRepository requestLoanRepository;
    private LoanTransactionDetailRepository loanTransactionDetailRepository;

    @Override
    public ApproveTransactionResponse createApproveTransaction(ApproveTransactionRequest request) {

        LoanTransaction loanTransaction = findByIdOrThrowNotFound(request.getLoanTransactionId());

        Double nominalWithInterest = loanTransaction.getNominal() + (loanTransaction.getNominal() * request.getInterestRates() / 100);

        LoanTransactionDetail loanTransactionDetail = LoanTransactionDetail.builder()
                .transactionDate(System.currentTimeMillis())
                .nominal(nominalWithInterest)
                .loanStatus(LoanStatus.valueOf(request.getLoanStatus()))
                .createdAt(System.currentTimeMillis())
                .updatedAt(System.currentTimeMillis())
                .loanTransaction(loanTransaction)
                .build();

        loanTransaction.setApprovalStatus(ApprovalStatus.valueOf(request.getApprovalStatus()));
        loanTransaction.setApprovedBy(request.getApprovedBy());
        loanTransaction.setApprovedAt(System.currentTimeMillis());
        loanTransaction.setUpdatedAt(System.currentTimeMillis());
        loanTransaction.getLoanTransactionDetails().add(loanTransactionDetail);

        loanTransactionDetailRepository.save(loanTransactionDetail);
        loanTransaction = approveTransactionRepository.save(loanTransaction);

        return ApproveTransactionResponse.builder()
                .message("Pinjaman Anda Telah Disetujui")
                .data(convertToApproveTrxResponse(loanTransaction))
                .build();
    }

    private LoanTransaction findByIdOrThrowNotFound(String id) {
        return requestLoanRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
    }

    public RequestLoanResponse convertToApproveTrxResponse (LoanTransaction request){
        List<LoanTransactionDetailResponse> details = request.getLoanTransactionDetails().stream()
                .map(detail -> LoanTransactionDetailResponse.builder()
                        .id(detail.getId())
                        .transactionDate(detail.getTransactionDate())
                        .nominal(detail.getNominal())
                        .loanStatus(String.valueOf(detail.getLoanStatus()))
                        .createdAt(detail.getCreatedAt())
                        .updatedAt(detail.getUpdatedAt())
                        .loanTransactionId(detail.getId())
                        .build())
                .collect(Collectors.toList());

        return RequestLoanResponse.builder()
                .id(request.getId())
                .loanType(request.getLoanType().getId())
                .instalmentType(request.getInstalmentType().getId())
                .customer(request.getCustomer().getId())
                .nominal(request.getNominal())
                .approvedAt(request.getApprovedAt())
                .approvalStatus(String.valueOf(request.getApprovalStatus()))
                .approvedBy(request.getApprovedBy())
                .transactionDetail(details)
                .createAt(request.getCreatedAt())
                .updatedAt(request.getUpdatedAt())
                .build();
    }
}
