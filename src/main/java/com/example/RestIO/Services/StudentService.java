package com.example.RestIO.Services;

import com.example.RestIO.Models.AddStudent;
import com.example.RestIO.Models.Student;
import io.vavr.collection.List;

import java.util.Random;

public class StudentService {
    private List<Student> studenci = List.empty();
    public List<Student> getStudents()
    {
        return this.studenci;
    }
    public Student getStudent(Long id)
    {
        Student student = studenci.distinctBy((s)->s.id==id).single();
        return student;
    }
    public Student addStudent(AddStudent nowy)
    {
        Student student = new Student(newId(),nowy.name,nowy.number,nowy.grupa);
        studenci = studenci.append(student);
        return student;
    }

    private Long newId()
    {
        Long uuid = new Random().nextLong();
        return uuid;
    }

}