package com.github.bilakpoc.rediscachedemo.service;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.springframework.util.StringUtils;

import com.github.bilakpoc.rediscachedemo.generated.model.ModelImport;
import com.github.bilakpoc.rediscachedemo.persistence.model.ImportEntity;

@Mapper(componentModel = "spring")
public interface ImportMapper {

  default UUID stringToUUID(String id){
    if (StringUtils.isEmpty(id)){
      return null;
    }
    return UUID.fromString(id);
  }

  ModelImport fromEntity(ImportEntity entity);
}
