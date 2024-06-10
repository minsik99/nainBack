package io.paioneer.nain.community.model.service;

import io.paioneer.nain.community.jpa.entity.CommentEntity;
import io.paioneer.nain.community.jpa.repository.comment.CommentRepository;
import io.paioneer.nain.community.model.dto.CommentDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    //댓글 목록 조회
    public ArrayList<CommentDto> selectList(Long communityNo) {
        List<CommentEntity> commentEntities =  commentRepository.findList(communityNo);
        ArrayList<CommentDto> list = new ArrayList<>();
        for (CommentEntity commentEntity : commentEntities) {
            list.add(commentEntity.toDto());
        }
        return list;
    }

    //댓글 입력
    public void insertComment(CommentDto commentDto) {
        commentRepository.save(commentDto.toEntity());
    }

    //댓글 수정
    public void updateComment(CommentDto commentDto) {
        CommentEntity commentEntity = commentRepository.findById(commentDto.getCommentNo()).get();
        commentEntity.setContent(commentDto.getContent());
        commentEntity.setCommentDate(commentDto.getCommentDate());
        commentRepository.save(commentDto.toEntity());

    }

    //삭제컬럼 값추가
    public void deleteComment(CommentDto commentDto) {
        CommentEntity commentEntity = commentDto.toEntity();
        commentEntity.setDeletedDate(new Date());
        commentRepository.save(commentEntity);
    }

    //DB 행 삭제
    public void terminateComment(Long commentNo) {
        commentRepository.deleteById(commentNo);
    }
}
