package com.tianshu.customers.validator;

import com.tianshu.customers.exception.MassiveFileException;
import com.tianshu.customers.utils.MassiveFileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Component
public class MassiveFileCustomerValidator implements MassiveFileValidator{

    @Autowired
    private MassiveFileUtils massiveFileUtils;

    @Override
    public void validate(MultipartFile file){
        String fileName = file.getOriginalFilename();

        if(massiveFileUtils.getFileByFileName(fileName) != null){
            throw new MassiveFileException(String.format("There's already exists a massive file with file name:%s",fileName));
        }

        XSSFSheet sheet = massiveFileUtils.getWorkSheetFromFile(file);

        if(sheet.getPhysicalNumberOfRows() > 50 || sheet.getRow(0).getPhysicalNumberOfCells() > 3){
            throw new MassiveFileException("Incorrect form format");
        }
    }
}
