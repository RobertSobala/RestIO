package com.example.RestIO.Services;

import com.example.RestIO.Models.AddStudent;
import com.example.RestIO.Models.Score;
import com.example.RestIO.Models.Student;
import com.example.RestIO.db.StudentRepository;
import com.example.RestIO.db.StudentRow;
import com.example.RestIO.db.repositories.ScoreRepository;
import com.example.RestIO.db.repositories.ScoreRow;
import io.vavr.collection.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Random;
@Service
public class StudentService {
    //private List<Student> studenci = List.empty();
    final StudentRepository repository;
    final ScoreRepository scoreRepo;

    @Autowired
    public StudentService(ScoreRepository scoreRepository, StudentRepository repository) {
        this.repository=repository;
        scoreRepo = scoreRepository;
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
    //@Transactional
    public Optional<Student> changeNumber(long studentId, String newNumber) {
        final Optional<StudentRow> student =
                this.repository.findById(studentId);
        return student.map(c -> {
            c.setNumber(newNumber);
            repository.save(c);
            return c.toStudent();
        });
    }
    @Transactional
    public Optional<Integer>  addScore(final long studentId, final Score score)
    {
        final Optional<StudentRow> student = this.repository.findById(studentId);

        return student.map(c->{int existingScore=List.ofAll(c.getScores()).foldLeft(0,(p,s)->p+s.getScore());
            final ScoreRow newScore=new ScoreRow(score.score,score.comment,c);
            this.scoreRepo.save(newScore);
            return existingScore+score.score;}
        );
    }

}