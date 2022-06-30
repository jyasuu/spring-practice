package com.pouchen.scmocp.scmocpapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import com.pouchen.scmocp.scmocpapi.service.TodoService;
import com.pouchen.scmocp.scmocpapi.entity.Todo;


@Controller
public class TodoController {

    @Autowired
	private TodoService service;

	@RequestMapping("/todo")
	public @ResponseBody Iterable<Todo> getTodo() {
		return service.getTodo();
	}
}