package com.tianshu.customers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.tianshu.customers.entity.MassiveFile;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"fileName","fileType","content_type","processStatus","createDate"})
public class MassiveFileDto {

    @JsonProperty("file_name")
    private String fileName;

    @JsonProperty("file_type")
    private MassiveFile.FileType fileType;

    @JsonProperty("content_type")
    private MassiveFile.ContentType contentType;

    @JsonProperty("process_status")
    private MassiveFile.ProcessStatus processStatus;

    @JsonProperty("create_date")
    private Date createDate;
}
