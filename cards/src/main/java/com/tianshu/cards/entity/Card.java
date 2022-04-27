package com.tianshu.cards.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "CARDS")
@Getter
@Setter
@ToString
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "CUSTOMER_ID", nullable = false)
    private Long customerId;

    @Column(name = "CARD_NUMBER", nullable = false)
    private Long cardNumber;

    @Column(name = "CARD_TYPE", nullable = false)
    private String type;

    @Column(name = "TOTAL_LIMIT", nullable = false)
    private Double totalLimit;

    @Column(name = "AMOUNT_USED", nullable = false)
    private Double amountUsed;

    @Column(name = "AVAILABLE_AMOUNT", nullable = false)
    private Double availableAmount;

    @Column(name = "CREATE_DATE", nullable = false)
    private LocalDate createDate;
}
