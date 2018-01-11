package com.sven.redis;

/**
 * Created by sven on 2018/1/12.
 */
public interface KeyPrefix {
    int expireSeconds();
    String getPrefix();
}
