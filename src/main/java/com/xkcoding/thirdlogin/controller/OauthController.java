package com.xkcoding.thirdlogin.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.google.common.base.Splitter;
import com.xkcoding.thirdlogin.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Oauth 控制器
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/10/15 14:06
 */
@Slf4j
@Controller
@RequestMapping("/oauth")
public class OauthController {
    private final static String CLIENT_ID = "";
    private final static String CLIENT_SECRET = "";

    @GetMapping("/github/callback")
    public String callback(String code, String state, HttpSession session) {
        log.info("【code】= {}", code);
        log.info("【state】= {}", state);

        // 构造请求的参数
        Map<String, Object> params = new HashMap<>(5);
        params.put("client_id", CLIENT_ID);
        params.put("client_secret", CLIENT_SECRET);
        params.put("code", code);
        params.put("redirect_uri", "http://oauth.imooc.com/oauth/github/callback");
        params.put("state", state);
        String tokenBody = HttpUtil.post("https://github.com/login/oauth/access_token", params);
        log.info("【tokenBody】= {}", tokenBody);

        Map<String, String> token = Splitter.on("&").withKeyValueSeparator("=").split(tokenBody);
        log.info("【split】= {}", token);

        String userInfoBody = HttpRequest.get("https://api.github.com/user").header("Authorization", "token " + token.get("access_token")).execute().body();

        log.info("【userInfoBody】= {}", userInfoBody);
        JSONObject userObj = JSONUtil.parseObj(userInfoBody);
        User user = new User().setId(userObj.getStr("id")).setEmail(userObj.getStr("email")).setLocation(userObj.getStr("location")).setUsername(userObj.getStr("login")).setNickname(userObj.getStr("name")).setAvatar(userObj.getStr("avatar_url"));

        session.setAttribute("user", user);
        return "redirect:/";
    }
}
