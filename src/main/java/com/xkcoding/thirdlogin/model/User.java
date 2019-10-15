package com.xkcoding.thirdlogin.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * github 用户信息
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/10/15 15:12
 */
@Data
@Accessors(chain = true)
public class User {
    private String id;
    private String username;
    private String nickname;
    private String email;
    private String location;
    private String avatar;
}
