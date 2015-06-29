package com.example.scrumboard.model;

public class UserStory {

	private int id;
	private int sprintId;
	private String name;
	private String details;
	private String status;
	private int priority;
	private int estimatedTime;
	private String comment;
	
	public UserStory() {
    }
	
	public UserStory(int id,  String name, String details,
			String status, int priority, int estimatedTime, int sprintId, String comment) {
		super();
		this.id = id;
		this.sprintId = sprintId;
		this.name = name;
		this.details = details;
		this.status = status;
		this.priority = priority;
		this.estimatedTime = estimatedTime;
		this.comment = comment;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSprintId() {
		return sprintId;
	}
	public void setSprintId(int sprintId) {
		this.sprintId = sprintId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
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
	public int getEstimatedTime() {
		return estimatedTime;
	}
	public void setEstimatedTime(int estimatedTime) {
		this.estimatedTime = estimatedTime;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	
	
	
}
