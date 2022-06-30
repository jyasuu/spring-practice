package com.pouchen.scmocp.scmocpapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.pouchen.scmocp.scmocpapi.service.TodoService;
import com.pouchen.scmocp.scmocpapi.entity.Todo;


@Controller
@RequestMapping("/todo")
public class TodoController {

    @Autowired
	private TodoService service;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Iterable<Todo> getTodo() {
		return service.getTodo();
	}

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Todo  create(
            String task
    ) {
        
        return service.create(task);
    }

    @RequestMapping(value = "/{todoId}", method = RequestMethod.DELETE)
    public String complete(
            @PathVariable Integer todoId
    ) {
        service.delete(todoId);
        return "redirect:/todo";
    }
}