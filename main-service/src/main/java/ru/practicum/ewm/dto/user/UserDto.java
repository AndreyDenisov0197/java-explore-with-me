package ru.practicum.ewm.dto.user;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
public class UserDto {
    private Long id;
    @NotBlank
    @Size(min = 1, max = 50)
    private String name;
    @Email
    private String email;
}
