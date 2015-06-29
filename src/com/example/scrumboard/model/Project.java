package com.example.scrumboard.model;

import java.util.Date;



public class Project {

	
	private int id;
	private int userstoryId;
	private String name;
	private String description;
	private int master;
	private Date startDate;
	
	
	private String status;
	private int priority;
	private Date deadline;
	
	public Project() {
		
	}
	
	public Project(int id, int userstoryId, String name, String description,
			 Date startDate, String status, int master, int priority,
			Date deadline) {
		super();
		this.id = id;
		this.userstoryId = userstoryId;
		this.name = name;
		this.description = description;
		this.master = master;
		this.startDate = startDate;
		this.status = status;
		this.priority = priority;
		this.deadline = deadline;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserstoryId() {
		return userstoryId;
	}
	public void setUserstoryId(int userstoryId) {
		this.userstoryId = userstoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getMaster() {
		return master;
	}
	public void setMaster(int master) {
		this.master = master;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	
	
	
	
	
	
}
