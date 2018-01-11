package com.sven.redis;

/**
 * Created by sven on 2018/1/12.
 */
public class OrderKey extends BasePrefix {
    public OrderKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
}
