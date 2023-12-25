package com.mynotes.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/index")
public class IndexController {

    @GetMapping("/test")
    public ResponseEntity test(){
        System.out.println("In Index Controller Test Method.");
        return new ResponseEntity("Test Controller and method working", HttpStatus.OK);
    }
}
// END OF INDEX CONTROLLER CLASS.
