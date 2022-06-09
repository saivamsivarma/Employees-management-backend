package com.example.EMSbackend.Models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

@Document(collection = "Employees")
public class Employee {
    @Id
    @Field(name="_id")
    private ObjectId _id;
    @NotNull
    @Field(name="Dept_id")
    private ObjectId Dept_id;
    @Field(name="dept_name")
    private String dept_name;
    @Field(name="Employee_name")
    private String Employee_name;

    @Email
    @Field(name="Employee_email")
    private String Employee_email;
    @Size(min = 6,max=30)
    @Field(name="Role")
    private String Role;
    @Field(name="Experience")
    private Number Experience;

    @Field(name="Gender")
    private String Gender;
    @Field(name="Salary")
    private Number Salary;
    @Field(name="Data_of_join")
    private Date Data_of_join;
    @NotNull
    @Size(min =10,max=10)
    @Field(name="Contact")
    private BigInteger Contact;
    @Field(name="Org_id")
    private ObjectId Org_id;
    @Field(name="JobType")
    private String JobType;
    public Employee(ObjectId _id, ObjectId Dept_id, String dept_name,String Employee_name, String Employee_email, String Role, Number Experience,Number Salary, Date Data_of_join,BigInteger Contact, ObjectId Org_id,String JobType,String Gender){
        this._id=_id;
        this.Dept_id=Dept_id;
        this.dept_name=dept_name;
        this.Employee_name=Employee_name;
        this.Employee_email=Employee_email;
        this.Role=Role;
        this.Experience=Experience;
        this.Salary=Salary;
        this.Data_of_join=Data_of_join;
        this.Contact=Contact;
        this.Org_id=Org_id;
        this.JobType=JobType;
        this.Gender=Gender;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public void setDept_id(ObjectId dept_id) {
        this.Dept_id = dept_id;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public void setEmployee_name(String employee_name) {
        this.Employee_name = employee_name;
    }

    public void setEmployee_email(String employee_email) {
        this.Employee_email = employee_email;
    }

    public void setRole(String role) {
        this.Role = role;
    }

    public void setExperience(Number experience) {
        this.Experience = experience;
    }

    public void setSalary(Number salary) {
        this.Salary = salary;
    }

    public void setData_of_join(Date data_of_join) {
        this.Data_of_join = data_of_join;
    }

    public void setContact(BigInteger contact) {
        this.Contact = contact;
    }

    public void setOrg_id(ObjectId org_id) {
        this.Org_id = org_id;
    }

    public ObjectId getOrg_id() {
        return Org_id;
    }

    public void setGender(String Gender) {this.Gender=Gender;}

    public void setJobType(String jobType) {
        this.JobType = jobType;
    }

    public String get_id() {
        return _id.toHexString();
    }

    public String getDept_id() {
        return Dept_id.toHexString();
    }

    public String getDept_name() {
        return dept_name;
    }

    public String getEmployee_name() {
        return Employee_name;
    }

    public String getEmployee_email() {
        return Employee_email;
    }

    public String getRole() {
        return Role;
    }

    public Number getSalary() {
        return Salary;
    }


    public Date getData_of_join() {
        return Data_of_join;
    }

    public Number getExperience() {
        return Experience;
    }

    public Number getContact() {
        return Contact;
    }

    public String getJobType() {
        return JobType;
    }

    public String getGender() {
        return Gender;
    }
    public String getDOJ() {
        return new SimpleDateFormat("dd MMM yyyy").format(Data_of_join);
    }
}
