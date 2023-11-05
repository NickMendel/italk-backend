package de.mendel.forumitalk.controller;

import de.mendel.forumitalk.dto.CommentDto;
import de.mendel.forumitalk.service.CommentService;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/topics/{topic_id}/comments")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<String> createComment(@PathVariable Long topic_id,
                                                @RequestBody String username, @RequestBody CommentDto commentDto) {
        try {
            commentService.createComment(topic_id, username, commentDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Comment created successfully!");
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation Error: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<CommentDto>> getCommentsByTopic(@PathVariable Long topic_id) {
        List<CommentDto> comments = commentService.getCommentsByTopic(topic_id);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable Long id) {
        CommentDto commentDto = commentService.getCommentById(id);
        if (commentDto != null) {
            return ResponseEntity.ok(commentDto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommentById(@PathVariable Long id) {
        commentService.deleteCommentById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateComment(@PathVariable Long id, @RequestBody CommentDto commentDto) {
        commentService.updateComment(id, commentDto);
        return ResponseEntity.ok("Comment updated successfully!");
    }
}

