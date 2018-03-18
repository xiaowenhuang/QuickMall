package com.sven.service;

import com.sven.dao.MiaoshaUserDao;
import com.sven.exception.GlobalException;
import com.sven.model.MiaoshaUser;
import com.sven.redis.MiaoshaUserKey;
import com.sven.redis.RedisService;
import com.sven.result.CodeMsg;
import com.sven.utils.MD5Util;
import com.sven.utils.UUIDUtil;
import com.sven.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by sven on 2018/1/21.
 */
@Service
public class MiaoshaUserService {
    public static final String COOKIE_NAME_TOKEN = "token";
    @Autowired
    MiaoshaUserDao miaoshaUserDao;
    @Autowired
    RedisService redisService;

    public MiaoshaUser getById(long id){
        return miaoshaUserDao.getById(id);
    }

    public boolean login(HttpServletResponse response,LoginVo loginVo) {
        if (loginVo == null){
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        //判断手机号是否存在
        MiaoshaUser miaoshaUser = getById(Long.parseLong(mobile));
        if (miaoshaUser == null){
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //验证密码
        String dbPass = miaoshaUser.getPassword();
        String dbSalt = miaoshaUser.getSalt();
        String calPass = MD5Util.formPassToDBPass(formPass,dbSalt);
        if (!calPass.equals(dbPass)){
             throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        //生成cookie
        String token = UUIDUtil.uuid();
        redisService.set(MiaoshaUserKey.token,token,miaoshaUser);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN,token);
        cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
        return true;
    }

    public MiaoshaUser getByToken(String token) {
        if (StringUtils.isEmpty(token)){
            return null;
        }
        return redisService.get(MiaoshaUserKey.token,token,MiaoshaUser.class);
    }
}
