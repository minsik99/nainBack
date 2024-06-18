package io.paioneer.nain.manager.model.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdminListDto {
    private String memberEmail;
    private String memberName;
    private String memberNickname;
}
