package org.example.loan.service;

import jakarta.servlet.http.HttpServletRequest;
import org.example.loan.dto.response.AvatarResponse;
import org.example.loan.dto.response.CommonResponse;
import org.springframework.web.multipart.MultipartFile;

public interface AvatarService {
    CommonResponse<AvatarResponse> uploadAvatar (MultipartFile avatar, HttpServletRequest request);
}
