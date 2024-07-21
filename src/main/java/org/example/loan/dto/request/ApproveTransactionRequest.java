package org.example.loan.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.loan.entity.LoanTransaction;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApproveTransactionRequest {
    private String loanTransactionId;
    private Double interestRates;
    private String loanStatus;
    private String approvalStatus;
    private String approvedBy;

}
