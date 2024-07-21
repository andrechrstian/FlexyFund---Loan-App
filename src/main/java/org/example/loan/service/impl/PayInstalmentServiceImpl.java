package org.example.loan.service.impl;

import lombok.AllArgsConstructor;
import org.example.loan.dto.request.PayInstalmentRequest;
import org.example.loan.dto.response.LoanTransactionDetailResponse;
import org.example.loan.dto.response.PayInstalmentResponse;
import org.example.loan.entity.Enum.LoanStatus;
import org.example.loan.entity.LoanTransaction;
import org.example.loan.entity.LoanTransactionDetail;
import org.example.loan.repository.LoanTransactionDetailRepository;
import org.example.loan.service.PayInstalmentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class PayInstalmentServiceImpl implements PayInstalmentService {

    private LoanTransactionDetailRepository loanTransactionDetailRepository;

    @Override
    public PayInstalmentResponse payInstalment(PayInstalmentRequest request) {
        LoanTransactionDetail loanTransactionDetail = findByIdOrThrowNotFound(request.getId());
        loanTransactionDetail.setLoanStatus(LoanStatus.valueOf(request.getLoanStatus()));
        loanTransactionDetail.setUpdatedAt(System.currentTimeMillis());

        loanTransactionDetail = loanTransactionDetailRepository.save(loanTransactionDetail);
        return PayInstalmentResponse.builder()
                .message("Pinjaman Sudah dibayar")
                .data(convertToLoanTrxDetailResponse(loanTransactionDetail))
                .build();
    }

    private LoanTransactionDetail findByIdOrThrowNotFound(String id) {
        return loanTransactionDetailRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
    }

    public LoanTransactionDetailResponse convertToLoanTrxDetailResponse (LoanTransactionDetail request){
        return LoanTransactionDetailResponse.builder()
                .id(request.getId())
                .transactionDate(request.getTransactionDate())
                .nominal(request.getNominal())
                .loanStatus(String.valueOf(request.getLoanStatus()))
                .createdAt(request.getCreatedAt())
                .updatedAt(request.getUpdatedAt())
                .loanTransactionId(request.getLoanTransaction().getId())
                .build();
    }
}
