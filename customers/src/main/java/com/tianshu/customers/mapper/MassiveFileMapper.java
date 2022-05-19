package com.tianshu.customers.mapper;


import com.tianshu.customers.dto.MassiveFileDto;
import com.tianshu.customers.entity.MassiveFile;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MassiveFileMapper {

    MassiveFileMapper INSTANCE = Mappers.getMapper(MassiveFileMapper.class);

    MassiveFileDto entityToDto(MassiveFile massiveFile);

    MassiveFile dtoToEntity(MassiveFileDto massiveFileDto);

}
