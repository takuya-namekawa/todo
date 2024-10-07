package com.example.todo.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.todo.entity.Todo;
import com.example.todo.mapper.TodoMapper;

@Controller
public class TodoController {
	
	@Autowired
	TodoMapper todoMapper;
	
	@GetMapping("/")
	public String index(Model model, @RequestParam(required = false) String error) {

		List<Todo> list = todoMapper.selectIncomplete();
		List<Todo> doneList = todoMapper.selectComplete();

		model.addAttribute("todos", list);
		model.addAttribute("doneTodos", doneList);
		model.addAttribute("error", error);
		
		// 本日の日付をモデルに追加する
		model.addAttribute("today", LocalDate.now());
		
		// 各Todoのtime_limitとの日数の差分を計算する
		for (Todo todo : list) {
			long diffDays = ChronoUnit.DAYS.between(LocalDate.now(), todo.getTime_limit());
			todo.setDiffDays(diffDays);
		}
		
		return "index";
	}
	
	@PostMapping("/add")
	public String add(Todo todo) {
		
		// タイトルが空であるか確認する
		if (todo.getTitle() == null || todo.getTitle().trim().isEmpty()) {
			return "redirect:/?error=emptyTitle";
		}
		
		// 日付が過去の日付であるか確認する
		if (todo.getTime_limit() != null && todo.getTime_limit().isBefore(LocalDate.now())) {
			return "redirect:/?error=pastDate";
		}
		
		todoMapper.add(todo);
		return "redirect:/";
	}
	
	@PostMapping("/update")
	public String update(Todo todo) {
		
		// タイトルが空であるか確認する
		if (todo.getTitle().trim().isEmpty()) {
			return "redirect:/?error=emptyTitle";
		}
		
		// 日付が過去の日付であるか確認する
		if (todo.getTime_limit().isBefore(LocalDate.now())) {
			return "redirect:/?error=pastDate";
		}

		todoMapper.update(todo);
		
		return "redirect:/";
	}
	
	@PostMapping("/delete")
	public String delete() {
		todoMapper.delete();
		return "redirect:/";
	}
	
}
