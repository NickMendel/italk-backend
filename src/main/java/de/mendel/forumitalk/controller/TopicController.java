package de.mendel.forumitalk.controller;

import de.mendel.forumitalk.dto.TopicDto;
import de.mendel.forumitalk.service.TopicService;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/section/{section_id}/topics/")
@CrossOrigin("*")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;

    @PostMapping
    public ResponseEntity<String> createTopic(@PathVariable Long section_id,
                                              @RequestBody String username, @RequestBody TopicDto topicDto) {
        try {
            topicService.createTopic(section_id, username, topicDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Topic created successfully!");
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation Error: " + e.getMessage());
        }
    }

    @GetMapping("/{topic_id}")
    public ResponseEntity<TopicDto> getTopicById(@PathVariable Long topic_id) {
        TopicDto topicDto = topicService.getTopicById(topic_id);
        if (topicDto != null) {
            return ResponseEntity.ok(topicDto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{topic_id}")
    public ResponseEntity<Void> deleteTopicById(@PathVariable Long topic_id) {
        topicService.deleteTopicById(topic_id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTopic(@RequestBody TopicDto topicDto) {
        topicService.deleteTopic(topicDto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{topic_id}")
    public ResponseEntity<String> updateTopic(@PathVariable Long topic_id, @RequestBody TopicDto topicDto) {
        try {
            topicService.updateTopic(topic_id, topicDto);
            return ResponseEntity.ok("Topic updated successfully!");
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation Error: " + e.getMessage());
        }
    }
}
