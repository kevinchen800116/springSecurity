package com.example.demo.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.student;
import com.example.demo.repository.studentRepository;
@Service
public class kevinUserDetailsService implements UserDetailsService{
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    studentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String Email) throws UsernameNotFoundException {
        System.out.println("開始查詢DB");
         // 取得 DB 裡的帳號
        Optional<student> studentOptional=studentRepository.findStudentByEmail(Email);
        student dbUser = studentRepository.findStringStudentByEmail(Email);
        if(studentOptional.isPresent()){
            
            UserDetails veraUser= User.builder()
            .username(dbUser.getEmail())
            .password(passwordEncoder.encode("1234"))
            .roles("STUDENT") //ROLE_STUDENT
            .build();
            System.out.println("我是查詢DB的user"+veraUser);
            return veraUser;
        }else{
            throw new UsernameNotFoundException("User not found by email: " + dbUser.getEmail());
        }

    }
    
}
