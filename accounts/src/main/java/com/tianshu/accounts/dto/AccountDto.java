package com.tianshu.accounts.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class AccountDto {

    private Long id;
    private Long customerId;
    private String type;
    private String branchAddress;
    private LocalDate createDate;
}
