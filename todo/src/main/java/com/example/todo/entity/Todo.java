package com.example.todo.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Todo {
	private Integer id;
	private String title;
	private Integer done_flg;
	private LocalDate time_limit;
}
