package com.tianshu.customers.service;

import com.tianshu.customers.dao.MassiveFileRepository;
import com.tianshu.customers.dto.MassiveFileDto;
import com.tianshu.customers.entity.MassiveFile;
import com.tianshu.customers.exception.MassiveFileException;
import com.tianshu.customers.mapper.MassiveFileMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Service
@Slf4j
public class MassiveFileService {

    @Autowired
    private MassiveFileRepository massiveFileRepository;

    @Autowired
    private MassiveFileMapper massiveFileMapper;

    public MassiveFileDto createMassiveFile(MultipartFile file, String fileType) throws IOException {
        String fileName = file.getOriginalFilename();
        if(massiveFileRepository.findByFileName(fileName) != null){
            throw new MassiveFileException(String.format("The massive file with file name %s is already exists.", fileName));
        }

        MassiveFile massiveFile = MassiveFile.builder()
                .fileName(fileName)
                .fileType(MassiveFile.FileType.valueOf(fileType.toUpperCase()))
                .xlsxFile(file.getBytes())
                .createDate(new Date())
                .processStatus(MassiveFile.ProcessStatus.PENDING)
                .build();


        MassiveFile saved = massiveFileRepository.save(massiveFile);
        log.info("The massive file is saved with Id:{}", saved.getId());

        return massiveFileMapper.entityToDto(saved);
    }
}
