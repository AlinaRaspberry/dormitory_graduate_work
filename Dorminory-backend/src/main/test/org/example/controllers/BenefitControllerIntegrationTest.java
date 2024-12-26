package org.example.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.models.Benefit;
import org.example.models.requests.CreateBenefitRequest;
import org.example.repositories.BenefitRepository;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class BenefitControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BenefitRepository benefitRepository;

    @BeforeEach
    void setUp() {
        benefitRepository.deleteAll();
    }

    @Test
    void testGetAllBenefits_ShouldReturnOk() throws Exception {
        Benefit benefit1 = new Benefit();
        benefit1.setName("Benefit 1");
        benefit1.setDescription("Description 1");

        Benefit benefit2 = new Benefit();
        benefit2.setName("Benefit 2");
        benefit2.setDescription("Description 2");

        benefitRepository.save(benefit1);
        benefitRepository.save(benefit2);

        mockMvc.perform(get("/benefits"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Benefit 1"))
                .andExpect(jsonPath("$[1].name").value("Benefit 2"));
    }

    @Test
    void testDeleteBenefit_ShouldSucceed_WhenDoesNotExist() throws Exception {
        mockMvc.perform(delete("/benefits/999"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void testCreateBenefit_ShouldReturnOk_WhenBenefitCreated() throws Exception {
        CreateBenefitRequest request = new CreateBenefitRequest("Benefit 1", "Description 1");

        mockMvc.perform(post("/benefits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Benefit 1"))
                .andExpect(jsonPath("$.description").value("Description 1"));
    }
}
