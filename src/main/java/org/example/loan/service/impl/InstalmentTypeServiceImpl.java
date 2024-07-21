package org.example.loan.service.impl;

import lombok.AllArgsConstructor;
import org.example.loan.dto.request.InstalmentTypeRequest;
import org.example.loan.dto.response.InstalmentTypeResponse;
import org.example.loan.entity.InstalmentType;
import org.example.loan.repository.InstalmentTypeRepository;
import org.example.loan.service.InstalmentTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class InstalmentTypeServiceImpl implements InstalmentTypeService {
    private InstalmentTypeRepository instalmentTypeRepository;

    @Override
    public InstalmentTypeResponse createInstalmentType(InstalmentTypeRequest request) {
        InstalmentType instalmentType = instalmentTypeRepository.saveAndFlush(
                InstalmentType.builder()
                        .instalmentType(request.getInstalmentType())
                        .build()
        );

        return convertToInstalmentTypeResponse(instalmentType);
    }

    @Override
    public List<InstalmentTypeResponse> getAllInstalmentType() {
        return instalmentTypeRepository.findAll().stream()
                .map(this::convertToInstalmentTypeResponse)
                .toList();
    }

    @Override
    public InstalmentTypeResponse getInstalmentTypeById(String id) {
        InstalmentType instalmentType = findByIdOrThrowNotFound(id);
        return convertToInstalmentTypeResponse(instalmentType);
    }

    @Override
    public InstalmentTypeResponse updateInstalmentType(InstalmentTypeRequest request) {
        InstalmentType existingInstalmentType = findByIdOrThrowNotFound(request.getId());
        existingInstalmentType.setInstalmentType(request.getInstalmentType());

        InstalmentType updatedInstalmentType = instalmentTypeRepository.saveAndFlush(existingInstalmentType);

        return convertToInstalmentTypeResponse(updatedInstalmentType);
    }

    @Override
    public void deleteInstalmentType(String id) {
        findByIdOrThrowNotFound(id);
        instalmentTypeRepository.deleteById(id);
    }

    //METHOD
    private InstalmentType findByIdOrThrowNotFound(String id) {
        return instalmentTypeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
    }

    private InstalmentTypeResponse convertToInstalmentTypeResponse(InstalmentType instalmentType){
        return InstalmentTypeResponse.builder()
                .id(instalmentType.getId())
                .instalmentType(instalmentType.getInstalmentType())
                .build();
    }
}
