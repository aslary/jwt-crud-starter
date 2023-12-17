package io.github.aslary.jwtcrudstarter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {

    @NotBlank(message = "{notBlank}")
    private String firstName;

    @NotBlank(message = "{notBlank}")
    private String lastName;

    @NotBlank(message = "{notBlank}")
    @Size(min = 3, max = 30, message = "{string.minMax}")
    private String username;

    @NotBlank(message = "{notBlank}")
    @Size(min = 12, message = "{string.min}")
    private String password;

}

