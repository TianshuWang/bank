package com.tianshu.customers.task.process;

import com.tianshu.customers.dto.CustomerDto;
import com.tianshu.customers.entity.MassiveFile.ProcessStatus;
import com.tianshu.customers.service.CustomerService;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MassiveFileCustomerProcess extends MassiveFileProcess{

    @Autowired
    private CustomerService customerService;

    private final DataFormatter dataFormatter = new DataFormatter();

    @Override
    public ProcessStatus processRow(XSSFRow row, Long massiveFileId) throws Exception {
        CustomerDto customerDto = CustomerDto.builder()
                .name(dataFormatter.formatCellValue(row.getCell(0)))
                .email(dataFormatter.formatCellValue(row.getCell(1)))
                .mobileNumber(dataFormatter.formatCellValue(row.getCell(2)))
                .createDate(new Date())
                .build();

        customerService.createCustomer(customerDto);

        return ProcessStatus.FINISHED;
    }
}
