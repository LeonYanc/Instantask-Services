package com.instantask.service.controller;

import com.instantask.service.model.Task;
import com.instantask.service.model.TaskCard;
import com.instantask.service.repository.TaskRepository;
import com.instantask.service.repository.TaskCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskCardRepository taskCardRepository;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable String id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        return taskOptional.orElse(null);
    }

    @GetMapping("/search")
    public List<Task> findTasksByName(@RequestParam String name) {
        return taskRepository.findByName(name);
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        Task savedTask = taskRepository.save(task);
        // Create a TaskCard with the same id and assignee
        TaskCard newCard = new TaskCard(savedTask, 1); // default priority
        taskCardRepository.save(newCard);
        return savedTask;
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable String id, @RequestBody Task updatedTask) {
        return taskRepository.findById(id).map(task -> {
            task.setName(updatedTask.getName());
            task.setAssigneeId(updatedTask.getAssigneeId());
            task.setReporterId(updatedTask.getReporterId());
            task.setStatusColumnId(updatedTask.getStatusColumnId());
            task.setIssuedTime(updatedTask.getIssuedTime());
            task.setDueTime(updatedTask.getDueTime());
            task.setVisibility(updatedTask.isVisibility());
            task.setAttachedTaskIds(updatedTask.getAttachedTaskIds());
            task.setCommentIds(updatedTask.getCommentIds());
            task.setHistoryIds(updatedTask.getHistoryIds());
            task.setDescription(updatedTask.getDescription());

            Task savedTask = taskRepository.save(task);
            // Keep TaskCard in sync
            Optional<TaskCard> cardOpt = taskCardRepository.findById(savedTask.getId());
            cardOpt.ifPresent(card -> {
                card.syncWithTask(savedTask);
                // card.setPriority(...) if needed
                taskCardRepository.save(card);
            });
            return savedTask;
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable String id) {
        taskRepository.deleteById(id);
        // Remove the corresponding TaskCard
        taskCardRepository.deleteById(id);
    }
}
