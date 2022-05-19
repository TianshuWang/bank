package com.tianshu.customers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.tianshu.accounts.entity.MassiveFile;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@JsonPropertyOrder({"fileName","fileType","processStatus","createDate"})
public class MassiveFileDto {

    @JsonProperty("file_name")
    private String fileName;

    @JsonProperty("file_type")
    private MassiveFile.FileType fileType;

    @JsonProperty("process_status")
    private MassiveFile.ProcessStatus processStatus;

    @JsonProperty("create_date")
    private Date createDate;
}
