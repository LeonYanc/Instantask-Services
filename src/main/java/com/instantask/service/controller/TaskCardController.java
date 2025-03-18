package com.instantask.service.controller;

import com.instantask.service.model.Task;
import com.instantask.service.model.TaskCard;
import com.instantask.service.repository.TaskCardRepository;
import com.instantask.service.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/taskcards")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskCardController {

    @Autowired
    private TaskCardRepository taskCardRepository;

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public List<TaskCard> getAllTaskCards() {
        return taskCardRepository.findAll();
    }

    @GetMapping("/{id}")
    public TaskCard getTaskCardById(@PathVariable String id) {
        Optional<TaskCard> cardOpt = taskCardRepository.findById(id);
        return cardOpt.orElse(null);
    }

    /**
     * Create a TaskCard only if Task with the same ID exists.
     * The assigneeId must match Task.assigneeId at all times.
     */
    @PostMapping
    public TaskCard createTaskCard(@RequestBody TaskCard newCard) {
        // We expect newCard.id to be the same as Task.id
        String taskId = newCard.getId();
        if (taskId == null || taskId.isEmpty()) {
            throw new IllegalArgumentException("TaskCard must have an 'id' matching an existing Task.");
        }

        // Check if Task exists
        Optional<Task> taskOpt = taskRepository.findById(taskId);
        if (taskOpt.isEmpty()) {
            throw new IllegalArgumentException("No Task found with id=" + taskId);
        }

        Task task = taskOpt.get();
        // Force synchronization with Task data
        newCard.syncWithTask(task);
        // Now we can save
        return taskCardRepository.save(newCard);
    }

    /**
     * Update a TaskCard only if Task with the same ID exists.
     * Always sync certain fields from Task.
     */
    @PutMapping("/{id}")
    public TaskCard updateTaskCard(@PathVariable String id, @RequestBody TaskCard updatedCard) {
        Optional<Task> taskOpt = taskRepository.findById(id);
        if (taskOpt.isEmpty()) {
            throw new IllegalArgumentException("No Task found with id=" + id);
        }
        Task task = taskOpt.get();

        return taskCardRepository.findById(id).map(card -> {
            card.setId(id);

            card.syncWithTask(task);

            card.setPriority(updatedCard.getPriority());
            return taskCardRepository.save(card);

        }).orElseGet(() -> {
            updatedCard.setId(id);
            updatedCard.syncWithTask(task);
            return taskCardRepository.save(updatedCard);
        });
    }

    @DeleteMapping("/{id}")
    public void deleteTaskCard(@PathVariable String id) {
        taskCardRepository.deleteById(id);
    }
}
