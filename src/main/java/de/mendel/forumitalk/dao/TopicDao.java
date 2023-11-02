package de.mendel.forumitalk.dao;

import de.mendel.forumitalk.model.Section;
import de.mendel.forumitalk.model.Topic;
import de.mendel.forumitalk.model.User;
import de.mendel.forumitalk.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TopicDao {

    private final TopicRepository repository;

    @Autowired
    public TopicDao(TopicRepository repository) {
        this.repository = repository;
    }

    public Topic save(Topic topic) {
        return repository.save(topic);
    }

    public Topic findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public void deleteByTopic(Topic topic) {
        repository.delete(topic);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Topic> findAll() {
        return repository.findAll();
    }

    public List<Topic> findTopicsBySection(Section section) {
        return repository.findTopicsBySection(section);
    }

    public List<Topic> findTopicsByUser(User user) {
        return repository.findTopicsByUser(user);
    }
}
