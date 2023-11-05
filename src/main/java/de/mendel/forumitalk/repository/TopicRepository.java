package de.mendel.forumitalk.repository;

import de.mendel.forumitalk.model.Section;
import de.mendel.forumitalk.model.Topic;
import de.mendel.forumitalk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

    Topic findByTitle(String title);

    /* TODO: Implementation of findTopicByUser for User Dashboard
    List<Topic> findTopicsByUser(User user);
     */
}
