package com.kancho.user.application;

import com.kancho.common.constant.ReqTimeZone;
import com.kancho.common.user_context.UserInfo;
import com.kancho.common.util.ReplacePassword;
import com.kancho.user.domain.user.User;
import com.kancho.user.dto.requset.*;
import com.kancho.user.domain.user.UserRepository;
import com.kancho.user.dto.response.*;
import com.kancho.user.exception.FailAuthenticationNumberException;
import com.kancho.user.exception.NonexistentUserByEmailException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

    public ResUserIdDto findUserId(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(NonexistentUserByEmailException::new);

        return ResUserIdDto.builder()
                .userId(ReplacePassword.changeAsterisk(user.getUserId()))
                .build();

    }

    @Transactional
    public void signOut(UserInfo userInfo) {
        User user = userRepository.findById(userInfo.getId())
                .orElseThrow(FailAuthenticationNumberException::new);

        user.removeFcmToken();
    }

    public void modifyPassword(String token, ReqModifyPasswordDto reqModifyPasswordDto) {
        membershipService.modifyPassword(token, reqModifyPasswordDto);
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
                                         ReqModifyQuestionTimeDto reqModifyQuestionTimeDto) {
        return membershipService.modifyQuestionTime(userInfo, reqTimeZone, reqModifyQuestionTimeDto);
    }

    public ResUserDto modifyHoroscopeTime(UserInfo userInfo, ReqTimeZone reqTimeZone,
                                          ReqModifyHoroscopeTimeDto reqModifyHoroscopeTimeDto) {
        return membershipService.modifyHoroscopeTime(userInfo, reqTimeZone, reqModifyHoroscopeTimeDto);
    }

}
