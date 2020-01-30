package com.kancho.common.user_context;

public class ThreadContext {
    public static ThreadLocal<UserInfo> userInfo = new ThreadLocal<>();
}
