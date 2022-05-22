package com.tianshu.accounts.dao;

import com.tianshu.accounts.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    public List<Account> findByCustomerId(Long customerId);

    public Account findByIdAndCustomerId(Long id,Long customerId);
}
