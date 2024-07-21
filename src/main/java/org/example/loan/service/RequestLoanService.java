package org.example.loan.service;

import org.example.loan.dto.request.RequestLoanRequest;
import org.example.loan.dto.response.LoanTypeResponse;
import org.example.loan.dto.response.PayInstalmentResponse;
import org.example.loan.dto.response.RequestLoanResponse;

import java.util.List;

public interface RequestLoanService {
    RequestLoanResponse createRequestLoan (RequestLoanRequest request);
    List<RequestLoanResponse> getAllRequestLoan ();
    RequestLoanResponse getRequestLoanById (String id);
}
