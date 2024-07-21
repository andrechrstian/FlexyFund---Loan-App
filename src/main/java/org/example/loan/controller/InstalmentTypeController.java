package org.example.loan.controller;

import lombok.AllArgsConstructor;
import org.example.loan.constant.APIURL;
import org.example.loan.dto.request.InstalmentTypeRequest;
import org.example.loan.dto.response.InstalmentTypeResponse;
import org.example.loan.service.InstalmentTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(APIURL.INSTALMENT_TYPE_API)
@AllArgsConstructor
public class InstalmentTypeController {
    private final InstalmentTypeService instalmentTypeService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STAFF')")
    public List<InstalmentTypeResponse> getInstalmentTypes (){
        return instalmentTypeService.getAllInstalmentType();
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STAFF')")
    public ResponseEntity<InstalmentTypeResponse> getInstalmentTypeById (@RequestParam String id) {
        InstalmentTypeResponse instalmentTypeResponse = instalmentTypeService.getInstalmentTypeById(id);
        return ResponseEntity.ok(instalmentTypeResponse);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STAFF')")
    public ResponseEntity<InstalmentTypeResponse> postInstalmentType (@RequestBody InstalmentTypeRequest request) {
        InstalmentTypeResponse response = instalmentTypeService.createInstalmentType(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STAFF')")
    public ResponseEntity<InstalmentTypeResponse> putInstalmentType (@RequestBody InstalmentTypeRequest request) {
        InstalmentTypeResponse instalmentTypeResponse = instalmentTypeService.updateInstalmentType(request);
        return ResponseEntity.ok(instalmentTypeResponse);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STAFF')")
    public ResponseEntity<String> deleteLoanType(@RequestParam String id) {
        instalmentTypeService.deleteInstalmentType( id);
        return ResponseEntity.ok("Successful Delete Loan Type");
    }
}
