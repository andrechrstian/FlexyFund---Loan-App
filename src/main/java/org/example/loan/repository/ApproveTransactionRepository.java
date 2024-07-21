package org.example.loan.repository;

import org.example.loan.entity.LoanTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApproveTransactionRepository extends JpaRepository<LoanTransaction, String> {
}
