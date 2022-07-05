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
public class QuestionServiceTest {

    @Autowired
    private QuestionService questionService;

    @Test
    public void testAddOneQuestion() {
        Question question = new Question();
        question.setQuestionText("question1");

        Assert.assertEquals(question, questionService.addQuestion(question));
    }

    @Test
    public void testDeleteOneQuestion() {
        Question question = new Question();
        question.setQuestionText("question2");

        questionService.addQuestion(question);
        int size = questionService.findAllQuestions().size();

        questionService.deleteQuestion(question.getId());
        Assert.assertEquals(size - 1, questionService.findAllQuestions().size());
    }

    @Test
    public void testUpdateNonModifiedQuestion() {
        Question question = new Question();
        question.setQuestionText("question3");

        question = questionService.addQuestion(question);
        int size = questionService.findAllQuestions().size();
        UUID prevQuestionId = question.getId();

        question = questionService.updateQuestion(question);
        Assert.assertEquals(size, questionService.findAllQuestions().size());
        Assert.assertEquals(prevQuestionId, question.getId());
    }

    @Test
    public void testUpdateModifiedQuestion(){

        Question question = new Question();
        question.setQuestionText("question4");

        question = questionService.addQuestion(question);
        int size = questionService.findAllQuestions().size();
        UUID prevQuestionId = question.getId();

        question.setQuestionText("question5");
        question = questionService.updateQuestion(question);

        Assert.assertEquals(size, questionService.findAllQuestions().size());
        Assert.assertEquals(prevQuestionId, question.getId());
    }

}
