package com.example.app.mvcTests;

import com.example.app.dto.AnswerDTO;
import com.example.app.dto.SolutionDTO;
import com.example.app.models.Answer;
import com.example.app.repositories.AnswerRepository;
import com.example.app.repositories.SolutionRepository;
import com.example.app.utils.JsonMapper;
import com.example.app.utils.listeners.AnswerResetDatabaseTestExecutionListener;
import com.example.app.utils.listeners.SolutionResetDatabaseTestExecutionListener;
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

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestExecutionListeners(mergeMode =
        TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS,
        listeners = {
                AnswerResetDatabaseTestExecutionListener.class,
                SolutionResetDatabaseTestExecutionListener.class
        }
)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AnswerSolutionMVCTest {

    @LocalServerPort
    private Integer port;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private SolutionRepository solutionRepository;
    @Autowired
    private JsonMapper jsonMapper;

    private final String rootPath = "http://localhost:";
    private final String answerEndpoint = "/api/answer/";
    private final String solutionEndpoint = "/api/solution/";

    @Autowired
    public MockMvc mvc;

    @Test
    public void accessApplication() {
        Assert.assertEquals(5, answerRepository.findAll().size());
        Assert.assertEquals(5, solutionRepository.findAll().size());
    }

    @Test
    public void addAnswerWithSolutionTest() throws Exception {
        SolutionDTO solutionDTO = new SolutionDTO();
        solutionDTO.setSolutionText("sol");

        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setAnswerText("ans");
        answerDTO.setSolution(solutionDTO);

        mvc.perform(
                post(rootPath + port + answerEndpoint + "add/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.mapAnswerToJSON(answerDTO).toString())
                        .characterEncoding("utf-8")).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.answerText").value("ans"))
                .andExpect(jsonPath("$.solution.id").isNotEmpty())
                .andExpect(jsonPath("$.solution.solutionText").value("sol"));
    }

    @Test
    public void addAnddeleteAnswerWithSolutionTest() throws Exception {

        SolutionDTO solutionDTO = new SolutionDTO();
        solutionDTO.setSolutionText("sol");

        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setAnswerText("ans");
        answerDTO.setSolution(solutionDTO);

        mvc.perform(
                post(rootPath + port + answerEndpoint + "add/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.mapAnswerToJSON(answerDTO).toString())
                        .characterEncoding("utf-8")).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.answerText").value("ans"))
                .andExpect(jsonPath("$.solution.id").isNotEmpty())
                .andExpect(jsonPath("$.solution.solutionText").value("sol"));

        Optional<Answer> answerOptional = answerRepository.findAnswerByAnswerText("ans");
        answerOptional.ifPresent(answer -> {
            try {
                mvc.perform(
                        delete(rootPath + port + answerEndpoint + "delete/" + answer.getId()))
                        .andExpect(status().isOk());
                mvc.perform(
                        get(rootPath + port + answerEndpoint + "countAll/"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$").value("5"));
                mvc.perform(
                        get(rootPath + port + solutionEndpoint + "countAll/"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$").value("6"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

}
