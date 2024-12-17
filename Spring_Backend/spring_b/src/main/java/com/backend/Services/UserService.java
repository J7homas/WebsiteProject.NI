package com.backend.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.Repositories.InnerUserRepository;

@Service
public class UserService{

    @Autowired
    private InnerUserRepository innerUserRepository;

    public void deleteUserById(Integer id){
        innerUserRepository.deleteById(id);
    }
    
    public void deleteAllUsers() {
        innerUserRepository.deleteAll();
    }
}
