package com.sven.controller;

import com.sven.model.MiaoshaUser;
import com.sven.model.User;
import com.sven.redis.MiaoshaUserKey;
import com.sven.redis.RedisService;
import com.sven.service.GoodsService;
import com.sven.service.MiaoshaUserService;
import com.sven.vo.Goodsvo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by sven on 2018/3/18.
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);
    @Autowired
    MiaoshaUserService miaoshaUserService;
    @Autowired
    RedisService redisService;

    @Autowired
    GoodsService goodsService;

    @RequestMapping("to_list")
    public String list(Model model,MiaoshaUser user){
        model.addAttribute("user",user);
        //query goods table
        List<Goodsvo> goodsVo = goodsService.listGoodsVo();
        model.addAttribute("goodsList",goodsVo);
        return "goods_list";
    }

    @RequestMapping("to_detail")
    public String detail(HttpServletResponse response,Model model,@CookieValue(value = MiaoshaUserService.COOKIE_NAME_TOKEN,required = false) String cookieToken
            ,@RequestParam(value=MiaoshaUserService.COOKIE_NAME_TOKEN,required=false)String paramToken){
        if(StringUtils.isEmpty(cookieToken)&&StringUtils.isEmpty(paramToken)){
            return "login";
        }
        String token = StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
        MiaoshaUser user = miaoshaUserService.getByToken(token,response);
        model.addAttribute("user",user);
        return "goods_list";
    }
}
