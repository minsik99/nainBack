package io.paioneer.nain.community.jpa.repository.comment;

import io.paioneer.nain.community.jpa.entity.CommentEntity;
import io.paioneer.nain.community.model.dto.CommentDto;

import java.util.ArrayList;
import java.util.List;

public interface CommentRepositoryCustom {
    List<CommentDto> findList(Long communityNo);
}
