package com.instantask.service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "comments")
public class Comment {

    @Id
    private String id;
    private LocalDateTime issuedTime;
    private String commenter;   // references UserAccessID
    private String relatedTask; // references TaskID
    private String responseTo;  // references CommentID
    private String content;

    public Comment() {
    }

    public Comment(String id, LocalDateTime issuedTime, String commenter,
                   String relatedTask, String responseTo, String content) {
        this.id = id;
        this.issuedTime = issuedTime;
        this.commenter = commenter;
        this.relatedTask = relatedTask;
        this.responseTo = responseTo;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getIssuedTime() {
        return issuedTime;
    }

    public void setIssuedTime(LocalDateTime issuedTime) {
        this.issuedTime = issuedTime;
    }

    public String getCommenter() {
        return commenter;
    }

    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }

    public String getRelatedTask() {
        return relatedTask;
    }

    public void setRelatedTask(String relatedTask) {
        this.relatedTask = relatedTask;
    }

    public String getResponseTo() {
        return responseTo;
    }

    public void setResponseTo(String responseTo) {
        this.responseTo = responseTo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
