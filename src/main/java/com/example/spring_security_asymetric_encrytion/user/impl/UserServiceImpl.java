package com.example.spring_security_asymetric_encrytion.user.impl;

import com.example.spring_security_asymetric_encrytion.exception.BusinessException;
import com.example.spring_security_asymetric_encrytion.exception.ErrorCode;
import com.example.spring_security_asymetric_encrytion.user.User;
import com.example.spring_security_asymetric_encrytion.user.UserMapper;
import com.example.spring_security_asymetric_encrytion.user.UserRepository;
import com.example.spring_security_asymetric_encrytion.user.UserService;
import com.example.spring_security_asymetric_encrytion.user.request.ChangePasswordRequest;
import com.example.spring_security_asymetric_encrytion.user.request.ProfileUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.spring_security_asymetric_encrytion.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Slf4j

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(final String userEmail) throws UsernameNotFoundException {
        return this.userRepository.findByEmailIgnoreCase(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with user name: " + userEmail));
    }

    @Override
    public void updateProfileInfo(ProfileUpdateRequest request, String userId) {
        final User savedUser = this.userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USERNAME_NOT_FOUND,userId));
   this.userMapper.mergeUserInfo(savedUser, request);
   this.userRepository.save(savedUser);

    }

    @Override
    public void changePassword(ChangePasswordRequest request, String userId) {
        if (!request.getNewPassword()
                .equals(request.getConfirmNewPassword())) {
            throw new BusinessException(CHANGE_PASSWORD_MISMATCH);
        }

        final User savedUser = this.userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(USER_NOT_FOUND));

        if (!this.passwordEncoder.matches(request.getCurrentPassword(),
                savedUser.getPassword())) {
            throw new BusinessException(INVALID_CURRENT_PASSWORD);
        }

        final String encoded = this.passwordEncoder.encode(request.getNewPassword());
        savedUser.setPassword(encoded);
        this.userRepository.save(savedUser);

    }

    @Override
    public void deactivateAccount(String userId) {
        final User user= this.userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(USER_NOT_FOUND));
        if(!user.isEnabled()){
           throw new BusinessException(ACCOUNT_ALREADY_DEACTIVATED);
        }
        user.setEnabled(false);
        this.userRepository.save(user);

    }

    @Override
    public void reactivateAccount(String userId) {
        final User user= this.userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(USER_NOT_FOUND));
        if(user.isEnabled()){
            throw new BusinessException(ACCOUNT_ALREADY_DEACTIVATED);
        }
        user.setEnabled(true);
        this.userRepository.save(user);

    }

    @Override
    public void deleteAccount(String userId) {

    }


}
