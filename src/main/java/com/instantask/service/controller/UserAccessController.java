package com.instantask.service.controller;

import com.instantask.service.dto.UserAccessInviteRequestDto;
import com.instantask.service.dto.BoardUserListResponseDto;
import com.instantask.service.model.UserAccess;
import com.instantask.service.service.UserAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing user-board access (roles).
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/user-access")
@CrossOrigin(origins = "http://localhost:3000")
public class UserAccessController {

    @Autowired
    private UserAccessService userAccessService;

    /**
     * Invite an existing user (by email) to a board with a role.
     * POST /api/user-access/invite
     * Body: { "email":"xxx", "boardId":"...", "role":"ADMIN" }
     */
    @PostMapping("/invite")
    public ResponseEntity<?> inviteUserToBoard(@RequestBody UserAccessInviteRequestDto dto) {
        try {
            UserAccess userAccess = userAccessService.inviteUserToBoard(dto);
            return ResponseEntity.ok(userAccess);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Remove user from a board
     * DELETE /api/user-access/remove?userId=xxx&boardId=yyy
     */
    @DeleteMapping("/remove")
    public ResponseEntity<?> removeUserFromBoard(
            @RequestParam String userId,
            @RequestParam String boardId) {
        try {
            userAccessService.removeUserFromBoard(userId, boardId);
            return ResponseEntity.ok("User " + userId + " removed from board " + boardId + " successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    /**
     * List all userAccess records for a board
     * GET /api/user-access/board/{boardId}
     */
    @GetMapping("/board/{boardId}")
    public ResponseEntity<?> getBoardMembers(@PathVariable String boardId) {
        return ResponseEntity.ok(userAccessService.getBoardMembers(boardId));
    }

    /**
     * Get single user's access info for a board
     * GET /api/user-access/{boardId}/user/{userId}
     */
    @GetMapping("/{boardId}/user/{userId}")
    public ResponseEntity<?> getUserAccess(@PathVariable String boardId,
                                           @PathVariable String userId) {
        UserAccess ua = userAccessService.getUserAccess(userId, boardId);
        if (ua == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ua);
    }

    @GetMapping("/board/{boardId}/user-list")
    public ResponseEntity<BoardUserListResponseDto> getBoardUserList(@PathVariable String boardId) {
        BoardUserListResponseDto result = userAccessService.getBoardUserList(boardId);
        return ResponseEntity.ok(result);
    }

}
