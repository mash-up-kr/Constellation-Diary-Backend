package com.kancho.byeolbyeol.user.application;

import com.kancho.byeolbyeol.user.domain.user.UserRepository;
import com.kancho.byeolbyeol.user.dto.ResCheckUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public ResCheckUserDto duplicateCheck(String checkUserId) {
        boolean result = userRepository.existsByUserId(checkUserId);

        return ResCheckUserDto.builder()
                .userId(checkUserId)
                .available(!result)
                .build();
    }
}
