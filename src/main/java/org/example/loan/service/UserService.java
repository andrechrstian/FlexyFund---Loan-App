package org.example.loan.service;

import org.example.loan.dto.request.CustomerRequest;
import org.example.loan.dto.response.CustomerResponse;
import org.example.loan.dto.response.UserResponse;
import org.example.loan.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    public UserDetails loadUserById(String id);
    UserResponse getUserById(String id);
    List<UserResponse> getUsers();
    UserResponse updatedUser (User request);
    void deleteUser (String id);

}
