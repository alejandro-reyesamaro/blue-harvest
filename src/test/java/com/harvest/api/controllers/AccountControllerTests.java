package com.harvest.api.controllers;

import static org.mockito.Mockito.when;

import java.util.Optional;

import com.harvest.api.controllers.config.CostumerControllerTestConfiguration;
import com.harvest.application.features.AccountFeature;
import com.harvest.application.features.dto.AddAccountResult;
import com.harvest.application.features.dto.GetCostumerAccountsResult;
import com.harvest.application.services.dto.forms.AddAccountForm;
import com.harvest.core.entities.Account;
import com.harvest.testools.factories.AccountFactory;
import com.harvest.testools.factories.AddAccountFormFactory;
import com.harvest.testools.json.JsonTools;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.containsString;

@ExtendWith(SpringExtension.class)
@Import(CostumerControllerTestConfiguration.class)
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class AccountControllerTests {
    
    protected static final String REQUEST_MAPPING = "/account";

    private MockMvc mvc;

    @MockBean
    protected AccountFeature feature;

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
        int id = 1;
        int costumerId = 10;
        when(feature.getAccountById(id)).thenReturn(Optional.of(AccountFactory.buildAccount(id, costumerId)));

        // Act & Assert
        mvc.perform(get(REQUEST_MAPPING + "/1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(id))
            .andExpect(jsonPath("$.costumerId").value(costumerId));
    }

    @Test
    public void getCostumerAccounts_costumerNotFound() throws Exception {
        // Arrange
        int costumerId = 10;
        GetCostumerAccountsResult result = GetCostumerAccountsResult.costumerNotFound();
        when(feature.getCostumerAccounts(costumerId)).thenReturn(result);

        // Act & Assert
        mvc.perform(get(REQUEST_MAPPING + "/costumer/" + 10).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(GetCostumerAccountsResult.NO_COSTUMER_FOUND)));
    }

    @Test
    public void getCostumerAccounts_noAccountFound() throws Exception {
        // Arrange
        int costumerId = 10;
        GetCostumerAccountsResult result = GetCostumerAccountsResult.notAccountsFound();
        when(feature.getCostumerAccounts(costumerId)).thenReturn(result);

        // Act & Assert
        mvc.perform(get(REQUEST_MAPPING + "/costumer/" + 10).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString(GetCostumerAccountsResult.NO_ACCOUNT_FOUND)));
    }

    @Test
    public void getCostumerAccounts_success() throws Exception {
        // Arrange
        int costumerId = 10;
        GetCostumerAccountsResult result = GetCostumerAccountsResult.success(AccountFactory.buildAccounts4Test(costumerId, 3));
        when(feature.getCostumerAccounts(costumerId)).thenReturn(result);

        // Act & Assert
        mvc.perform(get(REQUEST_MAPPING + "/costumer/" + 10).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.accounts[0].id", is(1)))
            .andExpect(jsonPath("$.accounts[0].costumerId", is(costumerId)));
    }

    @Test
    public void addAccount_formValidationFail_credit() throws Exception {
        // Arrange
        int costumerId = 10;
        double credit = -10;
        AddAccountResult result = AddAccountResult.costumerNotFound();
        AddAccountForm form = AddAccountFormFactory.buildForm(costumerId, "Default", credit);
        when(feature.createAccount(form)).thenReturn(result);

        // Act & Assert
        mvc.perform(post(REQUEST_MAPPING).contentType(MediaType.APPLICATION_JSON)
            .content(JsonTools.writeObjectAsJsonString(form)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.errors[0]", is(AddAccountForm.CREDIT_MIN_ERR_MSG)));
    }

    @Test
    public void addAccount_costumerNotFound() throws Exception {
        // Arrange
        int costumerId = 10;
        double credit = 0;
        AddAccountResult result = AddAccountResult.costumerNotFound();
        AddAccountForm form = AddAccountFormFactory.buildForm(costumerId, "Default", credit);
        when(feature.createAccount(form)).thenReturn(result);

        // Act & Assert
        mvc.perform(post(REQUEST_MAPPING).contentType(MediaType.APPLICATION_JSON)
            .content(JsonTools.writeObjectAsJsonString(form)))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(AddAccountResult.NO_COSTUMER_FOUND)));
    }

    @Test
    public void addAccount_emptyAccount() throws Exception {
        // Arrange
        int id = 1;
        int costumerId = 10;
        double credit = 0;
        Account account = AccountFactory.buildAccount(id, costumerId, credit);
        AddAccountResult result = AddAccountResult.emptyAccountCreated(account);
        AddAccountForm form = AddAccountFormFactory.buildForm(costumerId, account.getName(), credit);
        when(feature.createAccount(form)).thenReturn(result);

        // Act & Assert
        mvc.perform(post(REQUEST_MAPPING).contentType(MediaType.APPLICATION_JSON)
            .content(JsonTools.writeObjectAsJsonString(form)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.account", is(account.getName())));
    }
}
