package org.example.loan.controller;

import lombok.AllArgsConstructor;
import org.example.loan.constant.APIURL;
import org.example.loan.dto.request.CustomerRequest;
import org.example.loan.dto.response.CustomerResponse;
import org.example.loan.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(APIURL.CUSTOMER_API)
@AllArgsConstructor
public class CustomerController {

    private CustomerService customerService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest request) {
        CustomerResponse createdCustomer = customerService.createCustomer(request);
        return ResponseEntity.ok(createdCustomer);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<CustomerResponse> updateCustomer(@RequestBody CustomerRequest request) {
        CustomerResponse createdCustomer = customerService.updateCustomer(request);
        return ResponseEntity.ok(createdCustomer);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<String> deleteCustomer(@RequestParam String id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("Success Delete Customer By Id");
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<CustomerResponse> getCustomer(@RequestParam String id) {
        CustomerResponse customerResponse = customerService.getCustomerById(id);
        return ResponseEntity.ok(customerResponse);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public List<CustomerResponse> getAllCustomer() {
        return customerService.getAllCustomer();
    }



}
