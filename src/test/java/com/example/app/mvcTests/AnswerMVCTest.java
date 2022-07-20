package com.example.app.mvcTests;

import com.example.app.models.Answer;
import com.example.app.repositories.AnswerRepository;
import com.example.app.utils.listeners.AnswerResetDatabaseTestExecutionListener;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestExecutionListeners(mergeMode =
        TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS,
        listeners = {AnswerResetDatabaseTestExecutionListener.class}
)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AnswerMVCTest {

    @LocalServerPort
    private Integer port;
    @Autowired
    private AnswerRepository answerRepository;

    private final String rootPath = "http://localhost:";
    private final String endpoint = "/api/answer/";

    @Autowired
    public MockMvc mvc;

    @Test
    public void accessApplication() {
        Assert.assertEquals(5, answerRepository.findAll().size());
    }

    @Test
    public void addAnswerTest() throws Exception {
        JSONObject answer = new JSONObject();
        answer.put("answerText", "ans");
        mvc.perform(
                post(rootPath + port + endpoint + "add/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(answer.toString())
                        .characterEncoding("utf-8")).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.answerText").value("ans"));
    }

    @Test
    public void deleteAnswerTest() throws Exception {
        int cnt = answerRepository.findAll().size();
        UUID answerId = answerRepository.findAll().get(0).getId();
        mvc.perform(
                delete(rootPath + port + endpoint + "delete/" + answerId))
                .andExpect(status().isOk());
        Assert.assertEquals(cnt - 1, answerRepository.findAll().size());
    }

    @Test
    public void updateAnswerTest() throws Exception {

        List<Answer> answers = answerRepository.findAll();
        Answer answer = answers.get(0);
        String id = answer.getId().toString();
        int prevSize = answers.size();

        JSONObject updatedAnswer = new JSONObject();
        updatedAnswer.put("id", id);
        updatedAnswer.put("answerText", "newAnsText");

        mvc.perform(
                put(rootPath + port + endpoint + "update/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedAnswer.toString())
                        .characterEncoding("utf-8"))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.answerText").value("newAnsText"));
        Assert.assertEquals(prevSize, answerRepository.findAll().size());
    }

    @Test
    public void getAllAnswersTest() throws Exception {
        mvc.perform(
                get(rootPath + port + endpoint + "all/"))
                .andExpect(status().isOk());
    }
    
}
