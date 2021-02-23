package aiit.filter;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 呆毛
 * @date 2021/1/8 16:12
 */
@Component
public class JwtCheckFilter implements GlobalFilter, Ordered {
    @Autowired
    private StringRedisTemplate redisTemplate;


    private static final Set<String> noRequireTokenUris ;
    static {
        noRequireTokenUris = new HashSet<>();
        noRequireTokenUris.add("/admin/login");
        noRequireTokenUris.add("/member/login/password");
        noRequireTokenUris.add("/member/login/authCode");
        noRequireTokenUris.add("/member/register");
        noRequireTokenUris.add("/project/project");
    }
    /**
     * 过滤器拦截到用户请求后做啥
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1、该接口是否需要token才能访问
        if (!isRequireToken(exchange)) {
            //不需要token，直接放行
            return chain.filter(exchange);
        }
        //2、取出用户token
        String token = getUserToken(exchange);
        //3、判断用户token是否有效
        if (StringUtils.isEmpty(token)) {
            return buildNoAuthorizationResult(exchange);
        }
        Boolean hasKey = redisTemplate.hasKey(token);
        if (hasKey != null && hasKey) {
            //token有效，直接放行
            return chain.filter(exchange);
        }
        return buildNoAuthorizationResult(exchange);
    }

    /**
     * 给用户响应一个没有token的错误
     * @param exchange
     * @return
     */
    private Mono<Void> buildNoAuthorizationResult(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().set("Content-Type","application/json");
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("error","no authorization");
        jsonObject.put("errorMsg","token is null or error");
        DataBuffer wrap = response.bufferFactory().wrap(jsonObject.toJSONString().getBytes());
        return response.writeWith(Flux.just(wrap));
    }

    /**
     * 从请求头中获取用户的token
     * @param exchange
     * @return
     */
    private String getUserToken(ServerWebExchange exchange) {
        //Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MTE4Mzc3ODgsInVzZXJfbmFtZSI6IjEiLCJhdXRob3JpdGllcyI6WyJST0xFX0FETUlOIl0sImp0aSI6ImI2MTBmZTVkLWMyNTEtNDg2OS1iMzNhLTMyY2YyZjA3ODg2NiIsImNsaWVudF9pZCI6ImNsb3VkLWluZHVzdHJ5LWFwaSIsInNjb3BlIjpbImFsbCJdfQ.ZrvWW9z8ply1tdbtDzf7oDI0nKBwxTy1bEv1-ie6yVqXOZVfInjXPI7Mw2SHc93cFjKP_RNO2SrskN2tJJy1N4YiIbSrGpJ07S9Cc-vf6OHpWBBqe5i2t0p_Nxuh09k8fBXDVoSeKMpwNzPdbH3qbS0162kLOzKkg6-LnI3BG-BkPxGDp1LxMVqUnG_igSog5kUylwInp3ZANJK7rhGqTKDBU5D-pvFUYRUY-2kp4fnKIcJx7Bjs304cDbobpZPYlPBNUVeAneolkEYgkpI0EzSwmcvFHji1Wf2x7cf5w2r3IBPG0SIghRR3epv0GlEoJq3-uNyRqKYrFts29WcuRQ
        String token =  exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isEmpty(token)){
            return null;
        }
        token = token.replace("Bearer ","");
        return token;

    }

    /**
     * 判断接口是否需要token
     *
     * @param exchange
     * @return
     */
    private boolean isRequireToken(ServerWebExchange exchange) {
        String path = exchange.getRequest().getURI().getPath();
        if (noRequireTokenUris.contains(path)) {
            //不需要token
            return false;
        }
        //需要token
        return true;
    }

    /**
     * 拦截器的顺序
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }

}
