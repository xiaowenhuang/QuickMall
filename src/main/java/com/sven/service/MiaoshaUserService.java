package com.sven.service;

import com.sven.dao.MiaoshaUserDao;
import com.sven.exception.GlobalException;
import com.sven.model.MiaoshaUser;
import com.sven.result.CodeMsg;
import com.sven.utils.MD5Util;
import com.sven.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sven on 2018/1/21.
 */
@Service
public class MiaoshaUserService {
    @Autowired
    MiaoshaUserDao miaoshaUserDao;
    public MiaoshaUser getById(long id){
        return miaoshaUserDao.getById(id);
    }

    public boolean login(LoginVo loginVo) {
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
        return true;
    }
}
