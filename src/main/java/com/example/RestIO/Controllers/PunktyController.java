package com.example.RestIO.Controllers;

import com.example.RestIO.Models.Score;
import com.example.RestIO.Services.StudentService;
import com.example.RestIO.Models.Student;
import com.example.RestIO.Models.User;
import com.example.RestIO.tools.NoStudentException;
import org.springframework.http.MediaType;
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

    @PostMapping(value = "/students/{id}/number/{number}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Student setNumber(@PathVariable long id, @PathVariable String number)
    {
        return this.service.changeNumber(id, number).orElseThrow(
                () -> new IllegalArgumentException("Student oid: " + id + " does not exist")    );
    }

    @PostMapping("/students/{id}/scores")
    public int addScore(@RequestBody Score score, @PathVariable("id") long id)
    {
        return this.service.addScore(id, score).orElseThrow(()->new NoStudentException( id ));
    }

}
