package org.example.loan.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.loan.entity.Enum.EInstalmentType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstalmentTypeResponse {
    private String id;
    private EInstalmentType instalmentType;
}
