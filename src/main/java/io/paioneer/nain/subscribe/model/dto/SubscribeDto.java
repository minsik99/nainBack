package io.paioneer.nain.subscribe.model.dto;

import io.paioneer.nain.member.model.dto.MemberDto;
import io.paioneer.nain.subscribe.jpa.entity.SubscribeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class SubscribeDto {
    private int payNo;
    private MemberDto memberdto;
    private int payAmount;
    private Date paymentDate;
    private Date expireDate;

    public SubscribeEntity toEntity() {
        return SubscribeEntity.builder()
                .payNo(this.payNo)
                .memberEntity(this.memberdto.toEntity())
                .payAmount(this.payAmount)
                .paymentDate(this.paymentDate)
                .expiryDate(this.expireDate)
                .build();
    }
}