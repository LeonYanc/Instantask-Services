package com.instantask.service.repository;

import com.instantask.service.model.UserAccess;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAccessRepository extends MongoRepository<UserAccess, String> {

    UserAccess findByUserIdAndBoardId(String userId, String boardId);

    List<UserAccess> findByBoardId(String boardId);

    List<UserAccess> findByUserId(String userId);
}
