package de.mendel.forumitalk.controller;

import de.mendel.forumitalk.dto.SectionDto;
import de.mendel.forumitalk.dto.TopicDto;
import de.mendel.forumitalk.model.Section;
import de.mendel.forumitalk.service.SectionService;
import de.mendel.forumitalk.service.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/topics/")
public class TopicController {

    private final TopicService topicService;

    private final SectionService sectionService;


    public TopicController(TopicService topicService, SectionService sectionService) {
        this.topicService = topicService;
        this.sectionService = sectionService;
    }

    @PostMapping
    public ResponseEntity<TopicDto> createTopic(@RequestBody TopicDto topicDto) {
        TopicDto createdTopic = topicService.createTopic(topicDto);
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
    public ResponseEntity<Void> deleteTopicById(@PathVariable Long topic_id) {
        topicService.deleteTopicById(topic_id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTopic(@RequestBody TopicDto topicDto) {
        topicService.deleteTopic(topicDto);
        System.out.println("Topic with ID: " + topicDto.getTopic_id() + " was deleted");
        System.out.println(topicService.getTopicById(topicDto.getTopic_id()));
        return ResponseEntity.noContent().build();
    }


    @PutMapping
    public ResponseEntity<TopicDto> updateTopic(@RequestBody TopicDto topicDto) {
        TopicDto updatedTopic = topicService.updateTopic(topicDto);
        return ResponseEntity.ok(updatedTopic);
    }

    @GetMapping
    public ResponseEntity<List<TopicDto>> getTopicsBySection(@RequestBody Section section) {
        SectionDto sectionDto = sectionService.getSectionById(section.getSection_id());
        List<TopicDto> topics = topicService.getTopicsBySection(sectionDto);
        return ResponseEntity.ok(topics);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TopicDto>> getTopicsBySection() {
        List<TopicDto> topics = topicService.getAllTopics();
        return ResponseEntity.ok(topics);
    }
}
