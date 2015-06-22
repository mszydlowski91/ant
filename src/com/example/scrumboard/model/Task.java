package com.example.scrumboard.model;

import java.util.Date;



public class Task {

	private int id;
	private int userstoryId;
	private int memberId;	
	private int estimatedTime;
	private String status;
	private String name;
	private Date deadline;
	private String comment;
	
	public Task() {
    }
	
	public Task(int id, int userstoryId, int memberId, int estimatedTime,
			String status, String name, Date deadline, String comment) {
		super();
		this.id = id;
		this.userstoryId = userstoryId;
		this.memberId = memberId;
		this.estimatedTime = estimatedTime;
		this.status = status;
		this.name = name;
		this.deadline = deadline;
		this.comment = comment;
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
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int getEstimatedTime() {
		return estimatedTime;
	}
	public void setEstimatedTime(int estimatedTime) {
		this.estimatedTime = estimatedTime;
	}
	
	public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

    @Override
    public String toString() {
        return "Task [id=" + id + ", userstoryId=" + userstoryId + ", memberId=" + memberId + ", estimatedTime="
                + estimatedTime + ", status=" + status + ", name=" + name + ", deadline=" + deadline + ", comment="
                + comment + "]";
    }
	
	
}
