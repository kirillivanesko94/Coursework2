package com.example.coursework2;

import com.example.coursework2.exception.QuestionNotFoundException;
import com.example.coursework2.model.Question;
import com.example.coursework2.service.JavaQuestionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionServiceTest {
    JavaQuestionServiceImpl javaQuestionService;
    private static final Question question1 = new Question("Какие примитивы бывают в Java?", "int,short,byte,long,double,float,char,boolean");
    private static final Question question2 = new Question("Кто молодец?", "Я молодец!");

    @BeforeEach
    public void setUp() {
        javaQuestionService = new JavaQuestionServiceImpl();
        javaQuestionService.add(question1);
        javaQuestionService.add(question2);
    }

    public static Stream<Arguments> getQuestion() {
        return Stream.of(Arguments.of(question1));
    }

    @Test
    void checkAddSuccess() {
        Set<Question> actual = javaQuestionService.getAll();
        int sizeBefore = actual.size();

        Question question3 = new Question("Чему равен квадрат гипотенузы?", "Сумме квадратов катетов");
        javaQuestionService.add(question3);

        assertTrue(actual.contains(question3));
        assertEquals(sizeBefore + 1, actual.size());
    }

    @Test
    void checkGetAllSuccess() {
        Set<Question> actual = javaQuestionService.getAll();

        Set<Question> expected = new HashSet<>();
        expected.add(question1);
        expected.add(question2);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("getQuestion")
    void checkRemoveSuccess(Question question) {
        Set<Question> actual = javaQuestionService.getAll();

        javaQuestionService.remove(question);

        assertEquals(1, actual.size());
        assertFalse(actual.contains(question));
    }

    @Test
    void checkRemoveException() {
        Question question = new Question("Hello?", "Hello");
        Exception exception = assertThrows(QuestionNotFoundException.class,
                () -> javaQuestionService.remove(question));
        String expectedMessage = question + " данный вопрос не найден в списке.";
        assertEquals(expectedMessage, exception.getMessage());

    }

    @Test
    void checkGetRandomSuccess() {
        Set<Question> actual = new HashSet<>();
        int count = 0;
        int limit = javaQuestionService.getAll().size() * 50;
        while (count < limit && actual.size() < javaQuestionService.getAll().size()) {
            count++;
            actual.add(javaQuestionService.getRandom());
        }
        assertEquals(javaQuestionService.getAll().size(), actual.size());
    }


}
