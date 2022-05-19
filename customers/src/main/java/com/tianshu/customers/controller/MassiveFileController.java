package com.tianshu.customers.controller;


import com.tianshu.customers.dto.MassiveFileDto;
import com.tianshu.customers.service.MassiveFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Api(tags = "Massive File Controller")
public class MassiveFileController {

    @Autowired
    private MassiveFileService massiveFileService;

    @PostMapping("/massive-file/{fileType}")
    @ApiOperation("Create Massive File")
    public MassiveFileDto createMassiveFile(@RequestParam MultipartFile file, @PathVariable String fileType) throws IOException {
        return massiveFileService.createMassiveFile(file,fileType);
    }
}
