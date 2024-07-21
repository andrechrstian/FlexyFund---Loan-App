package org.example.loan.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayInstalmentRequest {
    private String id;
    private String loanStatus;
    private Long updatedAt;
}
