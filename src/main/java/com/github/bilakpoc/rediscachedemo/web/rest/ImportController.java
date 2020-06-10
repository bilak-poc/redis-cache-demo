package com.github.bilakpoc.rediscachedemo.web.rest;

import java.net.URI;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.github.bilakpoc.rediscachedemo.generated.api.ImportsApi;
import com.github.bilakpoc.rediscachedemo.generated.model.ImportRequest;
import com.github.bilakpoc.rediscachedemo.generated.model.ModelImport;
import com.github.bilakpoc.rediscachedemo.service.ImportService;

@RestController
public class ImportController implements ImportsApi {

  private final ImportService importService;

  public ImportController(ImportService importService) {
    this.importService = importService;
  }

  @Override
  public ResponseEntity<ModelImport> createImport(@Valid ImportRequest importRequest) {
    ModelImport modelImport = importService.createImport(importRequest.getMetadata());
    return ResponseEntity.created(buildCurrentPathWithId(modelImport.getId())).body(modelImport);
  }

  @Override
  public ResponseEntity<ModelImport> readImport(UUID importId) {
    return ResponseEntity.ok(importService.getImportById(importId.toString()));
  }

  private URI buildCurrentPathWithId(final Object id) {
    return ServletUriComponentsBuilder.fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand(id)
      .toUri();
  }
}
