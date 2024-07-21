package org.example.loan.controller;

import lombok.RequiredArgsConstructor;
import org.example.loan.constant.APIURL;
import org.example.loan.dto.request.RequestLoanRequest;
import org.example.loan.dto.response.RequestLoanResponse;
import org.example.loan.service.RequestLoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(APIURL.REQUEST_LOAN_API)
@RequiredArgsConstructor
public class RequestLoanController {
    private final RequestLoanService requestLoanService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<RequestLoanResponse> createRequestLoan(@RequestBody RequestLoanRequest request) {
        RequestLoanResponse requestLoanResponse = requestLoanService.createRequestLoan(request);
        return ResponseEntity.ok(requestLoanResponse);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public List<RequestLoanResponse> getAllRequestLoan (){
        return requestLoanService.getAllRequestLoan();
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<RequestLoanResponse> getRequestLoanById (@RequestParam String id) {
        RequestLoanResponse requestLoanResponse = requestLoanService.getRequestLoanById(id);
        return ResponseEntity.ok(requestLoanResponse);
    }

}
