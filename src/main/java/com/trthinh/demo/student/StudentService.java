package com.trthinh.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAllByOrderByIdAsc();
    }

    public void addNewStudent(Student student){
        Optional<Student> studentPresent = studentRepository.findStudentByEmail(student.getEmail());
        if(studentPresent.isPresent()){
            throw new IllegalStateException("Email has been taken.");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        Optional<Student> studentPresent = studentRepository.findById(id);
        if(studentPresent.isEmpty()){
            throw new IllegalStateException("Student id "+id+" has not existed.");
        }
        studentRepository.delete(studentPresent.get());
    }

    @Transactional
    public Student updateStudent(Student student) {

        // validate id
        Student newStudent = studentRepository.findById(student.getId()).orElseThrow(()->new IllegalStateException("Student id "+student.getId()+" does not exist."));

        // validate name
        var name = student.getName();
        name.trim();
        for (String word : Arrays.stream(name.split(" "))
                        .map((word) -> word.substring(0,1))
                        .toList()) {
            if(!word.equals(word.toUpperCase()))
                throw new IllegalStateException("First letter of each word must be uppercased.");
        }
        newStudent.setName(student.getName());

        // validate email
        Optional<Student> studentPresent = studentRepository.findStudentByEmail(student.getEmail());
        if(studentPresent.isPresent()){
            throw new IllegalStateException("Email has been taken.");
        }
        newStudent.setEmail(student.getEmail());

        newStudent.setDob(student.getDob());

        //studentRepository.save(newStudent);
        return newStudent;
    }
}
