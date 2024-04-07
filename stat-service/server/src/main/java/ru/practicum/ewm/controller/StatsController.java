package ru.practicum.ewm.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.HitDto;
import ru.practicum.ewm.StatsDto;
import ru.practicum.ewm.StatsRequestDto;
import ru.practicum.ewm.service.StatsService;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class StatsController {
    private final StatsService service;

    @PostMapping("/hit")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void hit(@RequestBody HitDto hit) {
        log.info("Post запрос на сохранение информации");
        service.saveHit(hit);
    }

    @GetMapping("/stats")
    public List<StatsDto> getStats(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")LocalDateTime start,
                                   @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")LocalDateTime end,
                                   @RequestParam(defaultValue = "") List<String> uris,
                                   @RequestParam(defaultValue = "false") boolean unique) {
        log.info("Get запрос на получение статистики");
        if (end.isBefore(start)) {
            log.warn("Неверно указаны даты: start {} позже end {}", start, end);
            throw new InvalidParameterException("Неверно указаны даты: start " + start + " позже end " + end);
        }

        return service.getStatsList(
                StatsRequestDto.builder()
                        .start(start)
                        .end(end)
                        .uris(uris)
                        .unique(unique)
                        .build());
    }
}
