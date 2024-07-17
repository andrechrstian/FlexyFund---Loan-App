package org.example.loan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "trx_loan")
public class LoanTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "nominal")
    private Double nominal;
    @Column(name = "approved_At")
    private Long approvedAt;
    @Column(name = "approved_by")
    private String approvedBy;
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;
    @Column(name = "created_at")
    private Long createdAt;
    @Column(name = "updated_at")
    private Long updatedAt;

    @ManyToOne
    @JoinColumn(name = "loan_type_id")
    private LoanType loanType;
    @ManyToOne
    @JoinColumn(name = "instalment_type_id")
    private InstalmentType instalmentType;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToMany(mappedBy = "loanTransaction", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LoanTransactionDetail> loanTransactionDetails;

}


