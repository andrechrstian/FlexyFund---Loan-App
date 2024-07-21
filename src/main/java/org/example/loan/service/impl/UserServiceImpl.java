package org.example.loan.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.loan.dto.response.UserResponse;
import org.example.loan.entity.AppUser;
import org.example.loan.entity.User;
import org.example.loan.repository.UserRepository;
import org.example.loan.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;


    @Override
    @PreAuthorize("hasRole('CUSTOMER')")
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid email"));

        return AppUser.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRoles().get(0).getRole())
                .build();
    }

    @Override
    public UserDetails loadUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid id -" + id));

        return AppUser.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRoles().get(0).getRole())
                .build();
    }

    @Override
    public UserResponse getUserById(String id) {
        User user = findByIdOrThrowNotFound(id);
        return convertToUserResponse(user);
    }

    @Override
    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToUserResponse)
                .toList();
    }

    @Override
    public UserResponse updatedUser(User request) {
        User existingUser = findByIdOrThrowNotFound(request.getId());
        existingUser.setEmail(request.getEmail());

        User updatedUser = userRepository.save(existingUser);

        return convertToUserResponse(updatedUser);
    }

    @Override
    public void deleteUser(String id) {
        findByIdOrThrowNotFound(id);
        userRepository.deleteById(id);
    }


    //METHOD
    private User findByIdOrThrowNotFound(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID Not Found"));
    }

    private UserResponse convertToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .roles(user.getRoles())
                .build();
    }


}

