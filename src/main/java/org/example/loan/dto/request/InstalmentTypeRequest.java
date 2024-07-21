package org.example.loan.dto.request;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.loan.entity.Enum.EInstalmentType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstalmentTypeRequest {
    private String id;
    private EInstalmentType instalmentType;
}
