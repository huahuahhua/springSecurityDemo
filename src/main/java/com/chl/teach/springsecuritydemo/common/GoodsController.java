package com.chl.teach.springsecuritydemo.common;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Chenghl
 * @date: 2024/9/12 11:06
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @RequestMapping("/select")
    public String getGoods() {
        return "这是商品";
    }
}
