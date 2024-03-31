package ru.practicum.ewm.repository;

import ru.practicum.ewm.HitDto;
import ru.practicum.ewm.StatsDto;
import ru.practicum.ewm.StatsRequestDto;

import java.util.List;

public interface StatsRepository {
    void saveHit(HitDto hit);

    List<StatsDto> getUniqueStats(StatsRequestDto request);

    List<StatsDto> getStats(StatsRequestDto request);
}
