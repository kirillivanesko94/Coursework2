package com.example.coursework2.exception;

public class NumberOfQuestionsException extends RuntimeException {
    public NumberOfQuestionsException(int amount) {
        super("Заправшиваемое вами количество вопросов - " + amount + " превышает количество доступных");
    }
}
