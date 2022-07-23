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
import static org.junit.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.mockito.Mockito;
import static org.mockito.Mockito.doThrow;

import com.pouchen.scmocp.scmocpapi.controller.TodoController;
import com.pouchen.scmocp.scmocpapi.service.TodoService;
import com.pouchen.scmocp.scmocpapi.entity.Todo;
import com.pouchen.scmocp.scmocpapi.dao.TodoDao;


import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@SpringBootTest
public class WebMockTodo2Test {

    @Autowired
	private TodoService todoService;
    
    @MockBean
    TodoDao todoDao;

    @Test
    public void testGetTodos () {
        // [Arrange] 預期資料
        List<Todo> expectedTodosList = new ArrayList();
        Todo todo = new Todo();
        todo.setId(1);
        todo.setTask("洗衣服");
        todo.setStatus(1);
        expectedTodosList.add(todo);
    
        // 定義模擬呼叫todoDao.findAll() 要回傳的預設結果
        Mockito.when(todoDao.findAll()).thenReturn(expectedTodosList);
    
        // [Act]操作todoService.getTodo();
        Iterable<Todo> actualTodoList = todoService.getTodo();
    
        // [Assert] 預期與實際的資料
        assertEquals(expectedTodosList, actualTodoList);
     }

    @Test
    public void testCreateTodo () {
        // [Arrange] 準備資料
        Todo todo = Todo.builder()
            .task("寫鐵人賽文章")
            .status(0)
            .id(0)
            .build();

        // 模擬呼叫todoDao.save(todo) 的回傳結果
        Mockito.when(todoDao.save(todo)).thenReturn(todo);

        // [Act] 實際呼叫操作todoService.createTodo
        Integer actualId = todoService.create(todo);
        Integer expectId = 0;

        //  [Assert] 預期與實際的資料
        assertEquals(expectId, actualId);
    }

    @Test
    public void testUpdateTodoSuccess () {
        // 準備資料
        Todo todo = Todo.builder()
        .task("寫鐵人賽文章")
        .status(0)
        .id(0)
        .build();
        Optional<Todo> resTodo = Optional.of(todo);
    
        // 模擬呼叫todoDao.findById(id) 回傳的資料
        Mockito.when(todoDao.findById(1)).thenReturn(resTodo);
    
        // [Arrange] 更改的資料
        todo.setStatus(2);
    
        // [Act] 實際呼叫操作todoService.createTodo
        Boolean actualUpdateRlt = todoService.updateTodo(1, todo);
    
        //  [Assert] 預期與實際的資料
        assertEquals(true, actualUpdateRlt);
    }
    
    @Test
    public void testUpdateTodoNotExistId () {
        // 準備更改的資料
        Todo todo = new Todo();
        todo.setStatus(2);
        Optional<Todo> resTodo = Optional.of(todo);
    
        // 模擬呼叫todoDao.findById(id)，資料庫沒有id=100的資料 回傳empty物件
        Mockito.when(todoDao.findById(100)).thenReturn(Optional.empty());
    
        // [Act] 實際呼叫操作todoService.updateTodo()
        Boolean actualUpdateRlt = todoService.updateTodo(100, todo);
    
        // [Assert] 預期與實際的資料
        assertEquals(false, actualUpdateRlt);
    }
    
    @Test
    public void testUpdateTodoOccurException () {
        // 準備更改的資料
        Todo todo = new Todo();
        todo.setId(1);
        todo.setStatus(1);
        Optional<Todo> resTodo = Optional.of(todo);
    
        // 模擬呼叫todoDao.findById(id)，資料庫有id=1的資料
        Mockito.when(todoDao.findById(1)).thenReturn(resTodo);
        todo.setStatus(2);
    
        // 模擬呼叫todoDao.save(todo)時發生NullPointerException例外
        doThrow(NullPointerException.class).when(todoDao).save(todo);
        
        // [Act] 實際呼叫操作todoService.updateTodo()
        Boolean actualUpdateRlt = todoService.updateTodo(100, todo);
    
        //  [Assert] 預期與實際的資料
        assertEquals(false, actualUpdateRlt);
    }

        @Test
    public void testDeleteTodoSuccess () {
        //準備更改的資料
        Todo todo = new Todo();
        todo.setId(1);
        todo.setTask("鐵人賽文章");
        todo.setStatus(2);
        Optional<Todo> resTodo = Optional.of(todo);

        // 模擬呼叫todoDao.findById(id)，模擬資料庫有id=1的資料
        Mockito.when(todoDao.findById(1)).thenReturn(resTodo);

        // [Act] 實際呼叫操作todoService.deleteTodo()
        Boolean actualDeleteRlt = todoService.delete(1);

        //  [Assert] 預期與實際的資料
        assertEquals(true, actualDeleteRlt);
    }

    @Test
    public void testDeleteTodoIdNotExist () {
        //準備更改的資料
        Todo todo = new Todo();
        todo.setId(1);
        todo.setTask("鐵人賽文章");
        todo.setStatus(2);
        Optional<Todo> resTodo = Optional.of(todo);

        // 模擬呼叫todoDao.findById(id)，並模擬資料庫沒有id=100的資料
        Mockito.when(todoDao.findById(100)).thenReturn(Optional.empty());

        // [Act] 實際呼叫操作todoService.deleteTodo()
        Boolean actualDeleteRlt = todoService.delete(100);

        //  [Assert] 預期與實際的資料
        assertEquals(false, actualDeleteRlt);
    }

    @Test
    public void testDeleteTodoOccurException () {
        //準備更改的資料
        Todo todo = new Todo();
        todo.setId(1);
        todo.setTask("鐵人賽文章");
        todo.setStatus(2);
        Optional<Todo> resTodo = Optional.of(todo);

        // 模擬呼叫todoDao.findById(id)，並模擬資料庫有id=1的資料
        Mockito.when(todoDao.findById(1)).thenReturn(resTodo);

        // 模擬呼叫todoDao.deleteById(id)，會發生NullPointerException
        doThrow(NullPointerException.class).when(todoDao).deleteById(1);

        // [Act] 實際呼叫操作todoService.deleteTodo()
        Boolean actualDeleteRlt = todoService.delete(1);

        //  [Assert] 預期與實際的資料
        assertEquals(false, actualDeleteRlt);
    }

}