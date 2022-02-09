package com.gorbatko.todolist.controller;

import com.gorbatko.todolist.entities.Task;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/todo")
@Api
public class Service {

    private static final Logger logger = Logger.getLogger(Service.class.getName());

    private final LinkedList<String> toDoList = new LinkedList<String>();

    @PostMapping("new")
    @ApiOperation("Adding task description to list")
    public String writeTask(@RequestBody Task task) {
        logger.info("Task description writing");
        toDoList.add(task.getDescription());
        logger.fine("Task description's been written");
        return "Task has been added!";
    }

    @GetMapping("showAll")
    @ApiOperation("Getting list of all tasks")
    public List<String> getToDoList() {
        logger.warning("Getting request for list");
        return toDoList;
    }

    @DeleteMapping("next")
    @ApiOperation("Getting task description and deleting it from list")
    public String toDoNextTask() {
        String str;
        if (!toDoList.isEmpty()) {
            str = "Done: " + toDoList.getFirst();
            logger.info("Deleting request for toDoList");
            toDoList.removeFirst();
        } else {
            logger.warning("List is empty");
            str = "You've done all tasks!";
        }
        return str;
    }

}
