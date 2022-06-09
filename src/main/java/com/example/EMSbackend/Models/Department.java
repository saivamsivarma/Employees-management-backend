package com.example.EMSbackend.Models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Size;

@Document(collection = "Department")
public class Department {
    @Id
    @Field(name="_id")
    private ObjectId _id;

    @Field(name="Organization_id")
    private ObjectId Organization_id;

    @Size(min = 5, max = 30)
    @Field(name="Dept_name")
    private String Dept_name;
    @Field(name="FullTimeEmployeeNum")
    private Number FullTimeEmployeeNum;
    @Field(name="InternEmployeeNum")
    private Number InternEmployeeNum;
    @Field(name="contractEmployeeNum")
    private Number contractEmployeeNum;

    public Department(ObjectId _id, ObjectId Organization_id, String Dept_name,Number FullTimeEmployeeNum,Number InternEmployeeNum, Number contractEmployeeNum){
        this._id=_id;
        this.Organization_id= Organization_id;
        this.Dept_name=Dept_name;
        this.FullTimeEmployeeNum=FullTimeEmployeeNum;
        this.InternEmployeeNum=InternEmployeeNum;
        this.contractEmployeeNum=contractEmployeeNum;

    }


    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public void setOrganization_id(ObjectId organization_id) {
        this.Organization_id = organization_id;
    }

    public void setDept_name(String dept_name) {
        this.Dept_name = dept_name;
    }

    public void setFullTimeEmployeeNum(Number fullTimeEmployeeNum) {
        this.FullTimeEmployeeNum = fullTimeEmployeeNum;
    }

    public void setContractEmployeeNum(Number contractEmployeeNum) {
        this.contractEmployeeNum = contractEmployeeNum;
    }

    public void setInternEmployeeNum(Number internEmployeeNum) {
        this.InternEmployeeNum = internEmployeeNum;
    }

    public String get_id() {
        return _id.toHexString();
    }

    public String getOrganization_id() {
        return Organization_id.toHexString();
    }

    public String getDept_name() {
        return Dept_name;
    }

    public Number getContractEmployeeNum() {
        return contractEmployeeNum;
    }

    public Number getFullTimeEmployeeNum() {
        return FullTimeEmployeeNum;
    }

    public Number getInternEmployeeNum() {
        return InternEmployeeNum;
    }

}
