package com.tianshu.customers.validator;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MassiveFileValidator {

    void validate(MultipartFile file) ;
}
