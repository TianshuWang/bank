package com.tianshu.customers.task.process;

import com.tianshu.customers.dao.MassiveFileRepository;
import com.tianshu.customers.entity.MassiveFile;
import com.tianshu.customers.entity.MassiveFile.ProcessStatus;
import com.tianshu.customers.exception.MassiveFileException;
import com.tianshu.customers.utils.MassiveFileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class MassiveFileProcess {

    @Autowired
    private MassiveFileRepository massiveFileRepository;

    @Autowired
    private MassiveFileUtils massiveFileUtils;

    public abstract ProcessStatus processRow(XSSFRow row, Long massiveFileId) throws Exception;

    public ProcessStatus process(MassiveFile massiveFile){
        XSSFSheet sheet = null;

        try {
            sheet = massiveFileUtils.getWorkSheetFromMassiveFile(massiveFile);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return MassiveFile.ProcessStatus.ERROR;
        }

        boolean sheetHasError = false;

        for(int rowNumber = 1,numberRows = sheet.getPhysicalNumberOfRows(); rowNumber < numberRows; rowNumber++){
            try{
                XSSFRow row = sheet.getRow(rowNumber);
                if(massiveFileUtils.rowIsEmpty(row)){
                    break;
                }
                processRow(row, massiveFile.getId());
            } catch (Exception e) {
                sheetHasError = true;
                log.error("Error in file:{}, row:{}, reason:{}", massiveFile.getId(),rowNumber,e.getMessage());
            }
        }

        if (sheetHasError) {
            return ProcessStatus.FINISHED_WITH_ERROR;
        } else {
            log.info("All records in the file:{} were processed", massiveFile.getId());
            return ProcessStatus.FINISHED;
        }
    }

}
