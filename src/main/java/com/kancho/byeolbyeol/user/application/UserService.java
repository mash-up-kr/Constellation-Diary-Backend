package com.kancho.byeolbyeol.user.application;

import com.kancho.byeolbyeol.user.domain.user.UserRepository;
import com.kancho.byeolbyeol.user.dto.ReqSignUpDto;
import com.kancho.byeolbyeol.user.dto.ResCheckUserDto;
import com.kancho.byeolbyeol.user.dto.ResUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserSignUpService userSignUpService;

    public ResCheckUserDto duplicateCheck(String checkUserId) {
        boolean result = userRepository.existsByUserId(checkUserId);

        return ResCheckUserDto.builder()
                .userId(checkUserId)
                .available(!result)
                .build();
    }

    public ResUserDto signUp(ReqSignUpDto reqSignUpDto) {
        return userSignUpService.signUp(reqSignUpDto);
    }
}
