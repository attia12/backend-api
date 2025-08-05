package com.example.spring_security_asymetric_encrytion.user;

import com.example.spring_security_asymetric_encrytion.auth.request.RegistrationRequest;
import com.example.spring_security_asymetric_encrytion.user.request.ProfileUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;
    public void mergeUserInfo(final User savedUser, final ProfileUpdateRequest request) {
        if(StringUtils.isNotBlank(request.getFirstName()) && !savedUser.getFirstName().equals(request.getFirstName())) {
            savedUser.setFirstName(request.getFirstName());

        }
        if(StringUtils.isNotBlank(request.getLastName()) && !savedUser.getLastName().equals(request.getLastName())) {
            savedUser.setLastName(request.getLastName());

        }
        if(request.getDateOfBirth() != null && !request.getDateOfBirth().equals(savedUser.getDateOfBirth())) {
           savedUser.setDateOfBirth(request.getDateOfBirth());

        }
    }

    public User toUser(RegistrationRequest request) {
        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(this.passwordEncoder.encode(request.getPassword()))
                .locked(false)
                .enabled(true)
                .credentialsExpired(false)
                .emailVerified(false)
                .phoneVerified(false)
                .build();
    }
}
