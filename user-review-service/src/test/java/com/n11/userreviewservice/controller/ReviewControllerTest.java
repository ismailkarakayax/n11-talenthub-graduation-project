package com.n11.userreviewservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.n11.userreviewservice.UserReviewServiceApplication;
import com.n11.userreviewservice.dto.request.create.CreateReviewRequest;
import com.n11.userreviewservice.dto.request.create.CreateUserRequest;
import com.n11.userreviewservice.dto.request.update.UpdateReviewRequest;
import com.n11.userreviewservice.model.enums.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {UserReviewServiceApplication.class})
class ReviewControllerTest extends BaseControllerTest{

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    void findAll() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/reviews")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }

    @Test
    void findAllByRestaurantId() throws Exception {
        String restaurantId = "92bc0c53-c4b1-47eb-9f55-9c1516457bdb";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/reviews/restaurantId/" + restaurantId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }

    @Test
    void shouldSaveReview() throws Exception {
        CreateReviewRequest request = new CreateReviewRequest(4L, "92bc0c53-c4b1-47eb-9f55-9c1516457bdb",5,"güzel");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/reviews")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }

    @Test
    void findById() throws Exception{
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/reviews/8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }

    @Test
    void updateById() throws Exception {
        UpdateReviewRequest request=new UpdateReviewRequest(3,"orta");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/api/v1/reviews/8")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }

    @Test
    void deleteById() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/reviews/8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}