package org.example.loan.controller;

import lombok.RequiredArgsConstructor;
import org.example.loan.constant.APIURL;
import org.example.loan.dto.response.CommonResponse;
import org.example.loan.dto.response.UserResponse;
import org.example.loan.entity.User;
import org.example.loan.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(APIURL.USER_API)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserResponse> getUserById(@RequestParam String id) {
        UserResponse userResponse = userService.getUserById(id);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<UserResponse> getAllUser() {
        return userService.getUsers();
    }

    @PutMapping
    public ResponseEntity<UserResponse> updateUser(@RequestBody User request) {
        UserResponse userResponse = userService.updatedUser(request);
        return ResponseEntity.ok(userResponse);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(@RequestParam String id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("Success delete User");
    }

}
