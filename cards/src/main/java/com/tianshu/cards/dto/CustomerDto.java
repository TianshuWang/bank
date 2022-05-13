package com.tianshu.cards.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    private Date createDate;
}
