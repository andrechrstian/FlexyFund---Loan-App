package org.example.loan.repository;

import org.example.loan.entity.LoanTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestLoanRepository extends JpaRepository<LoanTransaction, String> {

}
