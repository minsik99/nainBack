package io.paioneer.nain.Payment.model.service;

import io.paioneer.nain.member.jpa.entity.MemberEntity;
import io.paioneer.nain.member.jpa.repository.MemberRepository;
import io.paioneer.nain.subscribe.jpa.entity.SubscribeEntity;
import io.paioneer.nain.subscribe.repository.SubscribeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PaymentService {
    private final MemberRepository memberRepository;
    private final SubscribeRepository subscribeRepository;

    public void processSubscription(Long memberNo, int amount) {
        MemberEntity memberEntity = memberRepository.findById(memberNo)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        // 구독 정보 업데이트
        memberEntity.setSubscribeYN("Y");
        memberRepository.save(memberEntity);

        // 구독 엔티티 생성 및 저장
        SubscribeEntity subscribeEntity = new SubscribeEntity();
        subscribeEntity.setMemberEntity(memberEntity); // memberEntity 설정
        subscribeEntity.setPayAmount(amount);
        subscribeEntity.setPaymentDate(new Date());

        // 구독 만료일은 결제일로부터 한 달 후로 설정
        LocalDate expiryDate = LocalDate.now().plusMonths(1);
        subscribeEntity.setExpiryDate(Date.from(expiryDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));

        subscribeRepository.save(subscribeEntity);
    }
}