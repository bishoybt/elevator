package com.anz.elevator.api;

import com.anz.elevator.model.Building;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(Controller.class)
@AutoConfigureWebMvc
public class ApiActuator {
    @Autowired
    private MockMvc mvc;

    private static final ObjectMapper objectMapper = new Jackson2ObjectMapperBuilder().build();

    private static Building result;

    public static Building getResultBuilding() { return result; }

    @When("Elevator time ticks")
    public void invokeTicks() throws Exception {
        Building building = DataStructureBuilder.getBuilding();

        MvcResult mvcResult = mvc.perform(post("/api/tick")
                        .content(objectMapper.writeValueAsString(building))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andReturn();

        result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                Building.class);
    }
}
