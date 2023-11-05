package de.mendel.forumitalk.service;

import de.mendel.forumitalk.dto.CommentDto;
import de.mendel.forumitalk.exceptions.NotFoundException;
import de.mendel.forumitalk.mapper.CommentMapper;
import de.mendel.forumitalk.model.Comment;
import de.mendel.forumitalk.model.Topic;
import de.mendel.forumitalk.model.User;
import de.mendel.forumitalk.repository.CommentRepository;
import de.mendel.forumitalk.repository.TopicRepository;
import de.mendel.forumitalk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final TopicRepository topicRepository;
    private final UserRepository userRepository;


    @Transactional(readOnly = true)
    public List<CommentDto> getCommentsByTopic(Long topic_id) {
        Topic topic = topicRepository.findById(topic_id).orElseThrow(
                () -> new NotFoundException("Topic not find with ID: " + topic_id)
        );
        return commentMapper.MapToDtoList(commentRepository.findAllByTopic(topic));
    }

    @Transactional
    public void deleteCommentById(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Comment not find with ID: " + id)
        );
        commentRepository.delete(comment);
    }

    @Transactional
    public void createComment(Long topic_id, String username, CommentDto commentDto) {
        User user = userRepository.findByUsername(username);
        Topic topic = topicRepository.findById(topic_id).orElseThrow(
                () -> new NotFoundException("Topic not find with ID: " + topic_id)
        );
        Comment comment = new Comment();
        comment.setBody(commentDto.getBody());
        comment.setUser(user);
        comment.setTopic(topic);
        commentRepository.save(comment);
    }

    @Transactional(readOnly = true)
    public CommentDto getCommentById(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Comment not find with ID: " + id)
        );
        return commentMapper.mapToDto(comment);
    }

    @Transactional
    public void updateComment(Long comment_id, CommentDto commentDto) {
        Comment existingComment = commentRepository.findById(comment_id).orElseThrow(
                () -> new NotFoundException("Comment not find with ID: " + comment_id)
        );
        existingComment.setBody(commentDto.getBody());
        existingComment.setUpdated_at(LocalDate.now());
        commentRepository.save(existingComment);
    }
}
