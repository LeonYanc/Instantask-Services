package com.instantask.service.controller;

import com.instantask.service.model.Column;
import com.instantask.service.repository.ColumnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/columns")
public class ColumnController {

    @Autowired
    private ColumnRepository columnRepository;

    @GetMapping
    public List<Column> getAllColumns() {
        return columnRepository.findAll();
    }

    @GetMapping("/{id}")
    public Column getColumnById(@PathVariable String id) {
        Optional<Column> colOpt = columnRepository.findById(id);
        return colOpt.orElse(null);
    }

    @PostMapping
    public Column createColumn(@RequestBody Column column) {
        return columnRepository.save(column);
    }

    @PutMapping("/{id}")
    public Column updateColumn(@PathVariable String id, @RequestBody Column updatedCol) {
        return columnRepository.findById(id).map(existingCol -> {
            existingCol.setName(updatedCol.getName());
            existingCol.setTaskCardList(updatedCol.getTaskCardList());
            existingCol.setInitialColumn(updatedCol.isInitialColumn());
            return columnRepository.save(existingCol);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteColumn(@PathVariable String id) {
        columnRepository.deleteById(id);
    }
}
