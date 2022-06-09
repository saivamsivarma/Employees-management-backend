package com.example.EMSbackend.Models;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import com.example.EMSbackend.Models.Membership;
import javax.validation.constraints.Size;

@Document(collection = "Organization")
public class OrganizationModel {
    @Id
    @Field(name = "_id")
    private ObjectId _id;

    @Size(min=4, max=30)
    @Field(name="Organization_Name")
    private String Organization_Name;

    @Size(min=4, max=30)
    @Field(name="Organization_code")
    private String Organization_code;

    @Field(name="Membership")
    private Membership MembershipType;
    @Field(name="Size")
    private Number Size;
    @Field(name="No_ofAdmin")
    private Number No_ofAdmin;
    @Field(name="No_ofDepart")
    private Number No_ofDepart;
    public OrganizationModel(ObjectId _id, String Organization_Name, String Organization_code, Membership MembershipType,Number Size,Number No_ofAdmin,Number No_ofDepart){
        this._id=_id;
        this.Organization_Name=Organization_Name;
        this.Organization_code=Organization_code;
        this.MembershipType=MembershipType;
        this.Size=Size;
        this.No_ofAdmin=No_ofAdmin;
        this.No_ofDepart=No_ofDepart;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public void setOrganization_Name(String organization_Name) {
        this.Organization_Name = organization_Name;
    }

    public void setOrganization_code(String organization_code) {
        this.Organization_code = organization_code;
    }

    public void setMembershipType(Membership membershipType) {
        this.MembershipType = membershipType;
    }

    public void setSize(Number size) {
        this.Size = size;
    }

    public void setNo_ofAdmin(Number no_ofAdmin) {
        this.No_ofAdmin = no_ofAdmin;
    }

    public void setNo_ofDepart(Number no_ofDepart) {
        this.No_ofDepart = no_ofDepart;
    }

    public String get_id() {
        return _id.toHexString();
    }
    public String getOrganization_Name() {
        return Organization_Name;
    }

    public String getOrganization_code() {
        return Organization_code;
    }

    public Membership getMembershipType() {
        return MembershipType;
    }

    public Number getSize() {
        return Size;
    }

    public Number getNo_ofAdmin() {
        return No_ofAdmin;
    }

    public Number getNo_ofDepart() {
        return No_ofDepart;
    }
}
