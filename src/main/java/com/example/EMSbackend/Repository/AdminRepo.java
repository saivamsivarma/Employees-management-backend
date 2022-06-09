package com.example.EMSbackend.Repository;

import com.example.EMSbackend.Models.AdminModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface AdminRepo extends MongoRepository<AdminModel,String> {
    @Query(value = "{Email: ?0}")
    Optional<AdminModel> findByEmail(String Email);
}
