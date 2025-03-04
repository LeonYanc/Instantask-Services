package com.instantask.service.repository;

import com.instantask.service.model.Column;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColumnRepository extends MongoRepository<Column, String> {
}
