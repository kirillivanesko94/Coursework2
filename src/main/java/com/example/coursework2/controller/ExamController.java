package com.example.coursework2.controller;

import com.example.coursework2.exception.NumberOfQuestionsException;
import com.example.coursework2.model.Question;
import com.example.coursework2.service.ExaminerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("exam")
public class ExamController {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({NumberOfQuestionsException.class,})
    public String handleException(RuntimeException e) {
        return String.format("%s %s", HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
    ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("get")
    public Set<Question> getQuestion(@RequestParam("amount") int amount) {
        return examinerService.getQuestions(amount);
    }
}
