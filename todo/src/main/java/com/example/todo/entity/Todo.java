package com.example.todo.entity;
import java.time.LocalDate;

import lombok.Data;

@Data
public class Todo {
	private Integer id;
	private String title;
	private Integer done_flg;
	private LocalDate time_limit;
	// 現在の日付 - 目標日付 であと〇日と出力するためのフィールド
	private long diffDays;
}
