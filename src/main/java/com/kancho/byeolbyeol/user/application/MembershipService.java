package com.kancho.byeolbyeol.user.application;

import com.kancho.byeolbyeol.authentication.JWTManager;
import com.kancho.byeolbyeol.common.user_context.UserInfo;
import com.kancho.byeolbyeol.horoscope.domain.constellation.Constellation;
import com.kancho.byeolbyeol.horoscope.domain.constellation.ConstellationRepository;
import com.kancho.byeolbyeol.user.dto.requset.ReqModifyConstellationDto;
import com.kancho.byeolbyeol.user.domain.user.User;
import com.kancho.byeolbyeol.user.domain.user.UserRepository;
import com.kancho.byeolbyeol.user.dto.requset.ReqModifyQuestionAlarmDto;
import com.kancho.byeolbyeol.user.dto.requset.ReqSignInDto;
import com.kancho.byeolbyeol.user.dto.requset.ReqSignUpDto;
import com.kancho.byeolbyeol.user.dto.response.ResTokenDto;
import com.kancho.byeolbyeol.user.dto.response.ResUserDto;
import com.kancho.byeolbyeol.user.dto.response.ResUserInfoDto;
import com.kancho.byeolbyeol.user.exception.ExistsUserIdException;
import com.kancho.byeolbyeol.common.exception.NotFoundConstellationException;
import com.kancho.byeolbyeol.common.exception.NotFoundUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MembershipService {

    private final UserRepository userRepository;
    private final ConstellationRepository constellationRepository;
    private final JWTManager jwtManager;

    public ResUserInfoDto signUp(ReqSignUpDto reqSignUpDto) {
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
                .build();

        user = userRepository.save(user);

        ResTokenDto resTokenDto = createTokens(user);
        ResUserDto resUserDto = createUserInfo(user, constellation);

        return ResUserInfoDto.builder()
                .resTokenDto(resTokenDto)
                .resUserDto(resUserDto)
                .build();
    }

    public ResUserInfoDto signIn(ReqSignInDto reqSignInDto) {
        User user = userRepository.findByUserId(reqSignInDto.getUserId())
                .orElseThrow(NotFoundUserException::new);

        Constellation constellation = constellationRepository.findById(user.getConstellationsId())
                .orElseThrow(NotFoundConstellationException::new);

        ResTokenDto resTokenDto = createTokens(user);
        ResUserDto resUserDto = createUserInfo(user, constellation);

        return ResUserInfoDto.builder()
                .resTokenDto(resTokenDto)
                .resUserDto(resUserDto)
                .build();
    }

    @Transactional
    public ResUserDto modifyConstellations(UserInfo userInfo, ReqModifyConstellationDto reqModifyConstellationDto) {
        User user = userRepository.findById(userInfo.getId())
                .orElseThrow(NotFoundUserException::new);

        Constellation constellation = constellationRepository.findByName(reqModifyConstellationDto.getConstellation())
                .orElseThrow(NotFoundConstellationException::new);

        user.modifyConstellation(constellation.getId());

        return ResUserDto.builder()
                .id(user.getId())
                .constellation(constellation.getName())
                .userId(user.getUserId())
                .horoscopeAlarmFlag(user.getHoroscopeAlarmFlag())
                .questionAlarmFlag(user.getQuestionAlarmFlag())
                .questionTime(user.getQuestionTime())
                .build();
    }

    @Transactional
    public ResUserDto modifyQuestionAlarm(UserInfo userInfo, ReqModifyQuestionAlarmDto reqModifyQuestionAlarmDto) {
        User user = userRepository.findById(userInfo.getId())
                .orElseThrow(NotFoundUserException::new);

        Constellation constellation = constellationRepository.findById(user.getConstellationsId())
                .orElseThrow(NotFoundConstellationException::new);

        user.modifyQuestionAlarm(reqModifyQuestionAlarmDto.getModifyQuestionAlarm());

        return ResUserDto.builder()
                .id(user.getId())
                .constellation(constellation.getName())
                .userId(user.getUserId())
                .horoscopeAlarmFlag(user.getHoroscopeAlarmFlag())
                .questionAlarmFlag(user.getQuestionAlarmFlag())
                .questionTime(user.getQuestionTime())
                .build();
    }


    private ResTokenDto createTokens(User user) {

        String authenticationToken = jwtManager.createAuthenticationToken(user.getUserId(), user.getId());
        String refreshToken = jwtManager.createRefreshToken(user.getUserId(), user.getId());

        return ResTokenDto.builder()
                .authenticationToken(authenticationToken)
                .refreshToken(refreshToken)
                .build();
    }

    private ResUserDto createUserInfo(User user, Constellation constellation) {
        return ResUserDto.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .constellation(constellation.getName())
                .horoscopeAlarmFlag(user.getHoroscopeAlarmFlag())
                .questionAlarmFlag(user.getQuestionAlarmFlag())
                .questionTime(user.getQuestionTime())
                .build();
    }


}
