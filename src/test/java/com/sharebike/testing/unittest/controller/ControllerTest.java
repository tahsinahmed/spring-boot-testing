package com.sharebike.testing.unittest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sharebike.testing.controller.TestController;
import com.sharebike.testing.entity.TestEntity;
import com.sharebike.testing.service.TestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TestController.class)
@ActiveProfiles("test")
public class ControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TestService testService;

    @Autowired
    ObjectMapper objectMapper;

    private List<TestEntity> testEntities;

    @BeforeEach
    void setup() {
        this.testEntities = new ArrayList<>();
        testEntities.add(new TestEntity(1L, "Tahsin", 24, 5.8));
        testEntities.add(new TestEntity(2L, "Shafin", 25, 6.1));
        testEntities.add(new TestEntity(3L, "Shovon", 24, 5.7));
    }

    @Test
    void testGetAll() throws Exception {
        given(testService.getAll()).willReturn(testEntities);
        this.mockMvc.perform(get("/api/test").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(testEntities.size())));
    }

    @Test
    void testGetOne() throws Exception {
        final Long id = 1L;
        final TestEntity testEntity = new TestEntity(1L, "Tahsin", 24, 5.8);

        given(testService.getOne(id)).willReturn(testEntity);
        mockMvc.perform(get("/api/test-one?id=" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.age", is(testEntity.getAge())));
    }

    @Test
    void testCreate() throws Exception {

        given(testService.create(any(TestEntity.class))).willReturn("Saved Successfully");
        TestEntity testEntity = new TestEntity(1L, "Tahsin", 24, 5.8);
        mockMvc.perform(post("/api/test").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(testEntity)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", is("Saved Successfully")));
    }

}
