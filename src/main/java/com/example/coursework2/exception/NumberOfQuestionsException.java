package com.example.coursework2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NumberOfQuestionsException extends RuntimeException{
    public NumberOfQuestionsException(int amount) {
        super("Заправшиваемое вами количество вопросов - " + amount + " превышает количество доступных");
    }
}