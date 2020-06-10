package com.github.bilakpoc.rediscachedemo.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.bilakpoc.rediscachedemo.persistence.model.ImportEntity;

public interface ImportEntityRepository extends JpaRepository<ImportEntity, String> {
}
