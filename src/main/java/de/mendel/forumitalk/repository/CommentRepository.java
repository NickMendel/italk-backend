package de.mendel.forumitalk.repository;

import de.mendel.forumitalk.model.Comment;
import de.mendel.forumitalk.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByTopic(Topic topic);
}
