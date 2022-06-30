package com.pouchen.scmocp.scmocpapi.service;

import com.pouchen.scmocp.scmocpapi.entity.Todo;
import com.pouchen.scmocp.scmocpapi.dao.TodoDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class TodoService {
    @Autowired
    TodoDao todoDao;

    public Iterable<Todo> getTodo() {
        return todoDao.findAll();
    }
}