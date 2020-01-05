package com.kancho.byeolbyeol.user.application;

import com.kancho.byeolbyeol.authentication.JWTManager;
import com.kancho.byeolbyeol.constellation.domain.Constellation;
import com.kancho.byeolbyeol.constellation.domain.ConstellationRepository;
import com.kancho.byeolbyeol.user.domain.user.User;
import com.kancho.byeolbyeol.user.domain.user.UserRepository;
import com.kancho.byeolbyeol.user.dto.ReqSignUpDto;
import com.kancho.byeolbyeol.user.dto.ResTokenDto;
import com.kancho.byeolbyeol.user.dto.ResUserDto;
import com.kancho.byeolbyeol.user.exception.NotFoundConstellationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSignUpService {

    private final UserRepository userRepository;
    private final ConstellationRepository constellationRepository;
    private final JWTManager jwtManager;

    public ResUserDto signUp(ReqSignUpDto reqSignUpDto) {

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

        return ResUserDto.builder()
                .resTokenDto(resTokenDto)
                .constellation(constellation.getName())
                .id(user.getId())
                .userId(user.getUserId())
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
}
