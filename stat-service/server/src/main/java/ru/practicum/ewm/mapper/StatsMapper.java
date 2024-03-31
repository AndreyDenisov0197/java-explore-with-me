package ru.practicum.ewm.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.practicum.ewm.StatsDto;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class StatsMapper implements RowMapper<StatsDto> {

    @Override
    public StatsDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return StatsDto.builder()
                .app(rs.getString("app"))
                .uri(rs.getString("uri"))
                .hits(rs.getLong("hits"))
                .build();
    }
}
