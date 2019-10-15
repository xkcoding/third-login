package com.xkcoding.thirdlogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>
 * 首页控制器
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/10/15 13:55
 */
@Controller
public class IndexController {
    @GetMapping
    public String index() {
        return "index";
    }
}
