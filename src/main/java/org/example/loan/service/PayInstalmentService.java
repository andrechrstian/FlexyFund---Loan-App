package org.example.loan.service;

import org.example.loan.dto.request.PayInstalmentRequest;
import org.example.loan.dto.response.PayInstalmentResponse;

public interface PayInstalmentService {
    PayInstalmentResponse payInstalment (PayInstalmentRequest request);
}
