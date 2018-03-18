package com.sven.utils;

import java.util.UUID;

/**
 * Created by sven on 2018/3/18.
 */
public class UUIDUtil {
    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
