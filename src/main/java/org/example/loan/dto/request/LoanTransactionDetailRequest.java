package org.example.loan.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.loan.entity.Enum.LoanStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanTransactionDetailRequest {
    private String id;
    private Long transactionDate;
    private double nominal;
    private LoanStatus loanStatus;
    private Long createdAt;
    private Long updatedAt;

}
