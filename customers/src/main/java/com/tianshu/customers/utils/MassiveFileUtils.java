package com.tianshu.customers.utils;

import com.tianshu.customers.dao.MassiveFileRepository;
import com.tianshu.customers.entity.MassiveFile;
import com.tianshu.customers.exception.MassiveFileException;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

@Component
public class MassiveFileUtils {

    @Autowired
    private MassiveFileRepository massiveFileRepository;

    private DataFormatter dataFormatter = new DataFormatter();

    public MassiveFile getFileByFileName(String fileName){
        return massiveFileRepository.findByFileName(fileName);
    }

    public byte[] getBytesFromFile(MultipartFile file) throws IOException {
        return file.getBytes();
    }

    public XSSFSheet getWorkSheetFromFile(MultipartFile file){
        XSSFSheet sheet = null;

        try {
            InputStream inputStream = new ByteArrayInputStream(file.getBytes());
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            sheet = workbook.getSheetAt(0);
        }
        catch (Exception e) {
            throw new MassiveFileException("Error loading the form.");
        }

        return sheet;
    }

    public XSSFSheet getWorkSheetFromMassiveFile(MassiveFile massiveFile){
        XSSFSheet sheet = null;

        try {
            InputStream inputStream = new ByteArrayInputStream(massiveFile.getXlsxFile());
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            sheet = workbook.getSheetAt(0);
            XSSFRow row = sheet.getRow(1);

            if (row == null || rowIsEmpty(row)) {
                throw new MassiveFileException(String.format("The file %s is empty.", massiveFile.getFileName()));
            }
        }
        catch (Exception e) {
            String message = StringUtils.isBlank(e.getMessage()) ? "Error obtaining the massive file" : e.getMessage();
            throw new MassiveFileException(message);
        }

        return sheet;
    }

    public boolean rowIsEmpty(XSSFRow row){

        return dataFormatter.formatCellValue(row.getCell(0)).isEmpty() ||
                dataFormatter.formatCellValue(row.getCell(1)).isEmpty() ||
                dataFormatter.formatCellValue(row.getCell(2)).isEmpty();
    }

    public String base64EnCode(InputStream inputStream){
        byte[] bytes = null;
        try(ByteArrayOutputStream bos = new ByteArrayOutputStream(1024)) {
            byte[] b = new byte[1024];
            int n;

            while((n = inputStream.read(b)) != -1){
                bos.write(b,0,n);
            }

            bytes = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(bytes);
    }
}
