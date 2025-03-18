package com.instantask.service.controller;

import com.instantask.service.model.Board;
import com.instantask.service.model.UserAccess;
import com.instantask.service.repository.BoardRepository;
import com.instantask.service.repository.UserAccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/boards")
@CrossOrigin(origins = "http://localhost:3000")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserAccessRepository userAccessRepository;

    @GetMapping
    public List<Board> getBoards(@RequestParam(name = "userId", required = false) String userId) {
        if (userId != null && !userId.isEmpty()) {
            List<UserAccess> userAccessList = userAccessRepository.findByUserId(userId);

            List<String> boardIds = userAccessList.stream()
                    .map(UserAccess::getBoardId)
                    .distinct()
                    .toList();

            return boardRepository.findAllById(boardIds);
        } else {
            return boardRepository.findAll();
        }
    }

    @GetMapping("/{id}")
    public Board getBoardById(@PathVariable String id) {
        Optional<Board> boardOpt = boardRepository.findById(id);
        return boardOpt.orElse(null);
    }

    @PostMapping
    public Board createBoard(@RequestBody Board board) {
        return boardRepository.save(board);
    }

    @PutMapping("/{id}")
    public Board updateBoard(@PathVariable String id, @RequestBody Board updatedBoard) {
        return boardRepository.findById(id).map(existingBoard -> {
            existingBoard.setName(updatedBoard.getName());
            existingBoard.setCode(updatedBoard.getCode());
            existingBoard.setColumns(updatedBoard.getColumns());
            existingBoard.setMembers(updatedBoard.getMembers());
            return boardRepository.save(existingBoard);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable String id) {
        boardRepository.deleteById(id);
    }
}

