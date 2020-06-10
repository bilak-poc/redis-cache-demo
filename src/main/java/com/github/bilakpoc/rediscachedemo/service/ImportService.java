package com.github.bilakpoc.rediscachedemo.service;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.bilakpoc.rediscachedemo.generated.model.ImportRequestMetadata;
import com.github.bilakpoc.rediscachedemo.generated.model.ModelImport;
import com.github.bilakpoc.rediscachedemo.persistence.model.ImportEntity;
import com.github.bilakpoc.rediscachedemo.persistence.model.Status;
import com.github.bilakpoc.rediscachedemo.persistence.repository.ImportEntityRepository;

@Service
public class ImportService {

  private final ImportEntityRepository repository;
  private final ImportMapper mapper;

  public ImportService(ImportEntityRepository repository, ImportMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  public ModelImport createImport(ImportRequestMetadata metadata){
    ImportEntity entity = new ImportEntity();
    entity.setBusinessDate(metadata.getBusinessDate());
    entity.setCreated(OffsetDateTime.now());
    entity.setId(UUID.randomUUID().toString());
    entity.setStatus(Status.OPEN);
    entity = repository.save(entity);
    return mapper.fromEntity(entity);
  }

  @Cacheable(cacheNames = "imports")
  public ModelImport getImportById(String importId) {
    return repository.findById(importId)
      .map(mapper::fromEntity)
      .orElseThrow();
  }
}
