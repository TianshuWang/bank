package com.tianshu.customers.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "MASSIVE_FILES")
@Getter
@Setter
@ToString
@Builder
public class MassiveFile {

    public enum FileType{
        CUSTOMER, ACCOUNT
    }

    public enum ProcessStatus{
        PENDING, FINISHED, ERROR, FINISHED_WITH_ERROR
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "FILE_NAME", nullable = false)
    private String fileName;

    @Column(name = "FILE_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private FileType fileType;

    @Column(name = "XLSX_FILE", nullable = false)
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] xlsxFile;

    @Column(name = "PROCESS_STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProcessStatus processStatus;

    @Column(name = "CREATE_DATE")
    private Date createDate;
}
