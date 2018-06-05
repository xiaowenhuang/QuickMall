package com.sven.service;

import com.sven.dao.GoodsDao;
import com.sven.vo.Goodsvo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sven on 2018/6/5.
 */
@Service
public class GoodsService {
    @Autowired
    GoodsDao goodsDao;

    public List<Goodsvo> listGoodsVo(){
        return goodsDao.listGoodsVo();
    }
}
