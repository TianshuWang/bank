package com.tianshu.customers.controller;


import com.tianshu.customers.dto.MassiveFileDto;
import com.tianshu.customers.service.MassiveFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Api(tags = "Massive File Controller")
public class MassiveFileController {

    @Autowired
    private MassiveFileService massiveFileService;

    @PostMapping("/massive-file")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Receive Massive File")
    public MassiveFileDto createMassiveFile(@RequestParam MultipartFile file, @RequestParam String fileType,
                                            @RequestHeader("Content-Transfer-Encoding") String contentType) throws IOException {
        return massiveFileService.createMassiveFile(file,fileType,contentType);
    }
}
