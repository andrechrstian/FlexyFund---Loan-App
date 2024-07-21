package org.example.loan.service;

import org.example.loan.dto.request.ApproveTransactionRequest;
import org.example.loan.dto.response.ApproveTransactionResponse;

public interface ApproveTransactionService {
    ApproveTransactionResponse createApproveTransaction (ApproveTransactionRequest request);
}
