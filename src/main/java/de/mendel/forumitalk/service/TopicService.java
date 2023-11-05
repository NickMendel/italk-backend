package de.mendel.forumitalk.service;

import de.mendel.forumitalk.dto.TopicDto;
import de.mendel.forumitalk.exceptions.NotFoundException;
import de.mendel.forumitalk.mapper.TopicMapper;
import de.mendel.forumitalk.model.Section;
import de.mendel.forumitalk.model.Topic;
import de.mendel.forumitalk.model.User;
import de.mendel.forumitalk.repository.SectionRepository;
import de.mendel.forumitalk.repository.TopicRepository;
import de.mendel.forumitalk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;
    private final SectionRepository sectionRepository;
    private final TopicMapper topicMapper;
    private final UserRepository userRepository;


    @Transactional
    public void createTopic(Long section_id, String username, TopicDto topicDto) {
        User user = userRepository.findByUsername(username);
        Section section = sectionRepository.findById(section_id).orElseThrow(() ->
                new NotFoundException("Section with ID: " + section_id + " does not exist!"));
        Topic topic = new Topic();
        topic.setTitle(topicDto.getTitle());
        topic.setDescription(topicDto.getDescription());
        topic.setSection(section);
        topic.setUser(user);

        topicRepository.save(topic);
    }

    @Transactional(readOnly = true)
    public TopicDto getTopicById(Long id) {
        Topic topic = topicRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Topic with ID: " + id + " does not exist!"));
        return topicMapper.mapToDto(topic);
    }

    @Transactional(readOnly = true)
    public List<TopicDto> getTopicsForDashboard(String username) {
        User user = userRepository.findByUsername(username);
        List<Topic> topics = user.getTopics();
        return topicMapper.mapToDtoList(topics);
    }

    @Transactional
    public void deleteTopic(TopicDto topicDto) {
        Topic topic = topicRepository.findByTitle(topicDto.getTitle());
        if (topic != null) {
            topicRepository.delete(topic);
        } else {
            throw new NotFoundException("Topic with title: " + topicDto.getTitle() + " does not exist");
        }
    }

    @Transactional
    public void deleteTopicById(Long id) {
        Topic topic = topicRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Topic with ID: " + id + " does not exist!"));
        topicRepository.deleteById(topic.getTopic_id());
    }

    @Transactional
    public void updateTopic(Long id, TopicDto topicDto) {
        Topic topic = topicRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Topic with ID: " + id + " does not exist!"));

        topic.setTitle(topicDto.getTitle());
        topic.setDescription(topicDto.getDescription());
        topicRepository.save(topic);
    }
}
