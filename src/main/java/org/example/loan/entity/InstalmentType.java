package org.example.loan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_instalment_type")
public class InstalmentType {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Enumerated(EnumType.STRING)
    private EInstalmentType instalmentType;

    enum EInstalmentType {
        ONE_MONTH,
        THREE_MONTHS,
        SIXTH_MONTHS,
        NINE_MONTHS,
        TWELVE_MONTHS
    }
}
