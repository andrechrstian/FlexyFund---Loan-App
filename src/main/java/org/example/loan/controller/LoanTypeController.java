package org.example.loan.controller;

import lombok.RequiredArgsConstructor;
import org.example.loan.constant.APIURL;
import org.example.loan.dto.request.LoanTypeRequest;
import org.example.loan.dto.response.LoanTypeResponse;
import org.example.loan.entity.LoanType;
import org.example.loan.service.LoanTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(APIURL.LOAN_TYPE_API)
@RequiredArgsConstructor
public class LoanTypeController {

    private final LoanTypeService loanTypeService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STAFF')")
    public List<LoanTypeResponse> getLoanTypes (){
        return loanTypeService.getAllLoanType();
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STAFF')")
    public ResponseEntity<LoanTypeResponse> getLoanTypeById (@RequestParam String id) {
        LoanTypeResponse loanTypeResponse = loanTypeService.getLoanTypeById(id);
        return ResponseEntity.ok(loanTypeResponse);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STAFF')")
    public ResponseEntity<LoanTypeResponse> postLoanType (@RequestBody LoanTypeRequest request) {
        LoanTypeResponse loanTypeResponse = loanTypeService.createLoanType(request);
        return ResponseEntity.ok(loanTypeResponse);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STAFF')")
    public ResponseEntity<LoanTypeResponse> putLoanType(@RequestBody LoanTypeRequest request) {
        LoanTypeResponse loanTypeResponse = loanTypeService.updateLoanTypeId(request);
        return ResponseEntity.ok(loanTypeResponse);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STAFF')")
    public ResponseEntity<String> deleteLoanType(@RequestParam String id) {
        loanTypeService.deleteLoanType(id);
        return ResponseEntity.ok("Successful Delete Loan Type");
    }
}
