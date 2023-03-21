package com.example.demo.entity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

@Data
@Document
public class student {

    
    @Id
    private String id;

    private String name;
    @NonNull
    public String email;

    private Integer age;

    public student() {
    }
    public student(String name, String email, Integer age) {
        this.name=name;
        this.email=email;
        this.age=age;
    }
}
