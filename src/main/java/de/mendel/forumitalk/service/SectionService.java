package de.mendel.forumitalk.service;

import de.mendel.forumitalk.dto.SectionDto;
import de.mendel.forumitalk.dto.TopicDto;
import de.mendel.forumitalk.exceptions.NotFoundException;
import de.mendel.forumitalk.mapper.SectionMapper;
import de.mendel.forumitalk.mapper.TopicMapper;
import de.mendel.forumitalk.model.Section;
import de.mendel.forumitalk.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SectionService {

    private final SectionRepository sectionRepository;
    private final SectionMapper sectionMapper;
    private final TopicMapper topicMapper;

    @Transactional
    public SectionDto createSection(SectionDto sectionDto) {
        if (sectionRepository.findByTitle(sectionDto.getTitle()) != null) {
            throw new IllegalArgumentException("Section with title: " + sectionDto.getTitle() + " already exists");
        }
        Section section = new Section();
        section.setTitle(sectionDto.getTitle());
        section.setDescription(sectionDto.getDescription());
        sectionRepository.save(section);
        return sectionMapper.mapToDto(section);
    }

    @Transactional(readOnly = true)
    public SectionDto getSectionById(Long id) {
        Section section = sectionRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Section with ID: " + id + " does not exist"));
        return sectionMapper.mapToDto(section);
    }

    @Transactional(readOnly = true)
    public List<SectionDto> getAllSections() {
        List<Section> sections = sectionRepository.findAll();
        return sectionMapper.mapToDtoList(sections);
    }

    @Transactional(readOnly = true)
    public List<TopicDto> getSectionsTopics(Long id) {
        Section section = sectionRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Section with ID: " + id + " does not exist"));
        return topicMapper.mapToDtoList(section.getTopics());
    }

    @Transactional
    public void deleteSectionById(Long id) {
        Section section = sectionRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Section with ID: " + id + " does not exist"));
        sectionRepository.delete(section);
    }

    @Transactional
    public void updateSection(Long id, SectionDto sectionDto) {
        Section existingSection = sectionRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Section with ID: " + id + " does not exist"));
        existingSection.setTitle(sectionDto.getTitle());
        existingSection.setDescription(sectionDto.getDescription());
        sectionRepository.save(existingSection);
    }


}
