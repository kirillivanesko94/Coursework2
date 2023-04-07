package com.example.coursework2.service;

import com.example.coursework2.model.Question;

import java.util.Set;

public interface QuestionService {
    Question add(String question, String answer);

    Question add(Question question);

    Question remove(Question question);

    Set<Question> getAll();

    Question getRandom();


}
