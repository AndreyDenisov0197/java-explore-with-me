package ru.practicum.ewm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.HitDto;
import ru.practicum.ewm.StatsDto;
import ru.practicum.ewm.StatsRequestDto;
import ru.practicum.ewm.repository.StatsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {
    private final StatsRepository repository;

    @Override
    public void saveHit(HitDto hit) {
        repository.saveHit(hit);
    }

    @Override
    public List<StatsDto> getStatsList(StatsRequestDto request) {
        if (request.isUnique()) {
            return repository.getUniqueStats(request);
        }
        return repository.getStats(request);
    }
}
