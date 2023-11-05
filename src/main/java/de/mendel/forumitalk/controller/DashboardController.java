package de.mendel.forumitalk.controller;

import de.mendel.forumitalk.dto.TopicDto;
import de.mendel.forumitalk.model.User;
import de.mendel.forumitalk.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api/v1/dashboard")
@RequiredArgsConstructor
@CrossOrigin("*")
public class DashboardController {

    private final TopicService topicService;

    @GetMapping
    public ResponseEntity<List<TopicDto>> getTopicsForDashboard(@RequestBody String username) {
        return ResponseEntity.ok(topicService.getTopicsForDashboard(username));
    }
}
