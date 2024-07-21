package org.example.loan.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.loan.dto.request.CustomerRequest;
import org.example.loan.dto.response.LoginResponse;
import org.example.loan.dto.response.RegisterResponse;
import org.example.loan.entity.AppUser;
import org.example.loan.entity.Enum.ERole;
import org.example.loan.entity.Role;
import org.example.loan.entity.User;
import org.example.loan.repository.UserRepository;
import org.example.loan.security.JwtUtil;
import org.example.loan.service.AuthService;
import org.example.loan.service.CustomerService;
import org.example.loan.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final RoleService roleService;
    private final UserRepository userRepository;
    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public RegisterResponse registerAdmin(CustomerRequest.AuthRequest<CustomerRequest> authRequest) {
        Role role = roleService.getOrSave(ERole.ROLE_ADMIN);
        List<Role> roles = new ArrayList<>();
        roles.add(role);

        User user = User.builder()
                .email(authRequest.getEmail().toLowerCase())
                .password(passwordEncoder.encode(authRequest.getPassword()))
                .roles(roles)
                .build();

        user = userRepository.save(user);

        CustomerRequest requestData = authRequest.getData().orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Not Found")
        );

        requestData.setUser(user);

        customerService.createCustomer(requestData);

        return RegisterResponse.builder()
                .email(user.getEmail())
                .role(role.getRole())
                .build();
    }

    @Override
    @Transactional
    public RegisterResponse registerCustomer(CustomerRequest.AuthRequest<CustomerRequest> request) {
        Role role = roleService.getOrSave(ERole.ROLE_CUSTOMER);
        List<Role> roles = new ArrayList<>();
        roles.add(role);

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roles)
                .build();

        user = userRepository.save(user);

        CustomerRequest requestData = request.getData().orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Not Found")
        );

        requestData.setUser(user); //set relation customer to user

        customerService.createCustomer(requestData);

        return RegisterResponse.builder()
                .email(user.getEmail())
                .role(role.getRole())
                .build();
    }

    @Override
    public RegisterResponse registerStaff(CustomerRequest.AuthRequest<CustomerRequest> request) {
        Role role = roleService.getOrSave(ERole.ROLE_STAFF);
        List<Role> roles = new ArrayList<>();
        roles.add(role);

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roles)
                .build();

        user = userRepository.save(user);

        CustomerRequest requestData = request.getData().orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Not Found")
        );

        requestData.setUser(user); //set relation customer to user

        customerService.createCustomer(requestData);

        return RegisterResponse.builder()
                .email(user.getEmail())
                .role(role.getRole())
                .build();
    }

    @Override
    public LoginResponse login(CustomerRequest.AuthRequest<String> request) {

        //Authentication Manager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        AppUser appUser = (AppUser) authentication.getPrincipal();

        //TODO : Generate Token
        String token = jwtUtil.generateToken(appUser);

        return LoginResponse.builder()
                .token(token)
                .role(appUser.getRole())
                .build();
    }

}
