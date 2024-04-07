package ru.practicum.ewm.service;

import ru.practicum.ewm.HitDto;
import ru.practicum.ewm.StatsDto;
import ru.practicum.ewm.StatsRequestDto;

import java.util.List;

public interface StatsService {

    void saveHit(HitDto hit);

    List<StatsDto> getStatsList(StatsRequestDto build);
}
