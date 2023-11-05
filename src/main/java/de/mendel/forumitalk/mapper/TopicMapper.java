package de.mendel.forumitalk.mapper;

import de.mendel.forumitalk.dto.TopicDto;
import de.mendel.forumitalk.model.Topic;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TopicMapper {

    public TopicDto mapToDto(Topic topic) {
        return new TopicDto(topic.getTitle(), topic.getDescription());
    }

    public List<TopicDto> mapToDtoList(List<Topic> topics) {
        List<TopicDto> topicsDto = new ArrayList<>();
        topics.forEach((topic -> topicsDto.add(mapToDto(topic))));
        return topicsDto;
    }
}
