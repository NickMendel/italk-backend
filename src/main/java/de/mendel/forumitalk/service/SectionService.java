package de.mendel.forumitalk.service;

import de.mendel.forumitalk.dao.SectionDao;
import de.mendel.forumitalk.dto.SectionDto;
import de.mendel.forumitalk.dto.SectionMapper;
import de.mendel.forumitalk.exceptions.NotFoundException;
import de.mendel.forumitalk.model.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {

    private final SectionDao sectionDao;
    private final SectionMapper sectionMapper;

    @Autowired
    public SectionService(SectionDao sectionDao, SectionMapper sectionMapper) {
        this.sectionDao = sectionDao;
        this.sectionMapper = sectionMapper;
    }

    public SectionDto createSection(SectionDto sectionDto) {
        if (sectionDao.findByTitle(sectionDto.getTitle()) != null) {
            throw new NotFoundException("Section with name: " + sectionDto.getTitle() + " already exists");
        }
        Section section = sectionMapper.mapToEntity(sectionDto);
        Section savedSection = sectionDao.save(section);

        return sectionMapper.mapToDto(savedSection);
    }

    public SectionDto getSectionById(Long id) {
        Section section = sectionDao.findById(id);
        if (section == null) {
            throw new NotFoundException("Section not find with ID: " + id);
        }
        return sectionMapper.mapToDto(section);
    }

    public void deleteSection(Long id) {
        Section section = sectionDao.findById(id);
        if (section != null) {
            sectionDao.delete(section);
        } else {
            throw new NotFoundException("Section with ID: " + id + " does not exist");
        }
    }

    public SectionDto updateSection(SectionDto sectionDto) {
        Section existingSection = sectionDao.findById(sectionDto.getId());
        if (existingSection != null) {
            Section section = sectionMapper.mapToEntity(sectionDto);
            Section savedSection = sectionDao.save(section);
            return sectionMapper.mapToDto(savedSection);
        } else {
            throw new NotFoundException("Section with ID: " + sectionDto.getId() + " does not exist");
        }
    }

    public List<SectionDto> getAllSections() {
        List<Section> sections = sectionDao.findAll();
        return sectionMapper.mapToDtoList(sections);
    }
}
