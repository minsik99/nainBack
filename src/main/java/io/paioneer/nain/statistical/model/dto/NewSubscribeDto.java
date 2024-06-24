package io.paioneer.nain.statistical.model.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NewSubscribeDto {
    private String date;
    private int Subscriber;
}
