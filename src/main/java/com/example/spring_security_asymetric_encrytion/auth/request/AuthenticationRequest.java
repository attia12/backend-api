package com.example.spring_security_asymetric_encrytion.auth.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationRequest {
    @NotBlank(message = "VALIDATION.AUTHENTICATION.EMAIL.NOT_BLANK")
    @Email(message = "VALIDATION.AUTHENTICATION.EMAIL.FORMAT")
    @Schema(example = "attia@mail.com")
    String email;
    @NotBlank(message = "VALIDATION.AUTHENTICATION.PASSWORD.NOT_BLANK")
    @Schema(example = "pAssword1!_")
    String password;
}

