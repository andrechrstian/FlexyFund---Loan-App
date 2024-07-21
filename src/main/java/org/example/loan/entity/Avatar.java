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
@Table(name = "t_profile_picture")
public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column (name = "name")
    private String name;
    @Column (name = "url")
    private String url;
}
