package com.backend.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import com.backend.Models.UserModel;
import com.backend.Repositories.InnerUserRepository;
import com.backend.Services.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    
    @Autowired
    InnerUserRepository innerUserRepository;
    
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<UserModel> getAllUsers() {
        return innerUserRepository.findAll();
    }

    @GetMapping("/users/id/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable Integer id) {
        Optional<UserModel> user = innerUserRepository.findById(id);

        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<UserModel> postCreateUser(@RequestBody UserModel userModel) {
        innerUserRepository.save(userModel);
        return new ResponseEntity<UserModel>(HttpStatus.CREATED);
    }

    @PutMapping("/users/id/{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable("id") Integer id, @RequestBody UserModel userModel) {
        Optional<UserModel> userData = innerUserRepository.findById(id);

        if (userData.isPresent()) {
            UserModel _user = userData.get();

            _user.setFirst_Name(userModel.getFirst_Name());
            _user.setLast_Name(userModel.getLast_Name());

            return new ResponseEntity<>(innerUserRepository.save(_user), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Integer id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>("User has been deleted.", HttpStatus.OK);
    }

    @DeleteMapping("/users")
    public ResponseEntity<String> deleteAllUsers() {
        userService.deleteAllUsers();
        return new ResponseEntity<>("All users have been deleted.", HttpStatus.NO_CONTENT);
    }
}
