package com.greylogix.todos.model;

import java.util.Date;

public class Todo2 {
	
	private String id;
		
	public Todo2(String id, String t, Date s, Date d) {
		this.id = id;
		task = t;
		start = s;
		due = d;
	}
	
	public Todo2() {} ;

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
