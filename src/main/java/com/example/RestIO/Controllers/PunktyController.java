package com.example.RestIO.Controllers;

import com.example.RestIO.Models.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
@RestController
@RequestMapping("/punkty")
public class PunktyController {
    private List<User> users=new CopyOnWriteArrayList();
    public PunktyController() {
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
