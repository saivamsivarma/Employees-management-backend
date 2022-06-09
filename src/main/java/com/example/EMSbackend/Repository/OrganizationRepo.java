package com.example.EMSbackend.Repository;

import com.example.EMSbackend.Models.OrganizationModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface OrganizationRepo extends MongoRepository<OrganizationModel,String> {
    @Query(value = "{Organization_code: ?0}")
    Optional<OrganizationModel> findBranch_code(String Branch_code);

    OrganizationModel findBy_id(String id);
}
