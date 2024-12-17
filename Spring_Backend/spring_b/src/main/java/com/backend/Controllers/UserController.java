package com.backend.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import com.backend.Models.UserModel;
import com.backend.Repositories.InnerUserRepository;
import com.backend.Services.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@Controller
public class UserController {
    @Autowired
    InnerUserRepository innerUserRepository;
    UserService userService;
    
    @GetMapping("/users")
    public List<UserModel> getAllUsers(){
        //repository.findAll()
        return innerUserRepository.findAll();
    }

    @GetMapping("/users/id/{id}")
    public UserModel getUserById(int id){
        Optional<UserModel> user = innerUserRepository.findById(id);

        if(user.isEmpty()){
            System.out.println("User isn't real.");
        }

        return user.get();
    }

    @PostMapping("/users")
    public String postCreateUser(@RequestBody UserModel userModel){
        innerUserRepository.save(userModel);
        return "User created.";
    }
    
    @PutMapping("/users/id/{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable("id") Integer id, UserModel userModel){
        Optional<UserModel> userData = innerUserRepository.findById(id);

        if(userData.isPresent()){
            UserModel _user = userData.get();

            _user.setFirst_Name(_user.getFirst_Name());
            _user.setLast_Name(_user.getLast_Name());

            return new ResponseEntity<>(innerUserRepository.save(_user), HttpStatusCode.valueOf(200));
        }
        return new ResponseEntity<>(HttpStatusCode.valueOf(500));
    }

    @DeleteMapping("/users/{id}")
    public String deleteUserById(@PathVariable Integer id) {
        userService.deleteUserById(id);

        return "User has been deleted.";
    }

    @DeleteMapping("/users")
    public String deleteAllUsers() {
        userService.deleteAllUsers();

        return "All users have been deleted.";
    }
}
