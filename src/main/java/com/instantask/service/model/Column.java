package com.instantask.service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "columns")
public class Column {

    @Id
    private String id;
    private String name;             // unique
    private List<String> taskCardList; // references TaskCardID[]
    private boolean initialColumn;

    public Column() {
    }

    public Column(String id, String name, List<String> taskCardList, boolean initialColumn) {
        this.id = id;
        this.name = name;
        this.taskCardList = taskCardList;
        this.initialColumn = initialColumn;
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

    public List<String> getTaskCardList() {
        return taskCardList;
    }

    public void setTaskCardList(List<String> taskCardList) {
        this.taskCardList = taskCardList;
    }

    public boolean isInitialColumn() {
        return initialColumn;
    }

    public void setInitialColumn(boolean initialColumn) {
        this.initialColumn = initialColumn;
    }
}
