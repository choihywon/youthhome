package io.youth.home.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserRequest {
    private String email;
    private String password;
    private String nickname;
    private String address;
}
