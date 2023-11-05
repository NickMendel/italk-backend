package de.mendel.forumitalk.controller;

import de.mendel.forumitalk.dto.SectionDto;
import de.mendel.forumitalk.dto.TopicDto;
import de.mendel.forumitalk.service.SectionService;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sections")
@CrossOrigin("*")
@RequiredArgsConstructor
public class SectionController {

    private final SectionService service;

    @PostMapping
    public ResponseEntity<SectionDto> createSection(@RequestBody SectionDto sectionDto) {
        try {
            SectionDto createdSectionDto = service.createSection(sectionDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSectionDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SectionDto> getSectionById(@PathVariable Long id) {
        SectionDto sectionDto = service.getSectionById(id);
        if (sectionDto != null) {
            return ResponseEntity.ok(sectionDto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/topics")
    public ResponseEntity<List<TopicDto>> getSectionsTopics(@PathVariable Long id) {
        List<TopicDto> topics = service.getSectionsTopics(id);
        return ResponseEntity.ok(topics);
    }

    @GetMapping
    public ResponseEntity<List<SectionDto>> getAllSections() {
        List<SectionDto> sections = service.getAllSections();
        return ResponseEntity.ok(sections);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSection(@PathVariable Long id) {
        service.deleteSectionById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSection(@PathVariable Long id, @RequestBody SectionDto sectionDto) {
        try {
            service.updateSection(id, sectionDto);
            return ResponseEntity.ok("Section updated successfully!");
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation Error: " + e.getMessage());
        }
    }
}

