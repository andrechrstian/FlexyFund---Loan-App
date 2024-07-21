package org.example.loan.service.impl;

import lombok.AllArgsConstructor;
import org.example.loan.dto.request.RequestLoanRequest;
import org.example.loan.dto.response.LoanTransactionDetailResponse;
import org.example.loan.dto.response.PayInstalmentResponse;
import org.example.loan.dto.response.RequestLoanResponse;
import org.example.loan.entity.LoanTransaction;
import org.example.loan.entity.LoanTransactionDetail;
import org.example.loan.entity.LoanType;
import org.example.loan.repository.RequestLoanRepository;
import org.example.loan.service.RequestLoanService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RequestLoanServiceImpl implements RequestLoanService {

    private RequestLoanRepository requestLoanRepository;

    @Override
    public RequestLoanResponse createRequestLoan(RequestLoanRequest request) {
        LoanTransaction loanTransaction = requestLoanRepository.saveAndFlush(
                LoanTransaction.builder()
                        .loanType(request.getLoanType())
                        .instalmentType(request.getInstalmentType())
                        .customer(request.getCustomer())
                        .nominal(request.getNominal())
                        .createdAt(Instant.now().toEpochMilli())
                        .build()
        );
        return convertToLoanTrxResponse(loanTransaction);
    }

    @Override
    public List<RequestLoanResponse> getAllRequestLoan() {
        return requestLoanRepository.findAll().stream()
                .map(this::convertToLoanTrxResponse)
                .toList();
    }

    @Override
    public RequestLoanResponse getRequestLoanById(String id) {
        LoanTransaction loanTransaction = findByIdOrThrowNotFound(id);
        return convertToLoanTrxResponse(loanTransaction);
    }

    private LoanTransaction findByIdOrThrowNotFound(String id) {
        return requestLoanRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
    }

    public RequestLoanResponse convertToLoanTrxResponse (LoanTransaction loanTransaction){
        List<LoanTransactionDetailResponse> details = loanTransaction.getLoanTransactionDetails().stream()
                .map(detail -> LoanTransactionDetailResponse.builder()
                        .id(detail.getId())
                        .transactionDate(detail.getTransactionDate())
                        .nominal(detail.getNominal())
                        .loanStatus(String.valueOf(detail.getLoanStatus()))
                        .createdAt(detail.getCreatedAt())
                        .updatedAt(detail.getUpdatedAt())
                        .loanTransactionId(loanTransaction.getId())
                        .build())
                .collect(Collectors.toList());

        return RequestLoanResponse.builder()
                .id(loanTransaction.getId())
                .loanType(loanTransaction.getLoanType().getId())
                .instalmentType(loanTransaction.getInstalmentType().getId())
                .customer(loanTransaction.getCustomer().getId())
                .nominal(loanTransaction.getNominal())
                .approvedAt(loanTransaction.getApprovedAt())
                .approvedBy(loanTransaction.getApprovedBy())
                .approvalStatus(String.valueOf(loanTransaction.getApprovalStatus()))
                .createAt(Instant.now().toEpochMilli())
                .transactionDetail(details)
                .updatedAt(loanTransaction.getUpdatedAt())
                .build();
    }

}
