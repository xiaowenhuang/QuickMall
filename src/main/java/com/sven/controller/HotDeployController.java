package com.sven.controller;

import com.sven.model.User;
import com.sven.redis.RedisService;
import com.sven.result.Result;
import com.sven.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by sven on 2018/1/3.
 */
@Controller
@RequestMapping("hot")
public class HotDeployController {
    @Autowired
    UserService userService;
    @Autowired
    RedisService redisService;

    @RequestMapping(value = "say",method = RequestMethod.GET)
    public String say(Model model){
        model.addAttribute("say", "ds");
        return "imooc";
    }

    @RequestMapping("db/get")
    @ResponseBody
    public Result<User> dbGet(){
        User user = userService.getById(1);
        return Result.success(user);
    }

    @RequestMapping("db/ts")
    @ResponseBody
    public Result<Boolean> testTransaction(){
        userService.ts();
        return Result.success(true);
    }

    @RequestMapping("redis/get")
    @ResponseBody
    public Result<String> redisGet(){
        String v1 = redisService.get("sven",String.class);
        return Result.success(v1);
    }

    @RequestMapping("redis/set")
    @ResponseBody
    public Result<String> redisSet(){
        boolean ret = redisService.set("wack","World of Warcraft");
        String v1 = redisService.get("wack",String.class);
        return Result.success(v1);
    }
}
