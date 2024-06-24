package io.paioneer.nain.manager.jpa.repository;

import io.paioneer.nain.manager.model.dto.AdminListDto;
import io.paioneer.nain.manager.model.dto.UserListDto;

import java.util.List;

public interface UserManagerRepositoryCustom {
    List<UserListDto> userList();
}
