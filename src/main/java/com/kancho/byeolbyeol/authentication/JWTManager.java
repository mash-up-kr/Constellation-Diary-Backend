package com.kancho.byeolbyeol.authentication;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTManager {

    private final static String REFRESH_KEYWORD = "Bearer Token";
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
        return createJWT(email, REGISTER_TOKEN, ONE_DAY);
    }

    public String createAuthenticationToken(String userId, Long id) {
        return createJWT(userId, id, AUTHENTICATION_TOKEN, ONE_DAY);
    }

    public String createRefreshToken(String userId, Long id) {
        return createJWT(userId, id, REFRESH_TOKEN, THIRTY_DAYS);
    }

    public String createJWT(String email, String tokenType, Long day) {

        JwtBuilder jwtHeader = createJWTHeader(tokenType, day);

        return jwtHeader.claim(EMAIL, email)
                .signWith(generateKey(key))
                .compact();
    }

    public String createJWT(String userId, Long id, String tokenType, Long day) {

        JwtBuilder jwtHeader = createJWTHeader(tokenType, day);

        return jwtHeader.claim(USER_ID, userId)
                .claim(ID, id)
                .signWith(generateKey(key))
                .compact();
    }

    private JwtBuilder createJWTHeader(String tokenType, Long day) {
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
}