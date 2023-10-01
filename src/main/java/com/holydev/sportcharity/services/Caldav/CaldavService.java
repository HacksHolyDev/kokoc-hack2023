package com.holydev.sportcharity.services.Caldav;

import com.holydev.sportcharity.entities.utilities.Timer;
import com.holydev.sportcharity.services.EntityBased.utilities.TimerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CaldavService {
    private final TimerService timerService;

    public String createIcsFile(long userId) {
        var timers = timerService.getUserTimers(userId);

        var resultFile = new StringBuilder("BEGIN:VCALENDAR\r\nVERSION:2.0\r\n");
        for (var timer : timers) {
            resultFile.append(convertTimerToString(timer));
        }
        resultFile.append("END:VCALENDAR\n");

        return resultFile.toString();
    }

    private String convertTimerToString(Timer timer) {
        var result = new StringBuilder("BEGIN:VEVENT\r\n");

        var zoneOffsetInHours = Duration.between(
                LocalDateTime.now(ZoneId.of("UTC")),
                LocalDateTime.now(ZoneId.systemDefault())
        ).toHours();

        var start = timer.getDate().atTime(timer.getStart_time());
        result.append("DTEND:").append(
                start.minusHours(zoneOffsetInHours)
                        .plusMinutes(timer.getDurationInMinutes())
                        .format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'"))
        ).append("\r\n");
        result.append("DTSTART:").append(
                start.minusHours(zoneOffsetInHours)
                        .format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'"))
        ).append("\r\n");
        result.append("SUMMARY:").append(timer.getName()).append("\r\n");
        result.append("UID:").append(UUID.randomUUID()).append("\r\n");
        result.append("DESCRIPTION:").append(timer.getDescription()).append("\r\n");
        result.append("END:VEVENT\r\n");

        return result.toString();
    }
}
