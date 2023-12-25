package com.mynotes.services.auth;

import com.mynotes.models.User;
import com.mynotes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User loadUserByEmail(String email){
        return userRepository.getUserByEmail(email);
    }
    // END OF LOAD USER BY EMAIL.

    public List<String> doesEmailExist(String email){
        return userRepository.doesEmailExist(email);
    }
    // END OF CHECK IF EMAIL EXISTS SERVICE METHOD.

    public int signUpUser(String first_name, String last_name, String email, String password){
        return userRepository.signUpUser(first_name, last_name, email, password);
    }
    // END OF SIGN UP USER SERVICE METHOD.

}
// END OF UER SERVICE CLASS.