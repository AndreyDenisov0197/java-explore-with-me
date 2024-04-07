package ru.practicum.ewm.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.practicum.ewm.HitDto;
import ru.practicum.ewm.StatsDto;
import ru.practicum.ewm.StatsRequestDto;
import ru.practicum.ewm.mapper.StatsMapper;

import java.sql.Timestamp;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StatsRepositoryImpl implements StatsRepository {
    private final JdbcTemplate jdbcTemplate;
    private final StatsMapper mapper;

    @Override
    public void saveHit(HitDto hit) {
        jdbcTemplate.update("INSERT INTO stats (app, uri, ip, timestamp) VALUES (?, ?, ?, ?)",
                hit.getApp(), hit.getUri(), hit.getIp(), Timestamp.valueOf(hit.getTimestamp()));
    }

    @Override
    public List<StatsDto> getStats(StatsRequestDto request) {
        String query = "SELECT app, uri, COUNT (ip) AS hits FROM stats WHERE (timestamp >= ? AND timestamp <= ?) ";
        if (!request.getUris().isEmpty()) {
            query += createUrisQuery(request.getUris());
        }
        query += " GROUP BY app, uri ORDER BY hits DESC";
        return jdbcTemplate.query(query, mapper, request.getStart(), request.getEnd());
    }

    @Override
    public List<StatsDto> getUniqueStats(StatsRequestDto request) {
        String query = "SELECT app, uri, COUNT (DISTINCT ip) AS hits FROM stats WHERE (timestamp >= ? AND timestamp <= ?) ";
        if (!request.getUris().isEmpty()) {
            query += createUrisQuery(request.getUris());
        }
        query += " GROUP BY app, uri ORDER BY hits DESC";
        return jdbcTemplate.query(query, mapper, request.getStart(), request.getEnd());
    }


    private String createUrisQuery(List<String> uris) {
        StringBuilder result = new StringBuilder("AND uri IN ('");
        result.append(String.join("', '", uris));
        return result.append("') ").toString();
    }
}
