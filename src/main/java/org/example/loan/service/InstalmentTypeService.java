package org.example.loan.service;

import org.example.loan.dto.request.InstalmentTypeRequest;
import org.example.loan.dto.request.LoanTypeRequest;
import org.example.loan.dto.response.InstalmentTypeResponse;
import org.example.loan.dto.response.LoanTypeResponse;

import java.util.List;

public interface InstalmentTypeService {
    InstalmentTypeResponse createInstalmentType (InstalmentTypeRequest request);
    List<InstalmentTypeResponse> getAllInstalmentType ();
    InstalmentTypeResponse getInstalmentTypeById (String id);
    InstalmentTypeResponse updateInstalmentType (InstalmentTypeRequest request);
    void deleteInstalmentType (String id);
}
