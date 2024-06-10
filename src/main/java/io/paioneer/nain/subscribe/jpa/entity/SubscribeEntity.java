package io.paioneer.nain.subscribe.jpa.entity;

import io.paioneer.nain.member.jpa.entity.MemberEntity;
import io.paioneer.nain.subscribe.model.dto.SubscribeDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_SUBSCRIBE")
public class SubscribeEntity {
    @Id
    @Column(name = "PAY_NO", nullable = false)
    private int payNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_NO", referencedColumnName = "MEMBER_NO")
    private MemberEntity memberEntity;

    @Column(name = "PAY_AMOUNT")
    private int payAmount;

    @Column(name = "PAYMENT_DATE")
    private Date paymentDate;

    @Column(name = "EXPIRY_DATE")
    private Date expiryDate;

    @PrePersist  //jpa 로 넘어가지 전에 작동
    public void prePersist() {
        paymentDate = new java.sql.Date(System.currentTimeMillis());
    }

    public SubscribeDto toDto(){
        return SubscribeDto.builder()
                .payNo(this.payNo)
                .memberdto(this.memberEntity.toDto())
                .payAmount(this.payAmount)
                .paymentDate(this.paymentDate)
                .expireDate(this.expiryDate)
                .build();
    }
}
