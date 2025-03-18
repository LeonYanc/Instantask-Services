package com.instantask.service.controller;

import com.instantask.service.model.Comment;
import com.instantask.service.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/comments")
@CrossOrigin(origins = "http://localhost:3000")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable String id) {
        Optional<Comment> comOpt = commentRepository.findById(id);
        return comOpt.orElse(null);
    }

    @PostMapping
    public Comment createComment(@RequestBody Comment comment) {
        return commentRepository.save(comment);
    }

    @PutMapping("/{id}")
    public Comment updateComment(@PathVariable String id, @RequestBody Comment updatedComment) {
        return commentRepository.findById(id).map(existingComment -> {
            existingComment.setIssuedTime(updatedComment.getIssuedTime());
            existingComment.setCommenter(updatedComment.getCommenter());
            existingComment.setRelatedTask(updatedComment.getRelatedTask());
            existingComment.setResponseTo(updatedComment.getResponseTo());
            existingComment.setContent(updatedComment.getContent());
            return commentRepository.save(existingComment);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable String id) {
        commentRepository.deleteById(id);
    }
}
