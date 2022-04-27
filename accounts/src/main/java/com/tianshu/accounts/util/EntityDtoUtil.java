package com.tianshu.accounts.util;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.tianshu.accounts.dto.CustomerDto;
import com.tianshu.accounts.entity.Customer;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {

    public static <DTO> DTO entityToDto(Object entity, Class<DTO> dtoClass) {
        DTO dto = null;
        try {
            dto = dtoClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        BeanUtils.copyProperties(entity,dto);
        return dto;
    }

    public static <ENTITY> ENTITY dtoToEntity(Object dto, Class<ENTITY> entityClazz) {
        ENTITY entity = null;
        try {
            entity = entityClazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        BeanUtils.copyProperties(dto,entity);
        return entity;
    }
}
