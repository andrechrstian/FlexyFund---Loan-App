package org.example.loan.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.loan.constant.APIURL;
import org.example.loan.dto.response.AvatarResponse;
import org.example.loan.dto.response.CommonResponse;
import org.example.loan.service.AvatarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping (APIURL.AVATAR_API)
@RequiredArgsConstructor
public class AvatarController {
    private final AvatarService avatarService;

    @PostMapping("/upload")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<CommonResponse<AvatarResponse>> uploadAvatar(
            @RequestParam("avatar") MultipartFile avatar, HttpServletRequest request) {
        CommonResponse<AvatarResponse> response = avatarService.uploadAvatar(avatar, request);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
    }
}
