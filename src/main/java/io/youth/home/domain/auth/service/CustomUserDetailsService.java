package io.youth.home.domain.auth.service;

import io.youth.home.domain.auth.dto.CustomUserDetails;
import io.youth.home.domain.user.entity.User;
import io.youth.home.domain.user.repository.UserRepository;
import io.youth.home.global.ErrorCode;
import io.youth.home.global.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));

        return new CustomUserDetails(user);
    }
}