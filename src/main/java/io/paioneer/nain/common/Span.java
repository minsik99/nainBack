package io.paioneer.nain.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Span {
    private LocalDate begin;
    private LocalDate end;
}
