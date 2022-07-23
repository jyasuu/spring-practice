package com.pouchen.scmocp.scmocpapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.pouchen.scmocp.scmocpapi.service.TodoService;
import com.pouchen.scmocp.scmocpapi.entity.Todo;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/todo")
public class TodoController {
    private static final Logger logger
                = LoggerFactory.getLogger(TodoController.class);

    @Autowired
	private TodoService service;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Iterable<Todo> getTodo() {
        logger.info("Hi...");
        logger.error("I am an error");
        logger.warn("Warning!.");
		return service.getTodo();
	}

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Todo>  create(
            String task
    ) {
        return new ResponseEntity<>(service.create(task), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{todoId}", method = RequestMethod.DELETE)
    public String complete(
            @PathVariable Integer todoId
    ) {
        service.delete(todoId);
        return "redirect:/todo";
    }
}