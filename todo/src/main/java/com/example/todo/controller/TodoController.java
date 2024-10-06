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
//		List<Todo> list = todoMapper.selectAll();
	
		// デバッグ用
//		for (Todo todo : list) {
//		    System.out.println("ID: " + todo.getId() + ", Title: " + todo.getTitle() + ", Done Flag: " + todo.getDone_flg() + ", Time Limit: " + todo.getTime_limit());
//		}
		List<Todo> list = todoMapper.selectIncomplete();
		List<Todo> doneList = todoMapper.selectComplete();

		model.addAttribute("todos", list);
		model.addAttribute("doneTodos", doneList);
		return "index";
	}
	
	@PostMapping("/add")
	public String add(Todo todo) {
		todoMapper.add(todo);
		return "redirect:/";
	}
	
	@PostMapping("/update")
	public String update(Todo todo) {
		todoMapper.update(todo);
		return "redirect:/";
	}
	
	@PostMapping("/delete")
	public String delete() {
		todoMapper.delete();
		return "redirect:/";
	}
	
}
