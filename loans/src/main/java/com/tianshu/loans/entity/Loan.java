package com.tianshu.loans.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity(name = "LOANS")
@Getter
@Setter
@ToString
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "CUSTOMER_ID", nullable = false)
    private Long customerId;

    @Column(name = "START_DATE", nullable = false)
    private LocalDate startDate;

    @Column(name = "LOAN_TYPE", nullable = false)
    private String type;

    @Column(name = "TOTAL", nullable = false)
    private Double total;

    @Column(name = "AMOUNT_PAID", nullable = false)
    private Double amountPaid;

    @Column(name = "OUTSTANDING_AMOUNT", nullable = false)
    private Double outstandingAmount;

    @Column(name = "CREATE_DATE", nullable = false)
    private Date createDate;
}
