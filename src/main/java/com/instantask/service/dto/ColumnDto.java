package com.instantask.service.dto;

import java.util.List;

public class ColumnDto {
    private String id;
    private String name; // unique
    private List<String> taskCardList; // references TaskCardID[]
    private boolean isInitialColumn;

    public ColumnDto() {}

    public ColumnDto(String id, String name, List<String> taskCardList, boolean isInitialColumn) {
        this.id = id;
        this.name = name;
        this.taskCardList = taskCardList;
        this.isInitialColumn = isInitialColumn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isInitialColumn() {
        return isInitialColumn;
    }

    public void setInitialColumn(boolean initialColumn) {
        isInitialColumn = initialColumn;
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
}
