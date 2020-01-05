package com.kancho.byeolbyeol.common;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTManager {
    private final static String KEYWORD = "Bearer";
    private final static String USER_ID = "userId";
    private final static String ID = "id";
    private final static String EMAIL = "email";
    private static final Long ONE_DAY = 1000L * 60L * 60L * 24L;
    private static final Long THIRTY_DAYS = ONE_DAY * 30L;
    private static final String AUTHENTICATION_TOKEN = "Authentication Token";
    private static final String REFRESH_TOKEN = "Refresh Token";
    private static final String REGISTER_TOKEN = "Register Token";

    private String key;

    public JWTManager(@Value("${Secret.Key}") String key) {
        this.key = key;
    }


    public String createRegisterToken(String email) {
        return createRegisterJWT(email, REGISTER_TOKEN, ONE_DAY);
    }

    public String createAuthenticationToken(String userId, Long id) {
        return createJWT(userId, id, AUTHENTICATION_TOKEN, ONE_DAY);
    }

    public String createRefreshToken(String userId, Long id) {
        return createJWT(userId, id, REFRESH_TOKEN, THIRTY_DAYS);
    }

    private String createRegisterJWT(String email, String tokenType, Long day) {

        JwtBuilder jwtHeader = createJWTRegisterClaim(tokenType, day);

        return jwtHeader.claim(EMAIL, email)
                .signWith(generateKey(key))
                .compact();
    }

    private String createJWT(String userId, Long id, String tokenType, Long day) {

        JwtBuilder jwtHeader = createJWTRegisterClaim(tokenType, day);

        return jwtHeader.claim(USER_ID, userId)
                .claim(ID, id)
                .signWith(generateKey(key))
                .compact();
    }

    private JwtBuilder createJWTRegisterClaim(String tokenType, Long day) {
        Date today = new Date();
        Date tomorrow = new Date(today.getTime() + day);

        return Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setIssuer("API Server")
                .setSubject(tokenType)
                .setExpiration(tomorrow)
                .setIssuedAt(today);
    }

    private SecretKey generateKey(String key) {
        byte[] byteKey = key.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(byteKey);
    }

    public void authenticate(String token) {
        if (isNotStartBearer(token)) {
            throw new FailAuthenticationException();
        }

        String tempToken = subStringKeywordString(token);

        getPayLoad(tempToken);
    }

    private boolean isNotStartBearer(String token) {
        return !token.startsWith(KEYWORD);
    }

    private String subStringKeywordString(String token) {
        return token.substring(KEYWORD.length());
    }

    private Jws<Claims> getPayLoad(String token) {
        Jws<Claims> claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(generateKey(key))
                    .parseClaimsJws(token);
        } catch (JwtException e) {
            throw new FailAuthenticationException();
        }
        return claims;
    }

    public UserInfoDto getUserInfo(String token) {
        String tempToken = subStringKeywordString(token);

        Jws<Claims> claims = getPayLoad(tempToken);
        String subject = getSubjectRefresh(claims);

        if (!subject.equals(REFRESH_TOKEN)) {
            throw new FailAuthenticationException();
        }
        return UserInfoDto.builder()
                .userId(getClaimsUserId(claims))
                .id(getClaimsId(claims))
                .build();
    }

    private String getSubjectRefresh(Jws<Claims> claims) {
        return claims.getBody().getSubject();
    }

    private String getClaimsUserId(Jws<Claims> claims) {
        return claims.getBody().get(USER_ID).toString();
    }

    private Long getClaimsId(Jws<Claims> claims) {
        return Long.parseLong(claims.getBody().get(ID).toString());
    }

}
