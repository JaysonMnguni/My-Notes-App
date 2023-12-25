package com.mynotes.dto.responses;

import com.mynotes.services.auth.MyCustomUserDetails;

public class AuthResponse {

    private MyCustomUserDetails myCustomUserDetails;
    private String token;

    public AuthResponse(){

    }
    // END OF NO ARGS CONSTRUCTOR.

    public AuthResponse(String token, MyCustomUserDetails myCustomUserDetails){
        this.token = token;
        this.myCustomUserDetails = myCustomUserDetails;
    }

    public int getUserId(){
        return this.myCustomUserDetails.getUserId();
    }

    public String getUsername(){
        // This Will Return Users Email:
        return this.myCustomUserDetails.getUsername();
    }

    public String getFirstName(){
        return this.myCustomUserDetails.getFirstName();
    }

    public String getLastName(){
        return this.myCustomUserDetails.getLastName();
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
// END OF AUTH RESPONSE CLASS.
