package com.tianshu.customers.task;

import com.tianshu.customers.dao.MassiveFileRepository;
import com.tianshu.customers.entity.MassiveFile;
import com.tianshu.customers.entity.MassiveFile.ProcessStatus;
import com.tianshu.customers.task.process.MassiveFileProcessFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class MassiveFileTask {

    @Autowired
    private MassiveFileProcessFactory massiveFileProcessFactory;

    @Autowired
    private MassiveFileRepository massiveFileRepository;

    @Value("${task.massiveFiles.enabled}")
    private boolean enabled;

    @Scheduled(fixedDelay = 60 * 1000)
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void massiveFileTask(){

        if(!enabled){
            return;
        }

        MassiveFile massiveFile = massiveFileRepository.findFirstByProcessStatusOrderByCreateDateAsc(ProcessStatus.PENDING);

        if(massiveFile == null){
            return;
        }

        log.info("The file:{} will be processed",massiveFile.getFileName());

        ProcessStatus processStatus = massiveFileProcessFactory.getProcess(massiveFile.getFileType()).process(massiveFile);
        massiveFile.setProcessStatus(processStatus);
        final MassiveFile saved = massiveFileRepository.save(massiveFile);

        log.info("The file:{} was processed with process status:{}",saved.getFileName(),saved.getProcessStatus());
    }
}
