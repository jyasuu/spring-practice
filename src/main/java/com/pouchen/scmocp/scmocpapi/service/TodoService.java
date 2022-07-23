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

    public Integer create(Todo todo) {
        Todo rltTodo = todoDao.save(todo);
        return rltTodo.getId();
    }

    public Boolean updateTodo(Integer id,Todo todo) {
        Optional<Todo> isExistTodo = todoDao.findById(id);
        if (! isExistTodo.isPresent()) {
            return false;
        }
        Todo newTodo = isExistTodo.get();
        if (todo.getStatus() == null) {
            return false;
        }
        newTodo.setStatus(todo.getStatus());
        try {
            todoDao.save(newTodo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean delete(Integer taskId) {
        Optional<Todo> findTodo = todoDao.findById(taskId);
        if (!findTodo.isPresent()) {
            return false;
        }
        try {
            todoDao.deleteById(taskId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}