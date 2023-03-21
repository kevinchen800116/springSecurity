package com.example.demo.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.student;
import com.example.demo.service.studentService;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class indexControll {
    public studentService service;

    // @Autowired
    public indexControll(studentService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ModelAndView index(){
        System.out.println("home查看");
        // DB取值查詢
        Iterable<student> user = service.getStudent();
        // 創建對應的html頁面
        ModelAndView modelAndView=new ModelAndView("index");
        // 將查詢的值塞入頁面內
        modelAndView.addObject("user", user);
        return modelAndView;
    }
    @GetMapping("/home")
    public ModelAndView home(){
        System.out.println("home查看");
        // DB取值查詢
        Iterable<student> user = service.getStudent();
        // 創建對應的html頁面
        ModelAndView modelAndView=new ModelAndView("index");
        // 將查詢的值塞入頁面內
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView=new ModelAndView("login");
        return modelAndView;
    }

    @GetMapping(value="/register")
    public ModelAndView register() {
        // 創建空的物件給前端頁面
        student emptyStudent = new student();
        ModelAndView modelAndView=new ModelAndView("register");
        modelAndView.addObject("empty", emptyStudent);
        return modelAndView;
    }
    @PostMapping("/register")
    public ModelAndView PostRegister(@RequestParam Map<String ,String>student) {
        // System.out.println(student);
        // 取得前端資料並存入DB
        System.out.println(student);
        String Name=student.get("name");
        String Email=student.get("email");
        int Age=Integer.valueOf(student.get("age")).intValue();
        student stud=new student(Name,Email,Age);

        service.addNewStudent(stud);
        
        // // 跳轉網址
        String url ="/home";
        return new ModelAndView("redirect:" + url);
    }
    

}
