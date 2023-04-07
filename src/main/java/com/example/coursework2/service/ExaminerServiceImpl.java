package com.example.coursework2.service;

import com.example.coursework2.exception.NumberOfQuestionsException;
import com.example.coursework2.model.Question;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    public Set<Question> getQuestions(int amount) {
        if (amount > questionService.getAll().size()) {
            throw new NumberOfQuestionsException(amount);
        }
        Set<Question> result = new HashSet<>();
        while(result.size() < amount ){
            result.add(questionService.getRandom());
        }

        return result;
    }
}
