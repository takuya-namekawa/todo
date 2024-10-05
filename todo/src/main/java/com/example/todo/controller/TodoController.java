package com.example.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.todo.entity.Todo;
import com.example.todo.mapper.TodoMapper;

@Controller
public class TodoController {
	
	@Autowired
	TodoMapper todoMapper;
	
	@GetMapping("/")
	public String index(Model model) {
		List<Todo> list = todoMapper.selectAll();
		
		model.addAttribute("todos", list);
		return "index";
	}
	
	@PostMapping("/add")
	public String add(Todo todo) {
		todoMapper.add(todo);
		return "redirect:/";
	}
	
}
