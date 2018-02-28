package com.sven.controller;

import com.sven.redis.RedisService;
import com.sven.result.CodeMsg;
import com.sven.result.Result;
import com.sven.service.MiaoshaUserService;
import com.sven.utils.ValidatorUtil;
import com.sven.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * Created by sven on 2018/1/15.
 */
@Controller
@RequestMapping("login")
public class LoginController {
    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    RedisService redisService;
    @Autowired
    MiaoshaUserService miaoshaUserService;

    @RequestMapping("/to_login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("do_login")
    @ResponseBody
    public Result<Boolean> doLogin(@Valid LoginVo loginVo){
        log.info(loginVo.toString());
        miaoshaUserService.login(loginVo);
        return Result.success(true);
    }
}
