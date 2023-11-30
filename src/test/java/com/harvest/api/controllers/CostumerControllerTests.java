package com.harvest.api.controllers;

import static org.mockito.Mockito.when;

import java.util.Optional;

import com.harvest.api.controllers.config.CostumerControllerTestConfiguration;
import com.harvest.application.features.CostumerFeature;
import com.harvest.testools.factories.CostumerFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
//@WebMvcTest(CostumerController.class)
@Import(CostumerControllerTestConfiguration.class)
@SpringBootTest
public class CostumerControllerTests {
    
    //@Autowired
    private MockMvc mvc;

    @MockBean
    protected CostumerFeature costumerFeature;

    @Autowired
    private WebApplicationContext wac;


    @Test
    public void getById_success() throws Exception {
        MockitoAnnotations.openMocks(this);
        this.mvc = MockMvcBuilders.webAppContextSetup(wac).build();

        // Arrange
        int costumerId = 1;
        when(costumerFeature.getCostumerById(costumerId)).thenReturn(Optional.of(CostumerFactory.buildCostumer(costumerId)));

        // Act & Assert
        mvc.perform(get("/costumer/1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(costumerId));
    }
}
