package com.example.RestIO.Models;

public class Student {
    public final Long id;
    public final String name, number, grupa;

    public Student(Long id, String name, String number, String grupa) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.grupa = grupa;
    }
}
