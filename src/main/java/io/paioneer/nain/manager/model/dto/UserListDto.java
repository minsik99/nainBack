package io.paioneer.nain.manager.model.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserListDto {
    private String memberEmail;
    private String memberName;
    private String memberNickName;
    private String subscribeYN;
    private String blockYN;
    private String blockComment;
}
