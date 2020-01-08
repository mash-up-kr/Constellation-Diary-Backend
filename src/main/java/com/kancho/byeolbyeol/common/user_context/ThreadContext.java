package com.kancho.byeolbyeol.common.user_context;

public class ThreadContext {
    public static ThreadLocal<UserInfo> userInfo = new ThreadLocal<>();
}
