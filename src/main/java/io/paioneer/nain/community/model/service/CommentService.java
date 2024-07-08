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

    public CommentDto getComment(Long commentNo) {
        return commentRepository.findById(commentNo).get().toDto();
    }

    //댓글 목록 조회
    public ArrayList<CommentDto> selectList(Long communityNo) {
        List<CommentEntity> commentEntities =  commentRepository.findList(communityNo);
        for(CommentEntity comment : commentEntities){
            log.info("순서 " + comment.getCommentNo().toString());
        }
        ArrayList<CommentDto> list = new ArrayList<>();
        Long parentNo = 0L;
        for(int i = 0; i < commentEntities.size(); i++) {
            // 원댓글이면
            if(commentEntities.get(i).getParentCommentNo() == null) {
                //원댓글 번호
                parentNo = commentEntities.get(i).getCommentNo();
                // 원댓글 저장
                list.add(commentEntities.get(i).toDto());

                // 뒷 값들 중 자식 댓글들을 저장
                for(int j = i + 1; j < commentEntities.size(); j++) {
                    if(commentEntities.get(j).getParentCommentNo() == parentNo) {
                        list.add(commentEntities.get(j).toDto());
                    }
                }
            }

        }
        return list;
    }

    //댓글 입력
    public void insertComment(CommentDto commentDto) {
        commentRepository.save(commentDto.toEntity());
    }

    //댓글 수정
    public void updateComment(CommentDto commentDto) {
        commentRepository.save(commentDto.toEntity());
    }

    //삭제컬럼 값추가
    public void deleteComment(CommentDto commentDto) {
        commentRepository.save(commentDto.toEntity());
    }

    //DB 행 삭제
    public void terminateComment(Long commentNo) {
        commentRepository.deleteById(commentNo);
    }
}
