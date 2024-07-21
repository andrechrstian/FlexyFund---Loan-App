package org.example.loan.service.impl;

import lombok.AllArgsConstructor;
import org.example.loan.dto.request.CustomerRequest;
import org.example.loan.dto.response.CustomerResponse;
import org.example.loan.entity.Customer;
import org.example.loan.repository.CustomerRepository;
import org.example.loan.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse createCustomer(CustomerRequest request) {

        Customer customer = customerRepository.save(
                Customer.builder()
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .phone(request.getPhone())
                        .status(request.getStatus())
                        .dateOfBirth(request.getDateOfBirth())
                        .user(request.getUser())
                        .build()
        );
        return convertToCustomerResponse(customer);
    }

    @Override
    public List<CustomerResponse> getAllCustomer() {
        return customerRepository.findAll().stream()
                .map(this::convertToCustomerResponse)
                .toList();
    }

    @Override
    public CustomerResponse getCustomerById(String id) {
        Customer customer = findByIdOrThrowNotFound(id);
        return convertToCustomerResponse(customer);
    }

    @Override
    public CustomerResponse updateCustomer(CustomerRequest request) {
        Customer existingCustomer = findByIdOrThrowNotFound(request.getId());
        existingCustomer.setFirstName(request.getFirstName());
        existingCustomer.setLastName(request.getLastName());
        existingCustomer.setPhone(request.getPhone());
        existingCustomer.setStatus(request.getStatus());
        existingCustomer.setDateOfBirth(request.getDateOfBirth());

        Customer updatedCustomer = customerRepository.saveAndFlush(existingCustomer);

        return convertToCustomerResponse(updatedCustomer);
    }

    @Override
    public void deleteCustomer(String id) {
        findByIdOrThrowNotFound(id);
        customerRepository.deleteById(id);
    }

    private Customer findByIdOrThrowNotFound(String id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
    }

    private CustomerResponse convertToCustomerResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .phone(customer.getPhone())
                .status(customer.getStatus())
                .dateOfBirth(customer.getDateOfBirth())
                .build();
    }
}
