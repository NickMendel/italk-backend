package de.mendel.forumitalk.service;

import de.mendel.forumitalk.dao.SectionDao;
import de.mendel.forumitalk.dao.TopicDao;
import de.mendel.forumitalk.dto.SectionDto;
import de.mendel.forumitalk.dto.SectionMapper;
import de.mendel.forumitalk.dto.TopicDto;
import de.mendel.forumitalk.dto.TopicMapper;
import de.mendel.forumitalk.exceptions.NotFoundException;
import de.mendel.forumitalk.model.Section;
import de.mendel.forumitalk.model.Topic;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    private final TopicDao topicDao;
    private final SectionDao sectionDao;
    private final TopicMapper topicMapper;

    private final SectionMapper sectionMapper;

    public TopicService(TopicDao topicDao, SectionDao sectionDao, TopicMapper topicMapper, SectionMapper sectionMapper) {
        this.topicDao = topicDao;
        this.sectionDao = sectionDao;
        this.topicMapper = topicMapper;
        this.sectionMapper = sectionMapper;
    }

    public TopicDto createTopic(TopicDto topicDto) {
        Topic topic = topicMapper.mapToEntity(topicDto);
        Section section = sectionDao.findById(topicDto.getSection().getSection_id());
        topic.setSection(section);
        Topic savedTopic = topicDao.save(topic);

        return topicMapper.mapToDto(savedTopic);
    }

    public TopicDto getTopicById(Long id) {
        Topic topic = topicDao.findById(id);
        if (topic == null) {
            throw new NotFoundException("Topic not find with ID: " + id);
        }
        return topicMapper.mapToDto(topic);
    }

    public void deleteTopic(TopicDto topicDto) {
        Topic topic = topicDao.findById(topicDto.getTopic_id());
        if (topic != null) {
            topicDao.deleteByTopic(topic);
        } else {
            throw new NotFoundException("Topic with ID: " + topicDto.getTopic_id() + " does not exist");
        }
    }

    public void deleteTopicById(Long id) {
        Topic topic = topicDao.findById(id);
        if (topic != null) {
            topicDao.deleteById(id);
        } else {
            throw new NotFoundException("Topic with ID: " + id + " does not exist");
        }
    }

    public TopicDto updateTopic(TopicDto topicDto) {
        Topic existingTopic = topicDao.findById(topicDto.getTopic_id());
        if (existingTopic != null) {
            Topic topic = topicMapper.mapToEntity(topicDto);
            Section section = sectionDao.findById(topicDto.getSection().getSection_id());
            topic.setSection(section);
            Topic savedTopic = topicDao.save(topic);
            return topicMapper.mapToDto(savedTopic);
        } else {
            throw new NotFoundException("Topic with ID: " + topicDto.getTopic_id() + " does not exist");
        }
    }

    public List<TopicDto> getAllTopics() {
        return topicMapper.mapToDtoList(topicDao.findAll());
    }

    public List<TopicDto> getTopicsBySection(SectionDto sectionDto) {
        Section section = sectionMapper.mapToEntity(sectionDto);
        return topicMapper.mapToDtoList(topicDao.findTopicsBySection(section));
    }
}
