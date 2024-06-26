package io.paioneer.nain.blockMember.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class BlockMemberDto {
    private Long blockNo;
    private Long memberNo;
    private String blockYN;
    private String blockComment;
    private String blockDate;
}
