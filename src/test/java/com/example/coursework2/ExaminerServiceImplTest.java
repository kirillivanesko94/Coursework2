package com.example.coursework2;

import com.example.coursework2.exception.NumberOfQuestionsException;
import com.example.coursework2.model.Question;
import com.example.coursework2.service.ExaminerServiceImpl;
import com.example.coursework2.service.JavaQuestionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {ExaminerServiceImpl.class})
@ExtendWith(SpringExtension.class)

public class ExaminerServiceImplTest {
    @Autowired
    ExaminerServiceImpl examinerService;
    @MockBean
    JavaQuestionServiceImpl javaQuestionService;
    private static final Question question1 = new Question("Какие примитивы бывают в Java?", "int,short,byte,long,double,float,char,boolean");
    private static final Question question2 = new Question("Кто молодец?", "Я молодец!");
    private static final Question question3 = new Question("Чему равен квадрат гипотенузы?", "Сумме квадратов катетов");
    private static final Set<Question> setQuestion = new HashSet<>();


    @BeforeEach
    public void setUp() {
        setQuestion.add(question1);
        setQuestion.add(question2);
        setQuestion.add(question3);
    }

    public static Stream<Arguments> getAmount() {
        return Stream.of(Arguments.of(2));
    }
    public static Stream<Arguments> getAmountForException() {
        return Stream.of(Arguments.of(10));
    }

    @ParameterizedTest
    @MethodSource("getAmount")
    void getQuestionsSuccess(int amount) {
        when(javaQuestionService.getAll()).thenReturn(setQuestion);

        when(javaQuestionService.getRandom()).thenReturn(question1).thenReturn(question2);
        Set<Question> actual = examinerService.getQuestions(amount);
        assertTrue(actual.contains(question1));
        assertTrue(actual.contains(question2));
        assertEquals(2, actual.size());
    }

    @ParameterizedTest
    @MethodSource("getAmountForException")
    void getQuestionException(int amount) {
             Exception exception = assertThrows(NumberOfQuestionsException.class,
                () -> examinerService.getQuestions(amount));
        String expectedMessage = "Заправшиваемое вами количество вопросов - " + amount + " превышает количество доступных";
        assertEquals(expectedMessage, exception.getMessage());
    }

}
