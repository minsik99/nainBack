package io.paioneer.nain.manager.jpa.repository;

import io.paioneer.nain.manager.model.dto.AdminListDto;

import java.util.List;

public interface AdminManagerRepositoryCustom {
    List<AdminListDto> adminList();
}
