package com.example.EMSbackend.Repository;

import com.example.EMSbackend.Models.Department;
import com.example.EMSbackend.Models.Employee;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeesRepo extends MongoRepository<Employee,String> {

    @Query(value = "{Employee_email: ?0}")
    Optional<Employee> findByEmail(String Employee_email);

    Employee findBy_id(String id);
    @Query(value="{Dept_id: ?0}")
    List<Employee> findByDeptId(ObjectId Dept_id);
    @Query(value="{Org_id: ?0}")
    List<Employee> findByOrgId(ObjectId Org_id);
    /*@Query(value="{ $and: [ { Dept_id: ?0 }, { JobType:?0 } ] }")
    List<Employee> findByOrganizationId(ObjectId Dept_id,String JobType);*/
}
