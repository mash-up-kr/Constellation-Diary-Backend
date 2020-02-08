package com.kancho.fcm_test;

import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.kancho.authentication.JWTManager;
import com.kancho.authentication.TokenType;
import com.kancho.common.exception.NotFoundUserException;
import com.kancho.common.user_context.UserInfo;
import com.kancho.user.User;
import com.kancho.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FcmTestService {

    private final FirebaseApp firebaseApp;
    private final UserRepository userRepository;
    private final JWTManager jwtManager;

    public void send(String token) {
        UserInfo userInfo = jwtManager.getUserInfo(token, TokenType.AUTHENTICATION_TOKEN::verifyValue);
        User user = userRepository.findById(userInfo.getId()).orElseThrow(NotFoundUserException::new);

        Message message = Message.builder()
                .setNotification(new Notification("test title", "test body"))
                .putData("title", "test title")
                .putData("body", "test body")
                .setToken(user.getFcmToken())
                .build();
        try {
            FirebaseMessaging.getInstance(firebaseApp).send(message);
        } catch (FirebaseMessagingException e) {
            log.error("Fcm Send Error - userId" + userInfo.getUserId());
            log.error("Fcm Send Error - token" + token);
            throw new CanNotSendMessageException();

        }
    }
}
