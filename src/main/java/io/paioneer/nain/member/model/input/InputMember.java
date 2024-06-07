package io.paioneer.nain.member.model.input;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputMember {
    private String memberEmail, memberPwd;

    public InputMember(String username){
        this.memberEmail = username;
    }
}
