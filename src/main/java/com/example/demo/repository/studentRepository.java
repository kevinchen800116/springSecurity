package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.entity.student;

public interface studentRepository extends MongoRepository<student,String> {
        // 回傳Optional包裝過後的student物件
        Optional<student>findStudentByEmail(String email);
        student findStringStudentByEmail(String email);
}
