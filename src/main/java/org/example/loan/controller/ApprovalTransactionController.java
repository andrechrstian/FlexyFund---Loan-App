package org.example.loan.controller;

import lombok.RequiredArgsConstructor;
import org.example.loan.constant.APIURL;
import org.example.loan.dto.request.ApproveTransactionRequest;
import org.example.loan.dto.response.ApproveTransactionResponse;
import org.example.loan.dto.response.RequestLoanResponse;
import org.example.loan.entity.LoanTransaction;
import org.example.loan.service.ApproveTransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(APIURL.APPROVAL_LOAN_API)
@RequiredArgsConstructor
public class ApprovalTransactionController {

    private final ApproveTransactionService approveTransactionService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STAFF')")
    public ResponseEntity<ApproveTransactionResponse> createApproval (@RequestBody ApproveTransactionRequest request){
        ApproveTransactionResponse createdApproval = approveTransactionService.createApproveTransaction(request);
        return ResponseEntity.ok(createdApproval);
    }
}
