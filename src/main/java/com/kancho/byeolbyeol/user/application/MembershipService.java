package com.kancho.byeolbyeol.user.application;

import com.kancho.byeolbyeol.common.JWTManager;
import com.kancho.byeolbyeol.constellation.domain.Constellation;
import com.kancho.byeolbyeol.constellation.domain.ConstellationRepository;
import com.kancho.byeolbyeol.user.domain.user.User;
import com.kancho.byeolbyeol.user.domain.user.UserRepository;
import com.kancho.byeolbyeol.user.dto.requset.ReqSignInDto;
import com.kancho.byeolbyeol.user.dto.requset.ReqSignUpDto;
import com.kancho.byeolbyeol.user.dto.response.ResTokenDto;
import com.kancho.byeolbyeol.user.dto.response.ResUserDto;
import com.kancho.byeolbyeol.user.dto.response.ResUserInfoDto;
import com.kancho.byeolbyeol.user.exception.NotFoundConstellationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembershipService {

    private final UserRepository userRepository;
    private final ConstellationRepository constellationRepository;
    private final JWTManager jwtManager;

    public ResUserInfoDto signUp(ReqSignUpDto reqSignUpDto) {

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
                .orElseThrow(RuntimeException::new);

        Constellation constellation = constellationRepository.findById(user.getConstellationsId())
                .orElseThrow(NotFoundConstellationException::new);

        ResTokenDto resTokenDto = createTokens(user);
        ResUserDto resUserDto = createUserInfo(user, constellation);

        return ResUserInfoDto.builder()
                .resTokenDto(resTokenDto)
                .resUserDto(resUserDto)
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
