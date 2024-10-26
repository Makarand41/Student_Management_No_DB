package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Student;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private List<Student> students = new ArrayList<>();
    private int currentId = 1;

    // Create a new student
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        student.setId(currentId++);
        students.add(student);
        return student;
    }

    
    // Read all students
    @GetMapping
    public List<Student> readStudents() {
        return students;
    }
    
    
    
    // Update a student by ID
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable int id, @RequestBody Student updatedStudent) {
        for (Student student : students) {
            if (student.getId() == id) {
                student.setFirstName(updatedStudent.getFirstName());
                student.setLastName(updatedStudent.getLastName());
                return student;
            }
        }
        return null; // or throw an exception
    }

    
    // Delete a student by ID
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id) {
        students.removeIf(student -> student.getId() == id);
    }

    
 // Get a student by ID
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null; // or throw an exception if preferred
    }
    
    
 // Delete all students
    @DeleteMapping
    public void deleteAllStudents() {
        students.clear();
    }

    
 // Save all students
    @PostMapping("/saveAll")
    public List<Student> saveAllStudents(@RequestBody List<Student> newStudents) {
        for (Student student : newStudents) {
            student.setId(currentId++);
            students.add(student);
        }
        return students;
    }



    
 // Get the count of students
    @GetMapping("/count")
    public int countStudents() {
        return students.size();
    }

    
 // Check existence of a student by ID
    @GetMapping("/exists/{id}")
    public boolean exists(@PathVariable int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return true;
            }
        }
        return false; // Student does not exist
    }
}