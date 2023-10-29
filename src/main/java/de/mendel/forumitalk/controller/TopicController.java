package de.mendel.forumitalk.controller;

import de.mendel.forumitalk.dto.SectionDto;
import de.mendel.forumitalk.dto.TopicDto;
import de.mendel.forumitalk.service.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sections/{section_id}/topics/")
public class TopicController {

    private final TopicService service;

    public TopicController(TopicService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TopicDto> createTopic(@RequestBody TopicDto topicDto) {
        TopicDto createdTopic = service.createTopic(topicDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTopic);
    }

    @GetMapping("/{topic_id}")
    public ResponseEntity<TopicDto> getTopicById(@PathVariable Long topic_id) {
        TopicDto topicDto = service.getTopicById(topic_id);
        if (topicDto != null) {
            return ResponseEntity.ok(topicDto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{topic_id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long topic_id) {
        service.deleteTopic(topic_id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<TopicDto> updateTopic(@RequestBody TopicDto topicDto) {
        TopicDto updatedTopic = service.updateTopic(topicDto);
        return ResponseEntity.ok(updatedTopic);
    }

    @GetMapping
    public ResponseEntity<List<TopicDto>> getTopicsBySection(@RequestBody SectionDto sectionDto) {
        List<TopicDto> topics = service.getTopicsBySection(sectionDto);
        return ResponseEntity.ok(topics);
    }
}
