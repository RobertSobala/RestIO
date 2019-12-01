package com.example.RestIO.Controllers;

import com.example.RestIO.Models.User;
import com.example.RestIO.Services.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
@RestController
@RequestMapping("/punkty")
public class PunktyController {
    private final StudentService service;
    private List<User> users=new CopyOnWriteArrayList();
    public PunktyController(StudentService service) {
        this.service = service;
        this.users.add(new User("Robert", "Sobala"));
        this.users.add(new User("Kamil", "Adamus"));
        this.users.add(new User("Kamil", "Rogowski"));
    }
    @GetMapping
    @RequestMapping("/users")
    public List<User> getUsers()
    {
        return this.users;
    }

    @PostMapping
    @RequestMapping({"/users/add"})
    public String addUser(@RequestBody User user) {
        this.users.add(user);
        return "Dodano pomyslnie";
    }

}
