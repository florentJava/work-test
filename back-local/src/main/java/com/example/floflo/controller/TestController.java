package com.example.floflo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/test")
public class TestController {


    @GetMapping("")
    public String getMethodName() {
        return "Hello toto";
    }
    
    
}
