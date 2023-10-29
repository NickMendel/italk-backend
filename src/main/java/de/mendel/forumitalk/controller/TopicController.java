package de.mendel.forumitalk.controller;

import de.mendel.forumitalk.dto.SectionDto;
import de.mendel.forumitalk.dto.TopicDto;
import de.mendel.forumitalk.service.SectionService;
import de.mendel.forumitalk.service.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/sections/{section_id}/topics/")
public class TopicController {

    private final TopicService topicService;

    private final SectionService sectionService;


    public TopicController(TopicService topicService, SectionService sectionService) {
        this.topicService = topicService;
        this.sectionService = sectionService;
    }

    @PostMapping
    public ResponseEntity<TopicDto> createTopic(@RequestBody TopicDto topicDto, @PathVariable Long section_id) {
        TopicDto createdTopic = topicService.createTopic(topicDto, section_id);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTopic);
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
    public ResponseEntity<Void> deleteTopic(@PathVariable Long topic_id) {
        topicService.deleteTopic(topic_id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<TopicDto> updateTopic(@RequestBody TopicDto topicDto) {
        TopicDto updatedTopic = topicService.updateTopic(topicDto);
        return ResponseEntity.ok(updatedTopic);
    }

    @GetMapping
    public ResponseEntity<List<TopicDto>> getTopicsBySection(@PathVariable Long section_id) {
        SectionDto sectionDto = sectionService.getSectionById(section_id);
        List<TopicDto> topics = topicService.getTopicsBySection(sectionDto);
        return ResponseEntity.ok(topics);
    }
}
