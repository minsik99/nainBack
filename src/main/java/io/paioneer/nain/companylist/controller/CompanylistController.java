package io.paioneer.nain.companylist.controller;

import io.paioneer.nain.companylist.model.dto.CompanylistDto;
import io.paioneer.nain.companylist.model.service.CompanylistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/companylist")
public class CompanylistController {
    private final CompanylistService companylistService;

    public ResponseEntity<List<CompanylistDto>> selectCompanyList(@PathVariable("page") int page,String sorting) {
        PageRequest pageable = PageRequest.of(0, page,Sort.by("sorting"));
        List<CompanylistDto> companylistDto = companylistService.selectCompanyList(pageable);
        return new ResponseEntity<>(companylistDto, HttpStatus.OK);
    }
}
