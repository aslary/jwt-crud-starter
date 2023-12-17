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
public class LoginDto {

    @NotBlank(message = "{notBlank}")
    @Size(min = 3, max = 30)
    private String username;

    @NotBlank(message = "{notBlank}")
    @Size(min = 12, message = "{string.min}")
    private String password;

}
