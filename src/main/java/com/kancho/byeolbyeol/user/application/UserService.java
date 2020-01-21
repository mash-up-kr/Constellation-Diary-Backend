package com.kancho.byeolbyeol.user.application;

import com.kancho.byeolbyeol.common.user_context.UserInfo;
import com.kancho.byeolbyeol.user.dto.requset.*;
import com.kancho.byeolbyeol.user.domain.user.UserRepository;
import com.kancho.byeolbyeol.user.dto.response.ResCheckUserDto;
import com.kancho.byeolbyeol.user.dto.response.ResTokenDto;
import com.kancho.byeolbyeol.user.dto.response.ResUserDto;
import com.kancho.byeolbyeol.user.dto.response.ResUserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final MembershipService membershipService;
    private final TokenService tokenService;

    public ResCheckUserDto duplicateCheck(String checkUserId) {
        boolean result = userRepository.existsByUserId(checkUserId);

        return ResCheckUserDto.builder()
                .userId(checkUserId)
                .available(!result)
                .build();
    }

    public ResUserInfoDto signUp(ReqSignUpDto reqSignUpDto) {
        return membershipService.signUp(reqSignUpDto);
    }

    public ResUserInfoDto signIn(ReqSignInDto reqSignInDto) {
        return membershipService.signIn(reqSignInDto);
    }

    public ResTokenDto refreshToken(UserInfo userInfo) {
        return tokenService.refreshToken(userInfo);
    }

    public ResUserDto modifyConstellations(UserInfo userInfo, ReqModifyConstellationDto reqModifyConstellationDto) {
        return membershipService.modifyConstellations(userInfo, reqModifyConstellationDto);
    }

    public ResUserDto modifyQuestionAlarm(UserInfo userInfo, ReqModifyQuestionAlarmDto reqModifyQuestionAlarmDto) {
        return membershipService.modifyQuestionAlarm(userInfo, reqModifyQuestionAlarmDto);
    }

    public ResUserDto modifyHoroscopeAlarm(UserInfo userInfo, ReqModifyHoroscopeAlarmDto reqModifyHoroscopeAlarmDto) {
        return membershipService.modifyHoroscopeAlarm(userInfo, reqModifyHoroscopeAlarmDto);
    }

    public ResUserDto modifyQuestionTime(UserInfo userInfo, ReqModifyQuestionTimeDto reqModifyHoroscopeAlarmDto) {
        return membershipService.modifyQuestionTime(userInfo, reqModifyHoroscopeAlarmDto);
    }
}
