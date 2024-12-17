package com.backend.Repositories;

import com.backend.Models.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InnerUserRepository extends MongoRepository<UserModel, Integer>{

    
}
