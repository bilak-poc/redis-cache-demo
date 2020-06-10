package com.github.bilakpoc.rediscachedemo.persistence.model;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "imports")
@Data
public class ImportEntity {

  @Id
  private String id;
  private LocalDate businessDate;
  private OffsetDateTime created;
  @Enumerated(value = EnumType.STRING)
  private Status status;
}
