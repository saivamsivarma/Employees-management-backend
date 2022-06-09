package com.example.EMSbackend.Repository;

import com.example.EMSbackend.Models.Department;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface DepartmentRepo extends MongoRepository<Department,String> {
    Department findBy_id(String id);
    @Query(value="{Organization_id: ?0}")
    List<Department> findByOrganizationId(ObjectId Organization_id);
}
