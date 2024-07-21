package org.example.loan.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AvatarResponse {
    private String id;
    private String name;
    private String url;
}
