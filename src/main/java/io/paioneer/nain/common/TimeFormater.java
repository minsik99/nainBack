package io.paioneer.nain.common;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class TimeFormater {

    public static Date TimeCalculate(){
        LocalDateTime localdateTime = LocalDateTime.now();
        ZoneId zoneId = ZoneId.of("Asia/Seoul");
        ZonedDateTime seoulTime = localdateTime.atZone(zoneId);
        return Date.from(seoulTime.toInstant());
    }
}
