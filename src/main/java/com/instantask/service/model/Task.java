package com.instantask.service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "tasks")
public class Task {

    @Id
    private String id;
    private String name;
    private String assignee;
    private String reporter;
    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime issuedTime;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dueTime;

    private boolean visibility;
    private List<String> attachedTask;
    private List<String> comments;
    private List<String> history;
    private String description;

    public Task() {
    }

    public Task(String name, String assignee, String reporter, String status,
                LocalDateTime issuedTime, LocalDateTime dueTime,
                boolean visibility, List<String> attachedTask,
                List<String> comments, List<String> history,
                String description) {
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

    // 1. ID
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // 2. Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 3. Assignee
    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    // 4. Reporter
    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    // 5. Status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // 6. IssuedTime
    public LocalDateTime getIssuedTime() {
        return issuedTime;
    }

    public void setIssuedTime(LocalDateTime issuedTime) {
        this.issuedTime = issuedTime;
    }

    // 7. DueTime
    public LocalDateTime getDueTime() {
        return dueTime;
    }

    public void setDueTime(LocalDateTime dueTime) {
        this.dueTime = dueTime;
    }

    // 8. Visibility
    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    // 9. AttachedTask
    public List<String> getAttachedTask() {
        return attachedTask;
    }

    public void setAttachedTask(List<String> attachedTask) {
        this.attachedTask = attachedTask;
    }

    // 10. Comments
    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    // 11. History
    public List<String> getHistory() {
        return history;
    }

    public void setHistory(List<String> history) {
        this.history = history;
    }

    // 12. Description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting Task to JSON", e);
        }
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
