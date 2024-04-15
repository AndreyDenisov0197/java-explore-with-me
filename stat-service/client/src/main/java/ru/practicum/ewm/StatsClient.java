package ru.practicum.ewm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class StatsClient extends BaseClient {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Value("${server.application.name:ewm-main-service}")
    private String applicationName;

    public StatsClient(@Value("${server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public ResponseEntity<Object> saveHit(HitDto request) {
        /*final HitDto hit = HitDto.builder()
                .app(applicationName)
                .uri(request.getRequestURI())
                .ip(request.getRemoteAddr())
                .timestamp(Timestamp.from(Instant.now()).toLocalDateTime())
                .build();*/
        return post(request);
    }

    public ResponseEntity<Object> getHit(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        StringBuilder uriBuilder = new StringBuilder("/stats?start={start}&end={end}");
        Map<String, Object> parameters = Map.of(
                "start", start.format(formatter),
                "end", end.format(formatter)
        );

        if (uris != null) {
            parameters.put("uris", String.join(",", uris));
        }
        if (unique) {
            parameters.put("unique", true);
        }
        return get(uriBuilder.toString(), parameters);
    }
}