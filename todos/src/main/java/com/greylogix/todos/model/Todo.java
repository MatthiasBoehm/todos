package com.greylogix.todos.model;

import java.util.Date;
import java.util.*;

public class Todo {
	
	private final String id;
	
	{
		id = UUID.randomUUID().toString();
	}
	
	public Todo(String t, Date s, Date d) {		
		task = t;
		start = s;
		due = d;
	}
	
	public Todo() {} ;

	private String task;
	private Date start;
	private Date due;
	
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getDue() {
		return due;
	}
	public void setDue(Date due) {
		this.due = due;
	}

	public String getId() {
		return id;
	}
	
}
