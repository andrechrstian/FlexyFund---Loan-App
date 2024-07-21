package org.example.loan.service;

import org.example.loan.dto.request.LoanTypeRequest;
import org.example.loan.dto.response.LoanTypeResponse;

import java.util.List;

public interface LoanTypeService {
    LoanTypeResponse createLoanType (LoanTypeRequest request);
    List<LoanTypeResponse> getAllLoanType ();
    LoanTypeResponse getLoanTypeById (String id);
    LoanTypeResponse updateLoanTypeId (LoanTypeRequest request);
    void deleteLoanType (String id);

}
