package com.tianshu.customers.task.process;

import com.tianshu.customers.dto.AccountDto;
import com.tianshu.customers.dto.CustomerDto;
import com.tianshu.customers.entity.MassiveFile.ProcessStatus;
import com.tianshu.customers.message.AccountEvent;
import com.tianshu.customers.message.Event;
import com.tianshu.customers.message.EventType;
import com.tianshu.customers.message.KafkaMQProducer;
import com.tianshu.customers.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class MassiveFileAccountProcess extends MassiveFileProcess{

    @Autowired
    private KafkaMQProducer kafkaMQProducer;

    @Value("${spring.kafka.topicAccounts}")
    private String topic;

    private final DataFormatter dataFormatter = new DataFormatter();

    @Override
    public ProcessStatus processRow(XSSFRow row, Long massiveFileId) throws Exception {
        AccountDto accountDto = AccountDto.builder()
                .customerId(Long.valueOf(dataFormatter.formatCellValue(row.getCell(0))))
                .type(dataFormatter.formatCellValue(row.getCell(1)))
                .branchAddress(dataFormatter.formatCellValue(row.getCell(2)))
                .createDate(new Date())
                .build();

        Event accountEvent = AccountEvent.builder()
                .eventType(EventType.NEW)
                .data(accountDto)
                .build();

        log.info(accountEvent.toString());
        kafkaMQProducer.sendProducerRecord(accountEvent, topic);

        return ProcessStatus.FINISHED;
    }
}
