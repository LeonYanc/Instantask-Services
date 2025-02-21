package com.instantask.service.controller;

import com.instantask.service.model.Task;
import com.instantask.service.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

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
        return taskRepository.save(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable String id, @RequestBody Task updatedTask) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setName(updatedTask.getName());
                    task.setAssignee(updatedTask.getAssignee());
                    task.setReporter(updatedTask.getReporter());
                    task.setStatus(updatedTask.getStatus());
                    task.setIssuedTime(updatedTask.getIssuedTime());
                    task.setDueTime(updatedTask.getDueTime());
                    task.setVisibility(updatedTask.isVisibility());
                    task.setAttachedTask(updatedTask.getAttachedTask());
                    task.setComments(updatedTask.getComments());
                    task.setHistory(updatedTask.getHistory());
                    task.setDescription(updatedTask.getDescription());
                    return taskRepository.save(task);
                })
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable String id) {
        taskRepository.deleteById(id);
    }
}
