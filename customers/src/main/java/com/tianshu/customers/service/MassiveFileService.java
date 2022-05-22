package com.tianshu.customers.service;

import com.tianshu.customers.dao.MassiveFileRepository;
import com.tianshu.customers.dto.MassiveFileDto;
import com.tianshu.customers.entity.MassiveFile;
import com.tianshu.customers.exception.MassiveFileException;
import com.tianshu.customers.mapper.MassiveFileMapper;
import com.tianshu.customers.task.process.MassiveFileProcess;
import com.tianshu.customers.task.process.MassiveFileProcessFactory;
import com.tianshu.customers.utils.MassiveFileUtils;
import com.tianshu.customers.validator.MassiveFileValidator;
import com.tianshu.customers.validator.MassiveFileValidatorFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
@Slf4j
public class MassiveFileService {

    @Autowired
    private MassiveFileRepository massiveFileRepository;

    @Autowired
    private MassiveFileMapper massiveFileMapper;

    @Autowired
    private MassiveFileValidatorFactory massiveFileValidatorFactory;

    @Autowired
    private MassiveFileUtils massiveFileUtils;

    public MassiveFileDto createMassiveFile(MultipartFile file, String fileType, String contentType) throws IOException {
        MassiveFile.FileType massiveFileType = MassiveFile.FileType.valueOf(fileType.toUpperCase());
        MassiveFile.ContentType massiveFileContentType = MassiveFile.ContentType.valueOf(contentType.toUpperCase());

        InputStream inputStream = new ByteArrayInputStream(file.getBytes());
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] decoderResult = decoder.decodeBuffer(massiveFileUtils.base64EnCode(inputStream));

        MassiveFileValidator validator = massiveFileValidatorFactory.getValidator(massiveFileType);

        validator.validate(file);

        MassiveFile massiveFile = MassiveFile.builder()
                .fileName(file.getOriginalFilename())
                .fileType(massiveFileType)
                .xlsxFile(decoderResult)
                .createDate(new Date())
                .contentType(massiveFileContentType)
                .processStatus(MassiveFile.ProcessStatus.PENDING)
                .build();

        MassiveFile saved = massiveFileRepository.save(massiveFile);
        log.info("The massive file:{} is saved with Id:{}", saved.getFileName(), saved.getId());

        return massiveFileMapper.entityToDto(saved);
    }
}
