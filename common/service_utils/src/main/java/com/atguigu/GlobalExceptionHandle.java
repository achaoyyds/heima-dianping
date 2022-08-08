package com.atguigu;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(Exception.class)
    public R exception(Exception e) {
        return R.fail(e.getMessage());
    }

    @ExceptionHandler(CustomException.class)
    public R exception(CustomException e) {
        return R.fail(e.getMessage());
    }
}
