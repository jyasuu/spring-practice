package com.pouchen.scmocp.scmocpapi;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        List<Todo> todos = new ArrayList<>();
        when(service.getTodo()).thenReturn(todos);

        mockMvc.perform(
                get("/todo")
        )
                .andExpect(model().attribute("todos", equalTo(todos)));
    }

}