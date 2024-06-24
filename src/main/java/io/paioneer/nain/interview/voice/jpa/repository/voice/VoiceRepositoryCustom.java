package io.paioneer.nain.interview.voice.jpa.repository.voice;

import java.util.ArrayList;

public interface VoiceRepositoryCustom {
    long getAnswerCount(Long itvNo);
    ArrayList getParagraph(Long itvNo);
}
