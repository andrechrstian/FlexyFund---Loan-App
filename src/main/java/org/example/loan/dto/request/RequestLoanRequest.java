package org.example.loan.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.loan.entity.Customer;
import org.example.loan.entity.InstalmentType;
import org.example.loan.entity.LoanType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestLoanRequest {
    private String id;
    private LoanType loanType;
    private InstalmentType instalmentType;
    private Customer customer;
    private Double nominal;
}
