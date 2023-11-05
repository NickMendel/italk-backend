package de.mendel.forumitalk.mapper;

import de.mendel.forumitalk.dto.SectionDto;
import de.mendel.forumitalk.model.Section;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SectionMapper {
    private final TopicMapper topicMapper;

    public SectionDto mapToDto(Section section) {
        return new SectionDto(section.getTitle(), section.getDescription());
    }

    public List<SectionDto> mapToDtoList(List<Section> sections) {
        List<SectionDto> sectionsDto = new ArrayList<>();
        sections.forEach((section -> sectionsDto.add(mapToDto(section))));
        return sectionsDto;
    }
}
