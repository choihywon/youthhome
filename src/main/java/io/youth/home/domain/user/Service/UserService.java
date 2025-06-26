package io.youth.home.domain.user.Service;


import io.youth.home.domain.user.dto.UserRequest;
import io.youth.home.domain.user.entity.Role;
import io.youth.home.domain.user.entity.User;
import io.youth.home.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void register(UserRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .nickname(request.getNickname())
                .address(request.getAddress())
                .role(Role.USER)
                .active(true)
                .build();

        userRepository.save(user);
    }
}
