package com.example.EMSbackend.Controllers;

import com.example.EMSbackend.Models.AdminModel;
import com.example.EMSbackend.Repository.AdminRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.util.Date;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminRepo adminRepo;

    public AdminController(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }

    @PostMapping
    public ResponseEntity SignUpAdmin(@RequestBody AdminModel adminValues) {
        boolean existUser = adminRepo.findByEmail(adminValues.getEmail()).isPresent();
        if(!existUser){
            String afterHashPassword = passwordHashing(adminValues.getPassword());
            System.out.println(adminValues.getPassword());
            if(afterHashPassword==null) return ResponseEntity.status(HttpStatus.CONFLICT).body(adminValues);
            else{
                adminValues.setPassword(afterHashPassword);
                adminValues.setCreatedAt(new Date());
                adminRepo.insert(adminValues);
                return ResponseEntity.status(HttpStatus.CREATED).body(adminValues);
            }
        }else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already Existed! "+adminValues.getEmail());
        }
    }

    @PostMapping("/login")
    public ResponseEntity loginAdmin(@RequestBody AdminModel adminLogin){
        Optional<AdminModel> user = adminRepo.findByEmail(adminLogin.getEmail());
        String storedPassword = user.get().getPassword();
        if(user.isPresent()){
            String HashPassword = passwordHashing(adminLogin.getPassword());
            if(HashPassword==null) return ResponseEntity.status(HttpStatus.CONFLICT).body(adminLogin);
            if(HashPassword.equals(storedPassword)) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
            } else return ResponseEntity.status(HttpStatus.CONFLICT).body("invalid login credentials");
        }else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email doesn't Existed! ");
        }
    }

    public static String passwordHashing(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            messageDigest.update(password.getBytes());
            byte[] result = messageDigest.digest();
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : result) {
                stringBuilder.append(String.format("%02x", b));
            }
            String hashPassword = String.valueOf(stringBuilder);
            return (hashPassword);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
