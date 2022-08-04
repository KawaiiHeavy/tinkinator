package com.example.app.mvcTests;

import com.example.app.models.question.Question;
import com.example.app.repositories.QuestionRepository;
import com.example.app.utils.listeners.QuestionResetDatabaseTestExecutionListener;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestExecutionListeners(mergeMode =
        TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS,
        listeners = {QuestionResetDatabaseTestExecutionListener.class}
)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class QuestionMVCTest {

    @LocalServerPort
    private Integer port;
    @Autowired
    private QuestionRepository questionRepository;

    private final String rootPath = "http://localhost:";
    private final String endpoint = "/api/question/";

    @Autowired
    private MockMvc mvc;

    @Test
    public void accessApplication() {
        Assert.assertEquals(5, questionRepository.findAll().size());
    }

    @Test
    public void addQuestionTest() throws Exception {
        JSONObject question = new JSONObject();
        question.put("questionText", "quest");
        mvc.perform(
                post(rootPath + port + endpoint + "add/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(question.toString())
                        .characterEncoding("utf-8")).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.questionText").value("quest"));
    }

    @Test
    public void deleteQuestionTest() throws Exception {
        int cnt = questionRepository.findAll().size();
        UUID questionId = questionRepository.findAll().get(0).getId();
        mvc.perform(
                delete(rootPath + port + endpoint + "delete/" + questionId))
                .andExpect(status().isOk());
        Assert.assertEquals(cnt - 1, questionRepository.findAll().size());
    }

    @Test
    public void updateQuestionTest() throws Exception {

        List<Question> questions = questionRepository.findAll();
        Question question = questions.get(0);
        String id = question.getId().toString();
        int prevSize = questions.size();

        JSONObject updatedQuestion = new JSONObject();
        updatedQuestion.put("id", id);
        updatedQuestion.put("questionText", "newQuestText");

        mvc.perform(
                put(rootPath + port + endpoint + "update/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedQuestion.toString())
                        .characterEncoding("utf-8"))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.questionText").value("newQuestText"));
        Assert.assertEquals(prevSize, questionRepository.findAll().size());
    }


    @Test
    public void getAllQuestionsTest() throws Exception {
        mvc.perform(
                get(rootPath + port + endpoint + "all/"))
                .andExpect(status().isOk());
    }
    
}
