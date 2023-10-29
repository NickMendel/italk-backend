package de.mendel.forumitalk.controller;

import de.mendel.forumitalk.dto.SectionDto;
import de.mendel.forumitalk.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sections/")
public class SectionController {

    private final SectionService service;

    @Autowired
    public SectionController(SectionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SectionDto> createSection(@RequestBody SectionDto sectionDto) {
        SectionDto createdSection = service.createSection(sectionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSection);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SectionDto> getSectionById(@PathVariable Long id) {
        SectionDto sectionDto = service.getSectionById(id);
        if (sectionDto != null) {
            return ResponseEntity.ok(sectionDto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSection(@PathVariable Long id) {
        service.deleteSection(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<SectionDto> updateSection(@RequestBody SectionDto sectionDto) {
        SectionDto updatedSection = service.updateSection(sectionDto);
        return ResponseEntity.ok(updatedSection);
    }

    @GetMapping
    public ResponseEntity<List<SectionDto>> getAllSections() {
        List<SectionDto> sections = service.getAllSections();
        return ResponseEntity.ok(sections);
    }
}

