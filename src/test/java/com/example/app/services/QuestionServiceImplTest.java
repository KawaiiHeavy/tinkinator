package com.example.app.services;

import com.example.app.models.Question;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionServiceImplTest {

    @Autowired
    private QuestionServiceImpl questionServiceImpl;

    @Test
    public void testAddOneQuestion() {
        Question question = new Question();
        question.setQuestionText("question1");

        Assert.assertEquals(question, questionServiceImpl.addQuestion(question));
    }

    @Test
    public void testDeleteOneQuestion() {
        Question question = new Question();
        question.setQuestionText("question2");

        questionServiceImpl.addQuestion(question);
        int size = questionServiceImpl.findAllQuestions().size();

        questionServiceImpl.deleteQuestion(question.getId());
        Assert.assertEquals(size - 1, questionServiceImpl.findAllQuestions().size());
    }

    @Test
    public void testUpdateNonModifiedQuestion() {
        Question question = new Question();
        question.setQuestionText("question3");

        question = questionServiceImpl.addQuestion(question);
        int size = questionServiceImpl.findAllQuestions().size();
        UUID prevQuestionId = question.getId();

        question = questionServiceImpl.updateQuestion(question);
        Assert.assertEquals(size, questionServiceImpl.findAllQuestions().size());
        Assert.assertEquals(prevQuestionId, question.getId());
    }

    @Test
    public void testUpdateModifiedQuestion(){

        Question question = new Question();
        question.setQuestionText("question4");

        question = questionServiceImpl.addQuestion(question);
        int size = questionServiceImpl.findAllQuestions().size();
        UUID prevQuestionId = question.getId();

        question.setQuestionText("question5");
        question = questionServiceImpl.updateQuestion(question);

        Assert.assertEquals(size, questionServiceImpl.findAllQuestions().size());
        Assert.assertEquals(prevQuestionId, question.getId());
    }

}
