package org.example.loan.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.loan.constant.APIURL;
import org.example.loan.dto.response.AvatarResponse;
import org.example.loan.dto.response.CommonResponse;
import org.example.loan.entity.Avatar;
import org.example.loan.repository.AvatarRepository;
import org.example.loan.service.AvatarService;
import org.example.loan.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AvatarServiceImpl implements AvatarService {
    private static final Logger logger = LoggerFactory.getLogger(AvatarServiceImpl.class);

    private final AvatarRepository avatarRepository;
    private final FileStorageService fileStorageService;

    @Override
    public CommonResponse<AvatarResponse> uploadAvatar(MultipartFile avatar, HttpServletRequest request) {
        String userId =(String) request.getAttribute("userId");;
        String name = fileStorageService.storeFile(avatar, userId);
        String fileDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(APIURL.AVATAR_API)
                .path(name)
                .toUriString();

        Optional<Avatar> existingAvatar = avatarRepository.findByName(name);

        Avatar avatarEntity;
        if (existingAvatar.isPresent()) {
            avatarEntity = existingAvatar.get();
            avatarEntity.setName(name);
            avatarEntity.setUrl(fileDownloadUrl);
        }else {
            avatarEntity = new Avatar();
            avatarEntity.setName(name);
            avatarEntity.setUrl(fileDownloadUrl);
        }

        avatarRepository.save(avatarEntity);

        AvatarResponse avatarResponse = AvatarResponse.builder()
                .id(avatarEntity.getId())
                .url(fileDownloadUrl)
                .name(name)
                .build();

        return CommonResponse.<AvatarResponse>builder()
                .message("Avatar Uploaded Success")
                .statusCode(HttpStatus.CREATED.value())
                .data(Optional.of(avatarResponse))
                .build();
    }
}
