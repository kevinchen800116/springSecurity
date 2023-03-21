package com.example.demo.controller;

import java.util.List;
import java.util.Map;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.student;
import com.example.demo.service.studentService;


@RestController
@RequestMapping(path = "/api/v1/student")
public class studentController {
    private final studentService studentService;

    // @Autowired
    public studentController(studentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping
    // 將此getStudent的方法封裝至studentService 層
    public List<student> getStudent(){
        return studentService.getStudent();
    }
    @GetMapping("/deleteAll")
    public String delStudent(){
        return studentService.delStudent();
    }
    @DeleteMapping("/del")
    public void deleteStudent(@RequestBody String email){
        // System.out.println(email);
        studentService.deleteSpecStudent(email);
    }
    @GetMapping("/add")
    public ModelAndView registerGetNewStudent(){
        System.out.println("student查看");
        // DB取值查詢
        Iterable<student> user = studentService.getStudent();
        // 創建對應的html頁面
        ModelAndView modelAndView=new ModelAndView("test");
        // 將查詢的值塞入頁面內
        modelAndView.addObject("user", user);
        // 創建空的物件給前端頁面
        student emptyStudent = new student();
        modelAndView.addObject("empty", emptyStudent);
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView registerNewStudent(@RequestParam Map<String ,String>student){
        // System.out.println(student);
        // 取得前端資料並存入DB
        System.out.println(student);
        String Name=student.get("name");
        String Email=student.get("email");
        int Age=Integer.valueOf(student.get("age")).intValue();
        student stud=new student(Name,Email,Age);

        studentService.addNewStudent(stud);
        
        // try{
        //     studentService.addNewStudent(stud);
        // }catch(Exception exception){
        //     System.out.println(exception.getMessage());
        // }
        // // 跳轉網址
        String url ="/api/v1/student/add";
        return new ModelAndView("redirect:" + url);
    }
    
    @GetMapping("/update/{email}")
    public ModelAndView GetUpdateStudent(@PathVariable String email) {
        student student=studentService.getStudent(email);
        ModelAndView modelAndView=new ModelAndView("update");
        modelAndView.addObject("student", student);
        return modelAndView;
    }

    @PutMapping("/updateStudent")
    public void updateStudent(@RequestBody Map<String ,String>student) {
        System.out.println("put測試");
        System.out.println(student);
        String Name=student.get("name");
        String Email=student.get("email");
        int Age=Integer.valueOf(student.get("age")).intValue();

        studentService.update(Name,Age,Email);
    }

    // @PostMapping("/post")
    // public String AjaxRegisterNewStudent(@RequestBody String data){
    //     System.out.println(data.getClass().getSimpleName());
    //     System.out.println(data);
        
    //     // student newStudent=new student("kevin","email",12);
    //     // studentService.addNewStudent(student);
    //     return "成功";
    // }

}
