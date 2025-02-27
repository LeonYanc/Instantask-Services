package com.instantask.service.dto;

import java.util.List;

public class BoardDto {
    private String id;
    private String name;
    private String code;       // unique
    private List<String> columns;   // references ColumnID[]
    private List<String> members;   // references UserAccessID[]

    public BoardDto() {}

    public BoardDto(String id, String name, String code, List<String> columns, List<String> members) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.columns = columns;
        this.members = members;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
