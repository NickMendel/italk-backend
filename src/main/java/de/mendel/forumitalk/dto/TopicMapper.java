package de.mendel.forumitalk.dto;

import de.mendel.forumitalk.model.Topic;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TopicMapper {

    public TopicDto mapToDto(Topic topic) {
        return new TopicDto(topic.getId(), topic.getTitle(), topic.getDescription(), topic.getSection(), topic.getAuthor());
    }

    public Topic mapToEntity(TopicDto topicDto) {
        return new Topic(topicDto.getId(), topicDto.getTitle(), topicDto.getDescription(), topicDto.getSection(), topicDto.getAuthor());
    }

    public List<TopicDto> mapToDtoList(List<Topic> topics) {
        List<TopicDto> topicsDto = new ArrayList<>();
        topics.forEach((topic -> topicsDto.add(mapToDto(topic))));
        return topicsDto;
    }

    public List<Topic> mapToEntityList(List<TopicDto> topicsDto) {
        List<Topic> topics = new ArrayList<>();
        topicsDto.forEach((topicDto -> topics.add(mapToEntity(topicDto))));
        return topics;
    }
}
