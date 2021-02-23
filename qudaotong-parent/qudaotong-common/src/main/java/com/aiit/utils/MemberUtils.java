package com.aiit.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 呆毛
 */

public class MemberUtils {

    /**
     *
     * 从HttpServletRequest中获取用户id
     * @param request 前端请求
     * @return 返回用户id，若未得到则返回null
     */
    public static String getMemberIdFromRequest(HttpServletRequest request){
        if (request==null){
            return null;
        }
        String authorization = request.getHeader("Authorization");
        if (authorization==null){
            return null;
        }
        String jwt = authorization.replace("Bearer ", "");
        return getMemberIdFromJwt(jwt);
    }

    /**
     *
     * 从jwt中获取用户id
     * @param jwt jwt
     * @return 返回用户id，若未得到则返回null
     */
    public static String getMemberIdFromJwt(String jwt){
        if (StringUtils.isEmpty(jwt)){
            return null;
        }
        Jwt jwtObject = JwtHelper.decode(jwt);
        String claims = jwtObject.getClaims();
        JSONObject jsonObject = JSONObject.parseObject(claims);
        Object param = jsonObject.get("user_name");
        if (param!=null){
            return param.toString();
        }
        return null;
    }


}
