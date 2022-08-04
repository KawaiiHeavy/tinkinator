package com.example.app.mvcTests;

import com.example.app.models.other.Solution;
import com.example.app.repositories.SolutionRepository;
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

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestExecutionListeners(mergeMode =
        TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS,
        listeners = {SolutionResetDatabaseTestExecutionListener.class}
)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SolutionMVCTest {

    @LocalServerPort
    private Integer port;
    @Autowired
    private SolutionRepository solutionRepository;

    private final String rootPath = "http://localhost:";
    private final String endpoint = "/api/solution/";

    @Autowired
    private MockMvc mvc;

    @Test
    public void accessApplication() {
        Assert.assertEquals(5, solutionRepository.findAll().size());
    }

    @Test
    public void addSolutionTest() throws Exception {
        JSONObject solution = new JSONObject();
        solution.put("solutionText", "sol");
        mvc.perform(
                post(rootPath + port + endpoint + "add/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(solution.toString())
                        .characterEncoding("utf-8")).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.solutionText").value("sol"));
    }

    @Test
    public void deleteSolutionTest() throws Exception {
        int cnt = solutionRepository.findAll().size();
        UUID solutionId = solutionRepository.findAll().get(0).getId();
        mvc.perform(
                delete(rootPath + port + endpoint + "delete/" + solutionId))
                .andExpect(status().isOk());
        Assert.assertEquals(cnt - 1, solutionRepository.findAll().size());
    }

    @Test
    public void updateSolutionTest() throws Exception {

        List<Solution> solutions = solutionRepository.findAll();
        Solution solution = solutions.get(0);
        String id = solution.getId().toString();
        int prevSize = solutions.size();

        JSONObject updatedSolution = new JSONObject();
        updatedSolution.put("id", id);
        updatedSolution.put("solutionText", "newSolText");

        mvc.perform(
                put(rootPath + port + endpoint + "update/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedSolution.toString())
                        .characterEncoding("utf-8"))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.solutionText").value("newSolText"));
        Assert.assertEquals(prevSize, solutionRepository.findAll().size());
    }


    @Test
    public void getAllSolutionsTest() throws Exception {
        mvc.perform(
                get(rootPath + port + endpoint + "all/"))
                .andExpect(status().isOk());
    }
}
