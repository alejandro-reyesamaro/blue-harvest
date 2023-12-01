package com.harvest.api.controllers;

import static org.mockito.Mockito.when;

import java.util.Optional;

import com.harvest.api.controllers.config.CostumerControllerTestConfiguration;
import com.harvest.application.features.CostumerFeature;
import com.harvest.application.features.dto.GetAllCostumersResult;
import com.harvest.testools.factories.CostumerFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.containsString;

@ExtendWith(SpringExtension.class)
@Import(CostumerControllerTestConfiguration.class)
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class CostumerControllerTests {
    
    protected static final String REQUEST_MAPPING = "/costumer";

    private MockMvc mvc;

    @MockBean
    protected CostumerFeature costumerFeature;

    @Autowired
    private WebApplicationContext wac;

    @BeforeAll
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void getById_success() throws Exception {
        // Arrange
        int costumerId = 1;
        when(costumerFeature.getCostumerById(costumerId)).thenReturn(Optional.of(CostumerFactory.buildCostumer(costumerId)));

        // Act & Assert
        mvc.perform(get(REQUEST_MAPPING + "/1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(costumerId));
    }

    @Test
    public void getAll_exception() throws Exception {
        // Arrange
        when(costumerFeature.getAllCostumers()).thenThrow(new RuntimeException());

        // Act & Assert
        mvc.perform(get(REQUEST_MAPPING).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isInternalServerError());
    }

    @Test
    public void getAll_emptyResponse() throws Exception {
        // Arrange
        GetAllCostumersResult result = GetAllCostumersResult.noCostumers();
        when(costumerFeature.getAllCostumers()).thenReturn(result);

        // Act & Assert
        mvc.perform(get(REQUEST_MAPPING).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString(GetAllCostumersResult.NO_COSTUMER_FOUND)));
    }

    @Test
    public void getAll_success() throws Exception {
        // Arrange
        GetAllCostumersResult result = GetAllCostumersResult.success(CostumerFactory.buildCostumers4Test(3));
        when(costumerFeature.getAllCostumers()).thenReturn(result);

        // Act & Assert
        mvc.perform(get(REQUEST_MAPPING).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.costumers[0].id", is(1)));
    }
}
