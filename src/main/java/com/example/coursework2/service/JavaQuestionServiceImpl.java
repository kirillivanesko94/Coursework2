package com.example.coursework2.service;

import com.example.coursework2.exception.QuestionNotFoundException;
import com.example.coursework2.model.Question;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class JavaQuestionServiceImpl implements QuestionService {
    private static final Set<Question> questionSet = new HashSet<>();

    static {
        questionSet.add(new Question("Какие примитивы бывают в Java?", "int,short,byte,long,double,float,char,boolean"));
    }

    @Override
    public Question add(String question, String answer) {
        Question question1 = new Question(question, answer);
        questionSet.add(question1);
        return question1;
    }

    @Override
    public Question add(Question question) {
        questionSet.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (questionSet.contains(question)) {
            questionSet.remove(question);
        } else {
            throw new QuestionNotFoundException(question);
        }
        return question;
    }

    @Override
    public Set<Question> getAll() {
        return questionSet;
    }

    @Override
    public Question getRandom() {
        Question[] questionsArray = questionSet.toArray(new Question[questionSet.size()]);
        Random r = new Random();
        int randomNumber = r.nextInt(questionSet.size());
        return questionsArray[randomNumber];

    }

}
