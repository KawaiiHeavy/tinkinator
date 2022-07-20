package com.example.app.utils.listeners;

import com.example.app.models.Solution;
import com.example.app.repositories.AnswerRepository;
import com.example.app.repositories.SolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

public class SolutionResetDatabaseTestExecutionListener extends AbstractTestExecutionListener {

    @Autowired
    private SolutionRepository solutionRepository;
    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public void beforeTestMethod(TestContext testContext) {
        testContext.getApplicationContext()
                .getAutowireCapableBeanFactory()
                .autowireBean(this);
        String[] solutionTexts = { "sol1", "sol2", "sol3", "sol4", "sol5" };
        for (String solutionText : solutionTexts) {
            Solution solution = new Solution();
            solution.setSolutionText(solutionText);
            solutionRepository.save(solution);
        }
    }

    @Override
    public void afterTestMethod(TestContext testContext) {
        answerRepository.deleteAll();
        solutionRepository.deleteAll();
    }

}
