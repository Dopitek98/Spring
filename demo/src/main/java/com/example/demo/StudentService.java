package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void dodajNowegoStudenta(Student student) {
        Optional<Student> StudentEmail = studentRepository.znajdzStudentaPoEmailu(student.getEmail());
        if(StudentEmail.isPresent()){
            throw new IllegalStateException("zajety email");
        }
        studentRepository.save(student);
    }

    public void usunStudenta(Integer studentId) {
        boolean czyIstnieje = studentRepository.existsById(studentId);
        if(!czyIstnieje){
            throw new IllegalStateException("Id studenta "+studentId+" ,nie istnieje");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void aktualizajaStudenta(Integer id,String imie,String nazwisko,String email) {
        Student student = studentRepository.findById(id).orElseThrow(()->new IllegalStateException("Student z id "+id+" nie istnieje"));
        if(imie !=null && imie.length()>0 && !Objects.equals(student.getImie(),imie)){
            student.setImie(imie);
        }
        if(nazwisko!=null && nazwisko.length()>0 && !Objects.equals(student.getNazwisko(),nazwisko)){
            student.setNazwisko(nazwisko);
        }
        if (email!=null && email.length()>0 && !Objects.equals(student.getEmail(),email) && email.contains("@")){
            Optional<Student> studentOptional = studentRepository.znajdzStudentaPoEmailu(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("zajety email");
            }
            student.setEmail(email);
        }
    }
}
