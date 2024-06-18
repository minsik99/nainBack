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
    private int blockNo;
    private int memberNo;
    private String BlockYN;
    private String BlockComment;
    private String BlockDate;
}
