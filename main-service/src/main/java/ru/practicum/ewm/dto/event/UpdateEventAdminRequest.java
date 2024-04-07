package ru.practicum.ewm.dto.event;

import lombok.Data;
import ru.practicum.ewm.model.enums.EventAdminState;

@Data
public class UpdateEventAdminRequest extends UpdateEventRequest {

    private EventAdminState stateAction;
}
