package com.tianshu.accounts.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "ACCOUNTS")
@Getter
@Setter
@ToString
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "CUSTOMER_ID", nullable = false)
    private Long customerId;

    @Column(name = "ACCOUNT_TYPE", nullable = false)
    private String type;

    @Column(name = "BRANCH_ADDRESS", nullable = false)
    private String branchAddress;

    @Column(name = "CREATE_DATE", nullable = false)
    private LocalDate createDate;

}
