package com.tianshu.accounts.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerDto {

    private Long id;

    private String name;

    private String email;

    private String mobileNumber;

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date createDate;
}
