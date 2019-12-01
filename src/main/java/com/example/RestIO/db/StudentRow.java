package com.example.RestIO.db;

import com.example.RestIO.Models.Student;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StudentRow {
    @Id
    private long id;
    private String name;
    private String number;
    private String group;

    public StudentRow(String name, String number, String group) {
        this.name = name;
        this.number = number;
        this.group = group;
    }

    public Student toStudent()
    {
        return new Student(
                this.getId(),
                this.getName(),
                this.getNumber(),
                this.getGroup());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
