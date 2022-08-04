package com.example.app.utils.listeners;

import com.example.app.models.other.Answer;
import com.example.app.repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

public class AnswerResetDatabaseTestExecutionListener extends AbstractTestExecutionListener {

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public void beforeTestMethod(TestContext testContext) {
        testContext.getApplicationContext()
                .getAutowireCapableBeanFactory()
                .autowireBean(this);
        String[] answerTexts = { "ans1", "ans2", "ans3", "ans4", "ans5" };
        for (String answerText : answerTexts) {
            Answer answer = new Answer();
            answer.setAnswerText(answerText);
            answerRepository.save(answer);
        }
    }

    @Override
    public void afterTestMethod(TestContext testContext) {
        answerRepository.deleteAll();
    }
    
}
