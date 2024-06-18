package io.paioneer.nain.blockMember.entity;

import io.paioneer.nain.member.jpa.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name="TB_BLOCK_MEMBER")
@Entity
public class BlockMemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MEMBER_NO")
    @SequenceGenerator(name = "SEQ_MEMBER_NO", sequenceName = "SEQ_MEMBER_NO", allocationSize = 1)
    @Column(name="BLOCK_NO", nullable = false)
    private int blockNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_NO", referencedColumnName = "MEMBER_NO")
    private MemberEntity memberNo2;

    @Column(name="BLOCK_YN")
    private String blockYN;

    @Column(name="BLOCK_COMMENT")
    private String blockComment;

    @Column(name="BLOCK_DATE")
    private Date blockDate;

    @PrePersist
    public void prePersist() {
        blockDate = new java.sql.Date(System.currentTimeMillis());
    }
}
