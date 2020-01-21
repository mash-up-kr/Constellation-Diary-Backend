package com.kancho.byeolbyeol.user.application;

import com.kancho.byeolbyeol.common.constant.ReqTimeZone;
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

    public ResUserInfoDto signUp(ReqTimeZone reqTimeZone, ReqSignUpDto reqSignUpDto) {
        return membershipService.signUp(reqTimeZone, reqSignUpDto);
    }

    public ResUserInfoDto signIn(ReqTimeZone reqTimeZone, ReqSignInDto reqSignInDto) {
        return membershipService.signIn(reqTimeZone, reqSignInDto);
    }

    public ResTokenDto refreshToken(UserInfo userInfo) {
        return tokenService.refreshToken(userInfo);
    }

    public ResUserDto modifyConstellations(UserInfo userInfo, ReqTimeZone reqTimeZone,
                                           ReqModifyConstellationDto reqModifyConstellationDto) {
        return membershipService.modifyConstellations(userInfo, reqTimeZone, reqModifyConstellationDto);
    }

    public ResUserDto modifyQuestionAlarm(UserInfo userInfo, ReqTimeZone reqTimeZone,
                                          ReqModifyQuestionAlarmDto reqModifyQuestionAlarmDto) {
        return membershipService.modifyQuestionAlarm(userInfo, reqTimeZone, reqModifyQuestionAlarmDto);
    }

    public ResUserDto modifyHoroscopeAlarm(UserInfo userInfo, ReqTimeZone reqTimeZone,
                                           ReqModifyHoroscopeAlarmDto reqModifyHoroscopeAlarmDto) {
        return membershipService.modifyHoroscopeAlarm(userInfo, reqTimeZone, reqModifyHoroscopeAlarmDto);
    }

    public ResUserDto modifyQuestionTime(UserInfo userInfo, ReqTimeZone reqTimeZone,
                                         ReqModifyQuestionTimeDto reqModifyHoroscopeAlarmDto) {
        return membershipService.modifyQuestionTime(userInfo, reqTimeZone, reqModifyHoroscopeAlarmDto);
    }
}
