package ru.practicum.ewm.dto.event;

import lombok.*;
import ru.practicum.ewm.model.enums.EventAdminState;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEventUserRequest extends UpdateEventRequest {
    private EventAdminState stateAction;
}
