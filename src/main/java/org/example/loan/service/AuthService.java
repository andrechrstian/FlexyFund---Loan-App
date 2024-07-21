package org.example.loan.service;

import jakarta.transaction.Transactional;
import org.example.loan.dto.request.CustomerRequest;
import org.example.loan.dto.response.LoginResponse;
import org.example.loan.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse registerCustomer (CustomerRequest.AuthRequest<CustomerRequest> authRequest);
    RegisterResponse registerAdmin (CustomerRequest.AuthRequest<CustomerRequest> authRequest);
    RegisterResponse registerStaff (CustomerRequest.AuthRequest<CustomerRequest> authRequest);
    LoginResponse login (CustomerRequest.AuthRequest<String> request);

  }
