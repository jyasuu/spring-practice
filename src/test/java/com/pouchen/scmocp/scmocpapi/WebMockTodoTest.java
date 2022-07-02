package com.pouchen.scmocp.scmocpapi;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.pouchen.scmocp.scmocpapi.controller.TodoController;
import com.pouchen.scmocp.scmocpapi.service.TodoService;
import com.pouchen.scmocp.scmocpapi.entity.Todo;


import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(value = TodoController.class)
public class WebMockTodoTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TodoService service;

    @Test
    public void todolist() throws Exception {
                
        mockMvc.perform( MockMvcRequestBuilders
        .get("/todo")
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(0)));

    }

    @Test
    public void createTodo() throws Exception 
    {
        String task = "hello123";
        Todo todo = Todo.builder()
            .task(task)
            .status(0)
            .build();
		when(service.create(task)).thenReturn(todo);

        mockMvc.perform( MockMvcRequestBuilders
        .post("/todo?task="+task)
    //   .content(asJsonString(new EmployeeVO(null, "firstName4", "lastName4", "email4@mail.com")))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        )
        // .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.task").exists())
        .andExpect(jsonPath("$.task").value(task));
    }

}