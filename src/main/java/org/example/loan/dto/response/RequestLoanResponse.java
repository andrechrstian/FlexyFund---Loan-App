package org.example.loan.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.loan.entity.Customer;
import org.example.loan.entity.InstalmentType;
import org.example.loan.entity.LoanTransactionDetail;
import org.example.loan.entity.LoanType;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestLoanResponse {
    private String id;
    private String loanType;
    private String instalmentType;
    private String customer;
    private double nominal;
    private Long approvedAt;
    private String approvedBy;
    private String approvalStatus;
    private List<LoanTransactionDetailResponse> transactionDetail;
    private Long createAt;
    private Long updatedAt;


}
