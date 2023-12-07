package com.mm.moneymanagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mm.moneymanagement.model.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
//This annotation is used to load the 
//entire application context for the test.
//allows you to configure the environment 
//the application runs in during testing.
@AutoConfigureMockMvc
//It auto-configures MockMvc, 
//an essential tool for testing Spring 
//MVC applications. MockMvc allows you to 
//send HTTP requests to your controller 
//and assert the responses without needing 
//to start a full HTTP server.
//By using MockMvc, your tests run faster and are more focused since you're not starting an actual server. 
public class MoneyManagementJsonTest {
    @Autowired
    //This annotation is used for dependency injection in Spring. It lets Spring know that the annotated field should be injected with an instance of the specified type.
    //Spring provides the instances of these dependencies at runtime without you having to manually create them.
    //By using @Autowired, Spring's testing framework automatically provides a configured MockMvc instance. This instance is set up with the necessary context and configuration derived from your application, ensuring that it mirrors your application's a
    private MockMvc mockMvc;

    @Autowired
    //ObjectMapper is a part of the Jackson library that Spring Boot uses for JSON serialization and deserialization.
    //In testing, you often need to convert objects to JSON strings (serialization) or vice versa (deserialization), especially when testing REST APIs or any functionality involving JSON data. 
    //The ObjectMapper instance can be used to programmatically handle JSON in your tests.
    //By autowiring ObjectMapper, you ensure that the instance you use in tests is configured the same way as the one used by your application. This includes any custom configurations or modules your application may use for JSON processing.
    private ObjectMapper objectMapper;

    @Test
    public void firstTest() throws Exception{
        Account account = new Account();
        account.setAccountNumber("11773092");
        account.setBalance(new BigDecimal(230000));
        //Performing Mock Http Request
        mockMvc.perform(post("/account") //POST request to /account endpoint
                .contentType(MediaType.APPLICATION_JSON) //Set the content type of the request to JSON
                .content(objectMapper.writeValueAsString(account))) //Serializes the 'Account' object to a JSON string
                .andExpect(status().isOk()) //Check if the response status is OK
                .andExpect(jsonPath("$.accountNumber").value("11773092"))
                .andExpect(jsonPath("$.balance").value(230000));
                //Verifies that the JSON response contains 'accountNumber' field and 'balance' field with values
    
    }
    
}
