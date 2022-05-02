package com.tianshu.cards.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class CustomerDto {

    private Long id;
    private String name;
    private String email;
    private String mobileNumber;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
}
