package com.pouchen.scmocp.scmocpapi.service;

import com.pouchen.scmocp.scmocpapi.entity.Todo;
import com.pouchen.scmocp.scmocpapi.dao.TodoDao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class TodoService {
    @Autowired
    TodoDao todoDao;

    public Iterable<Todo> getTodo() {
        return todoDao.findAll();
    }

    public Todo create(String task) {
        Todo todo = Todo.builder()
            .task(task)
            .status(0)
            .build();
        return todoDao.save(todo);
    }

    public void delete(Integer taskId) {
        Optional<Todo> todo = todoDao.findById(taskId);
        if(todo.isPresent())
            todoDao.delete(todo.get());
    }
}