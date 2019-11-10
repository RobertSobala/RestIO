package com.example.RestIO.Services;
import com.example.RestIO.Models.AddStudent;
import com.example.RestIO.Models.Student;
import io.vavr.collection.List;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class StudentServiceTest extends StudentService {
    @Test
    public void getEmptyList()
    {
        final StudentService studentService = new StudentService();
        Assert.assertEquals(studentService.getStudents(), List.empty());
    }


    @Test
    public void addStudent()
    {
        final StudentService studentService = new StudentService();
        Student added = studentService.addStudent(new AddStudent("Student1","1-2-3","IP"));
        Assert.assertTrue(added.number=="1-2-3");
    }

    @Test
    public void addStudentIsReturned()
    {
        final StudentService studentService = new StudentService();
        Student added = studentService.addStudent(new AddStudent("Student2","2","IPpp"));
        final List<Student> students = studentService.getStudents();
        Assert.assertTrue(students.contains(added));
    }

    @Test
    public void addStudentHasNewId()
    {
        final StudentService studentService = new StudentService();
        Student added1 = studentService.addStudent(new AddStudent("Student2","2","IPpp"));
        Student added2 = studentService.addStudent(new AddStudent("Student3","3","IPpp"));
        assertEquals(2,studentService.getStudents().size());
    }
}