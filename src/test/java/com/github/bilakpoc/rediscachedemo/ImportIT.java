package com.github.bilakpoc.rediscachedemo;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.github.bilakpoc.rediscachedemo.generated.api.ImportsApi;
import com.github.bilakpoc.rediscachedemo.generated.model.ImportRequest;
import com.github.bilakpoc.rediscachedemo.generated.model.ImportRequestMetadata;
import com.github.bilakpoc.rediscachedemo.generated.model.ModelImport;

@SpringBootTest(classes = RedisCacheDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class ImportIT {

  @Autowired
  protected WebTestClient testClient;

  @Test
  void createAndGetImportFromCache() {
    final ModelImport modelImport = testClient.post()
      .uri("/imports")
      .bodyValue(mockImportRequest())
      .exchange()
      .expectStatus()
      .isCreated()
      .expectBody(ModelImport.class)
      .returnResult()
      .getResponseBody();

    getImport(modelImport.getId().toString());
    getImport(modelImport.getId().toString());

  }

  private void getImport(String importId) {
    testClient.get()
      .uri("/imports/{importId}", importId)
      .exchange()
      .expectStatus()
      .isOk()
      .expectBody(ModelImport.class);
  }

  protected ImportRequest mockImportRequest() {
    return new ImportRequest().metadata(mockRequestMetadata());
  }

  protected ImportRequestMetadata mockRequestMetadata() {
    return new ImportRequestMetadata().businessDate(LocalDate.now()).replaceId(UUID.randomUUID());
  }

}
