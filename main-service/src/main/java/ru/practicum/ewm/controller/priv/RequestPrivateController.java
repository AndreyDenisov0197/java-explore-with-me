package ru.practicum.ewm.controller.priv;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.request.ParticipationRequestDto;
import ru.practicum.ewm.service.request.RequestService;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping(value = "/users/{userId}/requests")
@Slf4j
@RequiredArgsConstructor
@Validated
public class RequestPrivateController {
    private final RequestService requestService;

    @GetMapping
    public List<ParticipationRequestDto> getAllRequests(@PathVariable(value = "userId") @Min(1) Long userId) {
        log.info("GET запрос на получение всех запросов на участие в в событиях пользователя с id={}", userId);
        return requestService.getRequestsByUserId(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParticipationRequestDto addRequest(@PathVariable(value = "userId") @Min(1) Long userId,
                                              @RequestParam(value = "eventId") @Min(1) Long eventId) {
        log.info("POST запрос на создание запроса участия в событии id={} пользователя id={}", eventId, userId);
        return requestService.addNewRequest(userId, eventId);
    }

    @PatchMapping("/{requestId}/cancel")
    public ParticipationRequestDto cancelRequest(@PathVariable(value = "userId") @Min(1) Long userId,
                                                 @PathVariable(value = "requestId") @Min(1) Long requestId) {
        log.info("PATCH запрос на отмену запроса id={} пользователя id={}", requestId, userId);
        return requestService.cancelRequest(userId, requestId);
    }
}
