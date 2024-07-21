package org.example.loan.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.loan.entity.LoanTransaction;
import org.example.loan.entity.LoanTransactionDetail;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApproveTransactionResponse {
    private String message;
    private RequestLoanResponse data;
}
