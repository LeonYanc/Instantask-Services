package com.instantask.service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "boards")
public class Board {

    @Id
    private String id;
    private String name;
    private String code;       // unique
    private List<String> columns; // references ColumnID[]
    private List<String> members; // references UserAccessID[]

    public Board() {
    }

    public Board(String id, String name, String code,
                 List<String> columns, List<String> members) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.columns = columns;
        this.members = members;
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

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }
}
