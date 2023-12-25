package com.mynotes.services.auth;

import com.mynotes.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyCustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.loadUserByEmail(username);
        // Check If User Is Not Null:
        if(user == null){
           throw new UsernameNotFoundException("Unable To Load User");
        }
        return new MyCustomUserDetails(user);
    }
    // END OF LOAD USER BY USERNAME METHOD.
}
// END OF MY CUSTOM USER DETAILS SERVICE CLASS.
