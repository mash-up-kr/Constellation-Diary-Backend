package com.kancho.byeolbyeol.user.application;

import com.kancho.byeolbyeol.authentication.JWTManager;
import com.kancho.byeolbyeol.common.constant.ReqTimeZone;
import com.kancho.byeolbyeol.common.user_context.FindPasswordUserInfo;
import com.kancho.byeolbyeol.common.user_context.UserInfo;
import com.kancho.byeolbyeol.common.util.TimeCalculate;
import com.kancho.byeolbyeol.horoscope.domain.constellation.Constellation;
import com.kancho.byeolbyeol.horoscope.domain.constellation.ConstellationRepository;
import com.kancho.byeolbyeol.user.dto.requset.*;
import com.kancho.byeolbyeol.user.domain.user.User;
import com.kancho.byeolbyeol.user.domain.user.UserRepository;
import com.kancho.byeolbyeol.user.dto.response.ResTokenDto;
import com.kancho.byeolbyeol.user.dto.response.ResUserDto;
import com.kancho.byeolbyeol.user.dto.response.ResUserInfoDto;
import com.kancho.byeolbyeol.user.exception.ExistsUserIdException;
import com.kancho.byeolbyeol.common.exception.NotFoundConstellationException;
import com.kancho.byeolbyeol.common.exception.NotFoundUserException;
import com.kancho.byeolbyeol.user.exception.IsNotEqualToPasswordException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class MembershipService {

    private final UserRepository userRepository;
    private final ConstellationRepository constellationRepository;
    private final JWTManager jwtManager;

    public ResUserInfoDto signUp(ReqTimeZone reqTimeZone, ReqSignUpDto reqSignUpDto) {
        boolean result = userRepository.existsByUserId(reqSignUpDto.getUserId());

        if (result) {
            throw new ExistsUserIdException();
        }

        Constellation constellation = constellationRepository.findByName(reqSignUpDto.getConstellation())
                .orElseThrow(NotFoundConstellationException::new);


        User user = User.builder()
                .constellationsId(constellation.getId())
                .email(reqSignUpDto.getEmail())
                .userId(reqSignUpDto.getUserId())
                .password(reqSignUpDto.getPassword())
                .horoscopeTime(TimeCalculate.createHoroscopeTime(reqTimeZone))
                .questionTime(TimeCalculate.createQuestionTime(reqTimeZone))
                .build();

        user = userRepository.save(user);

        ResTokenDto resTokenDto = createTokens(user);
        ResUserDto resUserDto = createUserInfo(user, constellation, reqTimeZone);

        return ResUserInfoDto.builder()
                .resTokenDto(resTokenDto)
                .resUserDto(resUserDto)
                .build();
    }

    @Transactional
    public ResUserInfoDto signIn(ReqTimeZone reqTimeZone, ReqSignInDto reqSignInDto) {
        User user = userRepository.findByUserId(reqSignInDto.getUserId())
                .orElseThrow(NotFoundUserException::new);

        if (user.isNotEqualToPassword(reqSignInDto.getPassword())) {
            throw new IsNotEqualToPasswordException();
        }

        Constellation constellation = constellationRepository.findById(user.getConstellationsId())
                .orElseThrow(NotFoundConstellationException::new);

        user.saveFcmToken(reqSignInDto.getFcmToken());

        ResTokenDto resTokenDto = createTokens(user);
        ResUserDto resUserDto = createUserInfo(user, constellation, reqTimeZone);

        return ResUserInfoDto.builder()
                .resTokenDto(resTokenDto)
                .resUserDto(resUserDto)
                .build();
    }

    @Transactional
    public ResUserDto modifyConstellations(UserInfo userInfo, ReqTimeZone reqTimeZone,
                                           ReqModifyConstellationDto reqModifyConstellationDto) {
        User user = userRepository.findById(userInfo.getId())
                .orElseThrow(NotFoundUserException::new);

        Constellation constellation = constellationRepository.findByName(reqModifyConstellationDto.getConstellation())
                .orElseThrow(NotFoundConstellationException::new);

        user.modifyConstellation(constellation.getId());

        return createUserInfo(user, constellation, reqTimeZone);
    }

    @Transactional
    public ResUserDto modifyQuestionAlarm(UserInfo userInfo, ReqTimeZone reqTimeZone,
                                          ReqModifyQuestionAlarmDto reqModifyQuestionAlarmDto) {
        User user = userRepository.findById(userInfo.getId())
                .orElseThrow(NotFoundUserException::new);

        Constellation constellation = constellationRepository.findById(user.getConstellationsId())
                .orElseThrow(NotFoundConstellationException::new);

        user.modifyQuestionAlarm(reqModifyQuestionAlarmDto.getModifyQuestionAlarm());

        return createUserInfo(user, constellation, reqTimeZone);
    }

    @Transactional
    public ResUserDto modifyHoroscopeAlarm(UserInfo userInfo, ReqTimeZone reqTimeZone,
                                           ReqModifyHoroscopeAlarmDto reqModifyHoroscopeAlarmDto) {
        User user = userRepository.findById(userInfo.getId())
                .orElseThrow(NotFoundUserException::new);

        Constellation constellation = constellationRepository.findById(user.getConstellationsId())
                .orElseThrow(NotFoundConstellationException::new);

        user.modifyHoroscopeAlarm(reqModifyHoroscopeAlarmDto.getHoroscopeAlarm());

        return createUserInfo(user, constellation, reqTimeZone);
    }

    @Transactional
    public ResUserDto modifyQuestionTime(UserInfo userInfo, ReqTimeZone reqTimeZone,
                                         ReqModifyQuestionTimeDto reqModifyQuestionTimeDto) {
        User user = userRepository.findById(userInfo.getId())
                .orElseThrow(NotFoundUserException::new);

        Constellation constellation = constellationRepository.findById(user.getConstellationsId())
                .orElseThrow(NotFoundConstellationException::new);

        LocalTime questionTime = TimeCalculate.convertLocalTime(reqModifyQuestionTimeDto.getDate());

        user.modifyQuestionTime(questionTime);

        return createUserInfo(user, constellation, reqTimeZone);
    }


    @Transactional
    public ResUserDto modifyHoroscopeTime(UserInfo userInfo, ReqTimeZone reqTimeZone,
                                          ReqModifyHoroscopeTimeDto reqModifyHoroscopeTimeDto) {
        User user = userRepository.findById(userInfo.getId())
                .orElseThrow(NotFoundUserException::new);

        Constellation constellation = constellationRepository.findById(user.getConstellationsId())
                .orElseThrow(NotFoundConstellationException::new);

        LocalTime horoscopeTime = TimeCalculate.convertLocalTime(reqModifyHoroscopeTimeDto.getDate());

        user.modifyHoroscopeTime(horoscopeTime);

        return createUserInfo(user, constellation, reqTimeZone);
    }

    @Transactional
    public void modifyPassword(String token, ReqModifyPasswordDto reqModifyPasswordDto) {
        FindPasswordUserInfo findPasswordUserInfo = jwtManager.getFindPasswordUserInfo(token);

        User user =
                userRepository.findByUserIdAndEmail(findPasswordUserInfo.getUserId(), findPasswordUserInfo.getEmail())
                        .orElseThrow(NotFoundUserException::new);

        user.modifyPassword(reqModifyPasswordDto.getPassword());
    }


    private ResTokenDto createTokens(User user) {
        String authenticationToken = jwtManager.createAuthenticationToken(user.getUserId(), user.getId());
        String refreshToken = jwtManager.createRefreshToken(user.getUserId(), user.getId());

        return ResTokenDto.builder()
                .authenticationToken(authenticationToken)
                .refreshToken(refreshToken)
                .build();
    }

    private ResUserDto createUserInfo(User user, Constellation constellation, ReqTimeZone reqTimeZone) {
        return ResUserDto.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .timeZone(reqTimeZone.getValue())
                .constellation(constellation.getName())
                .horoscopeAlarmFlag(user.getHoroscopeAlarmFlag())
                .questionAlarmFlag(user.getQuestionAlarmFlag())
                .horoscopeTime(user.getHoroscopeTime())
                .questionTime(user.getQuestionTime())
                .build();
    }

}
