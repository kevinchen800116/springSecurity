package com.example.demo.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = ProductNotfoundException.class)
    public ResponseEntity<Object> exception(ProductNotfoundException exception) {
        return new ResponseEntity<>("product not fund", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = EmailExistException.class)
    public ResponseEntity<Object> exception2(EmailExistException exception) {
        return new ResponseEntity<>("<h1>Email已經存在!!</h1><button><a href='http://localhost:8080/api/v1/student/post'>返回</a></button>", 
        HttpStatus.valueOf(200));
    }
    @ExceptionHandler(value = testException.class)
	public ModelAndView exception(Exception ex,WebRequest request){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("errorSomething","Email已存在!!");
		modelAndView.setViewName("error");
		return modelAndView;
	}
}
