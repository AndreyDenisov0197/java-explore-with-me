package ru.practicum.ewm;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class StatsDto {
    private String app;
    private String uri;
    private Long hits;
}
