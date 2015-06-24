package com.example.scrumboard.model;

import java.util.Date;



public class Sprint {

	private int id;
	private int projectId;
	private Date startDate;
	private Date endDate;
	
	
	public Sprint() {
		
	}
	
	public Sprint(int id, int projectId, Date startDate, Date endDate) {
		super();
		this.id = id;
		this.projectId = projectId;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	
	
	
}
