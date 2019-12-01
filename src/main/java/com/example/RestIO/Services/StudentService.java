package com.example.RestIO.Services;

import com.example.RestIO.Models.AddStudent;
import com.example.RestIO.Models.Student;
import com.example.RestIO.db.StudentRepository;
import com.example.RestIO.db.StudentRow;
import io.vavr.collection.List;
import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class StudentService {
    //private List<Student> studenci = List.empty();
    final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository=repository;
        }
        public List<Student> getStudents(){
            return List.ofAll(this.repository.findAll()).map(StudentRow::toStudent);
        }

        public Student getStudent(Long id) {
        StudentRow st = (repository.findById(id)).get();
        return List.of(st).map(StudentRow::toStudent).single();
        }
        public Long addStudent(final AddStudent newStudent){
            StudentRow student = new StudentRow(newStudent.name,newStudent.number,newStudent.grupa);
            repository.save(student);
            return student.getId();
        }

/*
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
*/
    private Long newId()
    {
        Long uuid = new Random().nextLong();
        return uuid;
    }

}