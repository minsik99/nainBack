package io.paioneer.nain.companylist.jpa.repository;

import io.paioneer.nain.companylist.jpa.entity.CompanylistEntity;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CompanylistRepositoryCustom {
    List<CompanylistEntity> selectCompanyList(PageRequest PageRequest);
    List<CompanylistEntity> selectCompanySearchList(String keyword);
    List<CompanylistEntity> selectPostSearchList(String keyword);
}
