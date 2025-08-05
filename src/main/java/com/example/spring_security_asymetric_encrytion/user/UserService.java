package com.example.spring_security_asymetric_encrytion.user;

import com.example.spring_security_asymetric_encrytion.user.request.ChangePasswordRequest;
import com.example.spring_security_asymetric_encrytion.user.request.ProfileUpdateRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void updateProfileInfo(ProfileUpdateRequest request, String userId);

    void changePassword(ChangePasswordRequest request, String userId);
    void deactivateAccount(String userId);

    void reactivateAccount(String userId);

    void deleteAccount(String userId);
}
