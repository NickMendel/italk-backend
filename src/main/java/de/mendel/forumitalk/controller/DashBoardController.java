package de.mendel.forumitalk.controller;

import de.mendel.forumitalk.dto.TopicDto;
import de.mendel.forumitalk.model.User;
import de.mendel.forumitalk.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DashBoardController {

    private final TopicService topicService;

    @GetMapping("/api/v1/dashboard")
    public ResponseEntity<List<TopicDto>> getTopicsForDashboard(@RequestBody User user) {
        return ResponseEntity.ok(topicService.getTopicsForDashboard(user));
    }
}
