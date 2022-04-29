package com.tianshu.accounts.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString
public class AccountDto {

    private Long id;
    private Long customerId;
    private String type;
    private String branchAddress;
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date createDate;
}
