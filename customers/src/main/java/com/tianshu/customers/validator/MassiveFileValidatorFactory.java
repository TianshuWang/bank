package com.tianshu.customers.validator;

import com.tianshu.customers.entity.MassiveFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MassiveFileValidatorFactory {

    private final Map<MassiveFile.FileType, MassiveFileValidator> validatorMap = new HashMap<>();

    @Autowired
    public MassiveFileValidatorFactory(MassiveFileCustomerValidator customerValidator, MassiveFileAccountValidator accountValidator) {
        this.validatorMap.put(MassiveFile.FileType.CUSTOMER, customerValidator);
        this.validatorMap.put(MassiveFile.FileType.ACCOUNT,accountValidator);
    }

    public MassiveFileValidator getValidator(MassiveFile.FileType fileType){
        if(fileType == null){
            return null;
        }

        return this.validatorMap.get(fileType);
    }
}
