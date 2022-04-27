package com.tianshu.loans.dao;

import com.tianshu.loans.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    public List<Loan> findByCustomerId(Long customerId);
}
