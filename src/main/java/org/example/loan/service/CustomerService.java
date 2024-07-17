package org.example.loan.service;


import org.example.loan.dto.request.CustomerRequest;
import org.example.loan.dto.response.CustomerResponse;

import java.util.List;

public interface CustomerService {
    CustomerResponse createCustomer (CustomerRequest request);
    List<CustomerResponse> getAllCustomer();
    CustomerResponse getCustomerById (String id);
    CustomerResponse updateCustomer (CustomerRequest request);
    void deleteCustomer (String id);

}
