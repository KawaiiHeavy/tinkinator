package com.example.app.utils.listeners;

import com.example.app.models.question.Question;
import com.example.app.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

public class QuestionResetDatabaseTestExecutionListener extends AbstractTestExecutionListener {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public void beforeTestMethod(TestContext testContext) {
        testContext.getApplicationContext()
                .getAutowireCapableBeanFactory()
                .autowireBean(this);
        String[] questionTexts = { "quest1", "quest2", "quest3", "quest4", "quest5" };
        for (String questionText : questionTexts) {
            Question question = new Question();
            question.setQuestionText(questionText);
            questionRepository.save(question);
        }
    }

    @Override
    public void afterTestMethod(TestContext testContext) {
        questionRepository.deleteAll();
    }
    
}
