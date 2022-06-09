package com.example.EMSbackend.Controllers;

import com.example.EMSbackend.Models.Membership;
import com.example.EMSbackend.Models.OrganizationModel;
import com.example.EMSbackend.Repository.OrganizationRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.EMSbackend.Models.Membership.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/Organization")
public class OrganizationController {
    private final OrganizationRepo organizationRepo;

    public OrganizationController(OrganizationRepo organizationRepo) {
        this.organizationRepo = organizationRepo;
    }
    @PostMapping("/")
    public ResponseEntity createOrganization(@RequestBody OrganizationModel organization ){
        Boolean organizationCodeCheck = organizationRepo.findBranch_code(organization.getOrganization_code()).isPresent();
        try{
            if(!organizationCodeCheck){
                if(organization.getMembershipType()== Basic){
                    organization.setSize(100);
                    organization.setNo_ofAdmin(1);
                    organization.setNo_ofDepart(10);
                }
                organizationRepo.insert(organization);
                return ResponseEntity.ok(organization);
            }
            else return ResponseEntity.status(404).body("Organization is present with this Branch code");
        }catch (Exception e){
            return ResponseEntity.status(404).body(e);
        }
    }

    @PutMapping("/{_id}/{membership}")
    public ResponseEntity updateMembership(@PathVariable String _id, @PathVariable String membership ){
       OrganizationModel organization =  organizationRepo.findBy_id(_id);
       int Size = (int) organization.getSize();
       int No_ofAdmin = (int) organization.getNo_ofAdmin();
       int No_ofDepart = (int) organization.getNo_ofDepart();
            String preMembership = String.valueOf(organization.getMembershipType());

            if(!preMembership.equals("Elite")&&membership.equals("Elite")){
                Size+=500;
                No_ofAdmin+=3;
                No_ofDepart+=10;
                organization.setMembershipType(Membership.Elite);
            }
            else if (!preMembership.equals("Pro")&&membership.equals("Pro")){
                Size+=1500;
                No_ofAdmin+=5;
                No_ofDepart+=25;
                organization.setMembershipType(Membership.Pro);
            }
            organization.setSize(Size);
            organization.setNo_ofAdmin(No_ofAdmin);
            organization.setNo_ofDepart(No_ofDepart);
            organizationRepo.save(organization);
            return ResponseEntity.status(HttpStatus.CREATED).body(organization);
    }
     @GetMapping("/{_id}")
    public ResponseEntity getOrganizationDetails(@PathVariable String _id){
        OrganizationModel organization = organizationRepo.findBy_id(_id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(organization);
     }
}
