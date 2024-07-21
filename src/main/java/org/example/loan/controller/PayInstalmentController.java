package org.example.loan.controller;

import lombok.RequiredArgsConstructor;
import org.example.loan.constant.APIURL;
import org.example.loan.dto.request.PayInstalmentRequest;
import org.example.loan.dto.response.PayInstalmentResponse;
import org.example.loan.service.PayInstalmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(APIURL.PAYMENT_INSTALMENT_API)
@RequiredArgsConstructor
public class PayInstalmentController {

    private final PayInstalmentService payInstalmentService;

    @PutMapping
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<PayInstalmentResponse> createPayment(@RequestBody PayInstalmentRequest request){
        PayInstalmentResponse payInstalmentResponse = payInstalmentService.payInstalment(request);
        return ResponseEntity.ok(payInstalmentResponse);
    }
}
