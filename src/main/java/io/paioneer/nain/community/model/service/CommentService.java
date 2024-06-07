package io.paioneer.nain.community.model.service;

import io.paioneer.nain.community.jpa.entity.CommentEntity;
import io.paioneer.nain.community.jpa.repository.comment.CommentRepositoryImpl;
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
    private final CommentRepositoryImpl commentRepository;

    public ArrayList<CommentDto> selectList(Long communityNo) {
        List<CommentEntity> commentEntities =  commentRepository.findList(communityNo);
        ArrayList<CommentDto> list = new ArrayList<>();
        for (CommentEntity commentEntity : commentEntities) {
            list.add(new commentEntity.toDto());
        }
        return list;
    }

    public CommentDto insertComment(CommentDto commentDto) {
    }

    public CommentDto updateComment(CommentDto commentDto) {
    }

    public CommentDto deleteComment(Long commentNo) {
    }
}
