package com.example.EMSbackend.Controllers;

import com.example.EMSbackend.Models.Department;
import com.example.EMSbackend.Models.Employee;
import com.example.EMSbackend.Models.OrganizationModel;
import com.example.EMSbackend.Repository.DepartmentRepo;
import com.example.EMSbackend.Repository.EmployeesRepo;
import com.example.EMSbackend.Repository.OrganizationRepo;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/Employees")
public class EmployeeController {
    private final EmployeesRepo employeesRepo;
    private final OrganizationRepo organizationRepo;
    private final DepartmentRepo departmentRepo;

    public EmployeeController(EmployeesRepo employeesRepo, OrganizationRepo organizationRepo, DepartmentRepo departmentRepo) {
        this.employeesRepo = employeesRepo;
        this.organizationRepo = organizationRepo;
        this.departmentRepo = departmentRepo;
    }

    @PostMapping("/{org_id}")
    public ResponseEntity createEmployee(@RequestBody Employee employee, @PathVariable String org_id){
        OrganizationModel organization = organizationRepo.findBy_id(org_id);
        Department department = departmentRepo.findBy_id(employee.getDept_id());
        int size = (int) organization.getSize();
        if(size>0){
            size-=1;
            organization.setSize(size);
            organizationRepo.save(organization);
            boolean existEmployee = employeesRepo.findByEmail(employee.getEmployee_email()).isPresent();
            int salary = (int) employee.getSalary();
            try{
                if(!existEmployee){
                    if(employee.getJobType().equals("Fulltime")||employee.getJobType().equals("Internship")) {
                        int fullTime = (int) department.getFullTimeEmployeeNum();
                        int Intern = (int) department.getInternEmployeeNum();

                        if(employee.getJobType().equals("Fulltime")) department.setFullTimeEmployeeNum(fullTime+1);
                        else if(employee.getJobType().equals("Internship")) department.setInternEmployeeNum(Intern+1);
                        employee.setSalary(salary * 160);
                    } else {
                        int Contract = (int) department.getContractEmployeeNum();
                        department.setContractEmployeeNum(Contract+1);
                        employee.setSalary(salary * 140);
                    }
                    departmentRepo.save(department);
                    employeesRepo.insert(employee);

                    return ResponseEntity.status(HttpStatus.CREATED).body(employee);
                }
                else return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already Existed! "+employee.getEmployee_email());

            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e);
            }
        }
        else return ResponseEntity.status(HttpStatus.ACCEPTED).body("upgrade");
    }

    @GetMapping("/{_id}")
    public ResponseEntity<List<Employee>> findEmployee(@PathVariable ObjectId _id) {
            List<Employee> employeeList = employeesRepo.findByOrgId(_id);
            return ResponseEntity.ok(employeeList);
    }

    @GetMapping("/dept_employee/{dep_id}")
    public ResponseEntity<List<Employee>> getOrganizationEmployee(@PathVariable ObjectId dep_id){
        return ResponseEntity.ok(employeesRepo.findByDeptId(dep_id));
    }

    @DeleteMapping("/{_id}/{org_id}")
    public ResponseEntity deleteEmployee(@PathVariable String _id, @PathVariable String org_id) {
        OrganizationModel organization = organizationRepo.findBy_id(org_id);
        Employee employee = employeesRepo.findBy_id(_id);
        Department department = departmentRepo.findBy_id(employee.getDept_id());
        int size = (int) organization.getSize();
        int fullTime = (int) department.getFullTimeEmployeeNum();
        int Intern = (int) department.getInternEmployeeNum();
        int Contract = (int) department.getContractEmployeeNum();
        if(size>=0){
            size+=1;
            organization.setSize(size);

            if(employee.getJobType().equals("Fulltime")) department.setFullTimeEmployeeNum(fullTime-1);
            else if(employee.getJobType().equals("Intern")) department.setInternEmployeeNum(Intern-1);
            else department.setContractEmployeeNum(Contract-1);
            departmentRepo.save(department);
            organizationRepo.save(organization);
            try{
                employeesRepo.deleteById(_id);
                return ResponseEntity.accepted().body("Employee record successFully deleted");
            } catch (Exception e) {
                return ResponseEntity.status(404).body(e);
            }
        }
        else return ResponseEntity.status(HttpStatus.ACCEPTED).body("upgrade");
    }

    @PatchMapping("/{_id}")
    public ResponseEntity updateEmployee( @RequestBody  Employee newEmployee,@PathVariable String _id){
        Employee employee = employeesRepo.findBy_id(_id);
        Department department = departmentRepo.findBy_id(employee.getDept_id());
        String preJobType =employee.getJobType();
        String newJobType = newEmployee.getJobType();
        int fullTime = (int) department.getFullTimeEmployeeNum();
        int Intern = (int) department.getInternEmployeeNum();
        int Contract = (int) department.getContractEmployeeNum();
        employee.setEmployee_name(newEmployee.getEmployee_name());
        employee.setEmployee_email(newEmployee.getEmployee_email());
        employee.setContact((BigInteger) newEmployee.getContact());
        employee.setRole(newEmployee.getRole());
        employee.setExperience(newEmployee.getExperience());
        employee.setSalary(newEmployee.getSalary());
        System.out.println(preJobType+" "+newJobType);
        if(!preJobType.equals(newJobType)){
            if(preJobType.equals("Contract")&&newJobType.equals("Fulltime")){
                department.setContractEmployeeNum(Contract-1);
                department.setFullTimeEmployeeNum(fullTime+1);
                departmentRepo.save(department);
                System.out.println(department.getFullTimeEmployeeNum());
            }
            else if(preJobType.equals("Internship")&&newJobType.equals("Fulltime")){
                department.setInternEmployeeNum(Intern-1);
                department.setFullTimeEmployeeNum(fullTime+1);
                departmentRepo.save(department);
            }
            else if(preJobType.equals("Internship")&&newJobType.equals("Contract")){
                department.setInternEmployeeNum(Intern-1);
                department.setContractEmployeeNum(Contract+1);
                departmentRepo.save(department);
            }
            else if(preJobType.equals("Fulltime")&&newJobType.equals("Contract")){
                department.setContractEmployeeNum(Contract+1);
                department.setFullTimeEmployeeNum(fullTime-1);
                departmentRepo.save(department);
            }else if(preJobType.equals("Contract")&&newJobType.equals("Internship")){
                department.setContractEmployeeNum(Contract-1);
                department.setInternEmployeeNum(Intern+1);
                departmentRepo.save(department);
            }
            else if(preJobType.equals("Fulltime")&&newJobType.equals("Internship")){
                department.setFullTimeEmployeeNum(fullTime-1);
                department.setInternEmployeeNum(Intern+1);
                departmentRepo.save(department);
            }
            employee.setJobType(newEmployee.getJobType());
        }

        employeesRepo.save(employee);
        return ResponseEntity.ok(employee);
    }
}
