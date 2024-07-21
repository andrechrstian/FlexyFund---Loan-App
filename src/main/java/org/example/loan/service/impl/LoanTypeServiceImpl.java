package org.example.loan.service.impl;

import lombok.AllArgsConstructor;
import org.example.loan.dto.request.LoanTypeRequest;
import org.example.loan.dto.response.LoanTypeResponse;
import org.example.loan.entity.LoanType;
import org.example.loan.repository.LoanTypeRepository;
import org.example.loan.service.LoanTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class LoanTypeServiceImpl implements LoanTypeService {

    private final LoanTypeRepository loanTypeRepository;

    @Override
    public LoanTypeResponse createLoanType(LoanTypeRequest request) {
        LoanType loanType = loanTypeRepository.saveAndFlush(
                LoanType.builder()
                        .type(request.getType())
                        .maxLoan(request.getMaxLoan())
                        .build()
        );

        return convertToLoanTypeResponse(loanType);
    }

    @Override
    public List<LoanTypeResponse> getAllLoanType() {
        return loanTypeRepository.findAll().stream()
                .map(this::convertToLoanTypeResponse)
                .toList();
    }

    @Override
    public LoanTypeResponse getLoanTypeById(String id) {
        LoanType loanType = findByIdOrThrowNotFound(id);
        return convertToLoanTypeResponse(loanType);
    }

    @Override
    public LoanTypeResponse updateLoanTypeId(LoanTypeRequest request) {
        LoanType existingLoanType = findByIdOrThrowNotFound(request.getId());
        existingLoanType.setType(request.getType());
        existingLoanType.setMaxLoan(request.getMaxLoan());

        return convertToLoanTypeResponse(loanTypeRepository.saveAndFlush(existingLoanType));
    }

    @Override
    public void deleteLoanType(String id) {
        findByIdOrThrowNotFound(id);
        loanTypeRepository.deleteById(id);
    }

    private LoanType findByIdOrThrowNotFound(String id) {
        return loanTypeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
    }

    private LoanTypeResponse convertToLoanTypeResponse(LoanType loanType){
        return LoanTypeResponse.builder()
                .id(loanType.getId())
                .type(loanType.getType())
                .maxLoan(loanType.getMaxLoan())
                .build();
    }
}
