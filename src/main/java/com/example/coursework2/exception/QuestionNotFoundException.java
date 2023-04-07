package com.example.coursework2.exception;

import com.example.coursework2.model.Question;

public class QuestionNotFoundException extends RuntimeException {
    public QuestionNotFoundException(Question question) {
        super(question + " данный вопрос не найден в списке.");
    }
}
