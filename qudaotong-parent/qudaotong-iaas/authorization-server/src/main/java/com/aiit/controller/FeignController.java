package com.aiit.controller;

import com.aiit.api.CommonResult;
import com.aiit.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 呆毛
 */
@RestController
@RequestMapping("/feign")
@Api(tags = "认证服务远程调用接口")
public class FeignController {
    @Resource
    private MemberService memberService;

    @ApiOperation(value = "创建新用户", notes = "创建新用户", tags = {"认证服务远程调用接口"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号", dataType = "string"),
            @ApiImplicitParam(name = "memberId", value = "用户id", dataType = "string"),
    })
    @PostMapping("/createMember")
    public Boolean createMember(String phone, String memberId) {
        try {
            memberService.firstCreateMember(phone, memberId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @ApiOperation(value = "重置密码", notes = "重置密码", tags = {"认证服务远程调用接口"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "password", value = "密码", dataType = "string"),
            @ApiImplicitParam(name = "memberId", value = "用户id", dataType = "string"),
    })
    @PostMapping("/reset")
    public Boolean resetPassword(String memberId, String password) {

        return memberService.resetPassword(memberId, password);
    }
}
