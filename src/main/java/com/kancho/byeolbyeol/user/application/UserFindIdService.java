package com.kancho.byeolbyeol.user.application;

import com.kancho.byeolbyeol.user.domain.authenticationnumber.AuthenticationNumber;
import com.kancho.byeolbyeol.user.domain.authenticationnumber.AuthenticationNumberRepository;
import com.kancho.byeolbyeol.user.domain.authenticationnumber.AuthenticationPurpose;
import com.kancho.byeolbyeol.user.domain.user.User;
import com.kancho.byeolbyeol.user.domain.user.UserRepository;
import com.kancho.byeolbyeol.user.dto.requset.ReqValidationNumberDto;
import com.kancho.byeolbyeol.user.dto.response.ResUserIdDto;
import com.kancho.byeolbyeol.user.exception.NotFoundAuthenticationNumberException;
import com.kancho.byeolbyeol.user.exception.NotFoundUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFindIdService {
    private final UserRepository userRepository;
    private final AuthenticationNumberRepository authenticationNumberRepository;

    public ResUserIdDto findUserId(ReqValidationNumberDto reqValidationNumberDto) {

        AuthenticationNumber authenticationNumber = authenticationNumberRepository
                .findFirstByEmailAndAuthenticationPurposeAndExpirationTimeGreaterThanEqualOrderByExpirationTime(
                        reqValidationNumberDto.getEmail(),
                        AuthenticationPurpose.FIND_ID,
                        System.currentTimeMillis())
                .orElseThrow(NotFoundAuthenticationNumberException::new);

        User user = userRepository.findByEmail(authenticationNumber.getEmail())
                .orElseThrow(NotFoundUserException::new);

        return ResUserIdDto.builder()
                .userId(user.getUserId())
                .build();
    }
}
