package com.controller;


import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello, Digital Library!";
    }
}
