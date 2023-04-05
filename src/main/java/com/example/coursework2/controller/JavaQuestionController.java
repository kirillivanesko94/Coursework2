package com.example.coursework2.controller;

import com.example.coursework2.model.Question;
import com.example.coursework2.service.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("exam/java")
public class JavaQuestionController {
    private final QuestionService questionService;

    public JavaQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(path = "add")
    public Question add(@RequestParam("question") String question, @RequestParam("answer") String answer) {
        return questionService.add(question, answer);
    }
    @GetMapping(path = "remove")
    public Question remove(@RequestParam("question") String question, @RequestParam("answer") String answer) {
        Question param = new Question(question,answer);
        return questionService.remove(param);
    }
    @GetMapping()
    public Set<Question> getAll(){
        return questionService.getAll();
    }


}
