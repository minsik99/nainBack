package io.paioneer.nain.statistical.model.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NewMemberDto {
    private String date;
    private int member;
}
