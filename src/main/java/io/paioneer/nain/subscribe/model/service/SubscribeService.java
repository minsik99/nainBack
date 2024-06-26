package io.paioneer.nain.subscribe.model.service;

import io.paioneer.nain.member.jpa.entity.MemberEntity;
import io.paioneer.nain.member.jpa.repository.MemberRepository;
import io.paioneer.nain.subscribe.jpa.entity.SubscribeEntity;
import io.paioneer.nain.subscribe.model.dto.SubscribeDto;

import io.paioneer.nain.subscribe.repository.SubscribeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;
    private final MemberRepository memberRepository;


    public SubscribeService(SubscribeRepository subscribeRepository, MemberRepository memberRepository) {
        this.subscribeRepository = subscribeRepository;
        this.memberRepository = memberRepository;
    }


    public SubscribeDto createSubscribe(Long memberNo, int payAmount){
        MemberEntity memberEntity = memberRepository.findById(memberNo)
                .orElseThrow(() -> new RuntimeException("Invalid member id: " + memberNo));

        SubscribeEntity subscribeEntity = SubscribeEntity.builder()
                .memberEntity(memberEntity)
                .payAmount(payAmount)
                .paymentDate(new Date())
                .expiryDate(calculateExpiryDate(new Date()))
                .build();

        SubscribeEntity savedEntity = subscribeRepository.save(subscribeEntity);

        return savedEntity.toDto();
    }

    private Date calculateExpiryDate(Date paymentDate) {
        // paymentDate를 LocalDate로 변환
        LocalDate localPaymentDate = paymentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // 30일 추가
        LocalDate localExpiryDate = localPaymentDate.plusDays(30);

        // LocalDate를 다시 Date로 변환
        return Date.from(localExpiryDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

}
