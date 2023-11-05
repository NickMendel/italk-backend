package de.mendel.forumitalk.mapper;

import de.mendel.forumitalk.dto.CommentDto;
import de.mendel.forumitalk.model.Comment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentMapper {

    public CommentDto mapToDto(Comment comment) {
        return new CommentDto(comment.getBody());
    }

    public List<CommentDto> MapToDtoList(List<Comment> comments) {
        List<CommentDto> commentsDto = new ArrayList<>();
        comments.forEach((comment -> commentsDto.add(mapToDto(comment))));
        return commentsDto;
    }

}
