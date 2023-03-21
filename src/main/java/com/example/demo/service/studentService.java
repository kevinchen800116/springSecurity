package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.student;
import com.example.demo.exception.testException;
import com.example.demo.repository.studentRepository;

@Service
public class studentService {
    private final studentRepository studentRep;

    public studentService(studentRepository studentRep) {
        this.studentRep = studentRep;
    }

    public student getStudent(String email) {
        student student=studentRep.findStringStudentByEmail(email);
        return student;
    }
    public List<student> getStudent(){
        return studentRep.findAll();
    }

    public String delStudent(){
        studentRep.deleteAll();
        return "已經刪除了";
    }
    public void deleteSpecStudent(String email) {
        student student=studentRep.findStringStudentByEmail(email);
        // System.out.println(student);
        try {
            studentRep.deleteById(student.getId());
            System.out.println("刪除成功!!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    // public String addStudent(){
    //     student maxWang= new student(
    //                             "maxWang",
    //                             "maxWang@gmail.com",
    //                             31
    //                         );
    //     studentRep.save(maxWang);
    //     return "已經新增了";
    // }
    // post方法 新增自己想要的user
    public void addNewStudent(student stu){
        Optional<student> studentOptional=studentRep.findStudentByEmail(stu.getEmail());
        if(studentOptional.isPresent()){
            // throw new IllegalStateException("email存在");
            // throw new EmailExistException();
            throw new  testException();
            // System.out.println("email已存在");
        }else{
            System.out.println("存入DB");
            studentRep.save(stu);
            System.out.println(stu);
        }

    }

    public void update(String Name,int Age,String Email) {
        student updateStudent=studentRep.findStringStudentByEmail(Email);
        String id=updateStudent.getId();
        //有id就是修改，没有id就是保存
        updateStudent.setId(id);
        updateStudent.setName(Name);
        updateStudent.setAge(Age);
        // System.out.println(updateStudent);
        studentRep.save(updateStudent);
    }
}
