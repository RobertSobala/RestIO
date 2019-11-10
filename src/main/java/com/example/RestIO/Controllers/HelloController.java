package com.example.RestIO.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String GetHello() { return "Hello " + LocalDateTime.now();}
}
