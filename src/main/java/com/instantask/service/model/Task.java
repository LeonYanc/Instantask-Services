package com.instantask.service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "tasks")
public class Task {

    @Id
    private String id;
    private String name;
    private String assignee;
    private String reporter;
    private String status;
    private Date issuedTime;
    private Date dueTime;
    private boolean visibility;
    private List<String> attachedTask;
    private List<String> comments;
    private List<String> history;
    private String description;

    public Task() {
    }

    public Task(String name, String assignee, String reporter, String status, Date issuedTime,
                Date dueTime, boolean visibility, List<String> attachedTask, List<String> comments,
                List<String> history, String description) {
        this.name = name;
        this.assignee = assignee;
        this.reporter = reporter;
        this.status = status;
        this.issuedTime = issuedTime;
        this.dueTime = dueTime;
        this.visibility = visibility;
        this.attachedTask = attachedTask;
        this.comments = comments;
        this.history = history;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getIssuedTime() {
        return issuedTime;
    }

    public void setIssuedTime(Date issuedTime) {
        this.issuedTime = issuedTime;
    }

    public Date getDueTime() {
        return dueTime;
    }

    public void setDueTime(Date dueTime) {
        this.dueTime = dueTime;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public List<String> getAttachedTask() {
        return attachedTask;
    }

    public void setAttachedTask(List<String> attachedTask) {
        this.attachedTask = attachedTask;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public List<String> getHistory() {
        return history;
    }

    public void setHistory(List<String> history) {
        this.history = history;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", assignee='" + assignee + '\'' +
                ", reporter='" + reporter + '\'' +
                ", status='" + status + '\'' +
                ", issuedTime=" + issuedTime +
                ", dueTime=" + dueTime +
                ", visibility=" + visibility +
                ", attachedTask=" + attachedTask +
                ", comments=" + comments +
                ", history=" + history +
                ", description='" + description + '\'' +
                '}';
    }
}
