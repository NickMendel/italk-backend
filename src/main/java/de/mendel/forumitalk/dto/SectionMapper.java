package de.mendel.forumitalk.dto;

import de.mendel.forumitalk.model.Section;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SectionMapper {

    public SectionDto mapToDto(Section section) {
        return new SectionDto(section.getSection_id(), section.getName(), section.getDescription(), section.getTopics());
    }

    public Section mapToEntity(SectionDto sectionDto) {
        return new Section(sectionDto.getId(), sectionDto.getName(), sectionDto.getDescription(), sectionDto.getTopics());
    }

    public List<SectionDto> mapToDtoList(List<Section> sections) {
        List<SectionDto> sectionsDto = new ArrayList<>();
        sections.forEach((section -> sectionsDto.add(mapToDto(section))));
        return sectionsDto;
    }
}
