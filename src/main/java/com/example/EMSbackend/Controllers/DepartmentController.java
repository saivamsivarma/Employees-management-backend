package com.example.EMSbackend.Controllers;

import com.example.EMSbackend.Models.Department;
import com.example.EMSbackend.Models.OrganizationModel;
import com.example.EMSbackend.Repository.DepartmentRepo;
import com.example.EMSbackend.Repository.EmployeesRepo;
import com.example.EMSbackend.Repository.OrganizationRepo;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/Department")
public class DepartmentController {
    private final DepartmentRepo departmentRepo;
    private final OrganizationRepo organizationRepo;
    private final EmployeesRepo employeesRepo;

    public DepartmentController(DepartmentRepo departmentRepo, OrganizationRepo organizationRepo, EmployeesRepo employeesRepo) {
        this.departmentRepo = departmentRepo;
        this.organizationRepo = organizationRepo;
        this.employeesRepo = employeesRepo;
    }

    @PostMapping("")
    public ResponseEntity createDepartment(@RequestBody Department department){
        System.out.println(department.getOrganization_id());
        OrganizationModel organization =  organizationRepo.findBy_id(department.getOrganization_id());

        int no_of_department = (int) organization.getNo_ofDepart();
        if(no_of_department>0){
            no_of_department-=1;
            organization.setNo_ofDepart(no_of_department);
            organizationRepo.save(organization);
            department.setContractEmployeeNum(0);
            department.setFullTimeEmployeeNum(0);
            department.setInternEmployeeNum(0);
            departmentRepo.insert(department);
            ResponseEntity.status(HttpStatus.ACCEPTED).body(department);
        }
        else {
            ResponseEntity.status(HttpStatus.ACCEPTED).body("upgrade");
        }
        return null;
    }

    @GetMapping("/details/{_id}")
    public ResponseEntity getDepartmentDetails(@PathVariable String _id){
        try{
            Department department = departmentRepo.findBy_id(_id);
            return ResponseEntity.ok(department);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e);
        }
    }
    @GetMapping("/{org_id}")
    public ResponseEntity<List<Department>> getOrganizationDepartments(@PathVariable ObjectId org_id){
        List<Department> departments= departmentRepo.findByOrganizationId(org_id);
        return ResponseEntity.ok(departments);
    }
}
