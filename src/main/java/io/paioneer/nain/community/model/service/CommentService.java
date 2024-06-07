package io.paioneer.nain.community.model.service;

import io.paioneer.nain.community.jpa.entity.CommentEntity;
import io.paioneer.nain.community.jpa.repository.comment.CommentRepository;
import io.paioneer.nain.community.model.dto.CommentDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public ArrayList<CommentDto> selectList(Long communityNo) {
        List<CommentEntity> commentEntities =  commentRepository.findList(communityNo);
        ArrayList<CommentDto> list = new ArrayList<>();
        for (CommentEntity commentEntity : commentEntities) {
            list.add(commentEntity.toDto());
        }
        return list;
    }

    public void insertComment(CommentDto commentDto) {
        commentRepository.save(commentDto.toEntity());
    }

    public void updateComment(CommentDto commentDto) {
        CommentEntity commentEntity = commentRepository.findById(commentDto.getCommentNo()).get();
        commentEntity.setContent(commentDto.getContent());
        commentEntity.setCommentDate(commentDto.getCommentDate());
        commentRepository.save(commentDto.toEntity());

    }

    public void deleteComment(Long commentNo) {
        commentRepository.deleteById(commentNo);
    }
}
