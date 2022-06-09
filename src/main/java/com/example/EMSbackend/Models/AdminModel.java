package com.example.EMSbackend.Models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.*;
import java.util.Date;

@Document(collection = "Admins")
public class AdminModel {
    @Id
    @Field(name = "_id")
    private ObjectId _id;

    @Size(min=2, max=30)
    @Field(name = " Name")
    private String Name;
    @Email
    @Field(name="Email")
    private String Email;
    @Field(name="Password")
    private String Password;
    @Field(name="Organization_id")
    private ObjectId Organization_id;
    @Field(name="createdAt")
    private Date createdAt;
    public AdminModel(ObjectId _id,String Name, String Email, String Password, ObjectId Organization_id, Date createdAt){
        this._id=_id;
        this.Name=Name;
        this.Email=Email;
        this.Password=Password;
        this.Organization_id=Organization_id;
        this.createdAt=createdAt;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public void setOrganization_id(ObjectId organization_id) {
        this.Organization_id = organization_id;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String get_id() {
        return _id.toHexString();
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public String getOrganization_id() {
        return Organization_id.toHexString();
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
