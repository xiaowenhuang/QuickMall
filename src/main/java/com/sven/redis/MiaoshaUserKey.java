package com.sven.redis;

/**
 * Created by sven on 2018/3/18.
 */
public class MiaoshaUserKey extends BasePrefix{
    public static final int TOKEN_EXPIRE = 3600*24 * 2;
    public MiaoshaUserKey(String prefix) {
        super(prefix);
    }

    public static MiaoshaUserKey token = new MiaoshaUserKey(TOKEN_EXPIRE,"tk");

    public MiaoshaUserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
}
