package com.instantask.service.repository;

import com.instantask.service.model.TaskCard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskCardRepository extends MongoRepository<TaskCard, String> {
}
