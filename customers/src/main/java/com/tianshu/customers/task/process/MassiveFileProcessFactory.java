package com.tianshu.customers.task.process;

import com.tianshu.customers.entity.MassiveFile;
import com.tianshu.customers.entity.MassiveFile.FileType;
import com.tianshu.customers.validator.MassiveFileAccountValidator;
import com.tianshu.customers.validator.MassiveFileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MassiveFileProcessFactory {

    private final Map<FileType, MassiveFileProcess> processMap = new HashMap<>();

    @Autowired
    public MassiveFileProcessFactory(MassiveFileCustomerProcess customerProcess, MassiveFileAccountProcess accountProcess){
        processMap.put(FileType.CUSTOMER,customerProcess);
        processMap.put(FileType.ACCOUNT, accountProcess);
    }

    public MassiveFileProcess getProcess(FileType fileType){
        if(fileType == null){
            return null;
        }

        return this.processMap.get(fileType);
    }

}
