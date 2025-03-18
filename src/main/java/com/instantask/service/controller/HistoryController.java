package com.instantask.service.controller;

import com.instantask.service.model.History;
import com.instantask.service.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/histories")
public class HistoryController {

    @Autowired
    private HistoryRepository historyRepository;

    @GetMapping
    public List<History> getAllHistories() {
        return historyRepository.findAll();
    }

    @GetMapping("/{id}")
    public History getHistoryById(@PathVariable String id) {
        Optional<History> histOpt = historyRepository.findById(id);
        return histOpt.orElse(null);
    }

    @PostMapping
    public History createHistory(@RequestBody History history) {
        return historyRepository.save(history);
    }

    @PutMapping("/{id}")
    public History updateHistory(@PathVariable String id, @RequestBody History updatedHist) {
        return historyRepository.findById(id).map(existingHist -> {
            existingHist.setRelatedUser(updatedHist.getRelatedUser());
            existingHist.setRelatedTask(updatedHist.getRelatedTask());
            existingHist.setIssuedTime(updatedHist.getIssuedTime());
            existingHist.setDescription(updatedHist.getDescription());
            return historyRepository.save(existingHist);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteHistory(@PathVariable String id) {
        historyRepository.deleteById(id);
    }
}
