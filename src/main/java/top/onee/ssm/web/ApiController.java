package top.onee.ssm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.onee.ssm.dto.CommonResult;
import top.onee.ssm.entity.UserInfo;
import top.onee.ssm.expand.enums.HttpStateEnum;
import top.onee.ssm.service.impl.CommonServiceImpl;

import java.util.List;

/**
 * 接口控制器（纯数据接口，返回JSON格式数据）
 * 接口设计风格遵循RESTful规范
 * Created by VOREVER on 23/04/2017.
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private CommonServiceImpl commonService;

    @GetMapping("/userInfo/list")
    public CommonResult<List<UserInfo>> getUserInfoList() {
        List<UserInfo> userInfos = commonService.getUserInfoList();
        return new CommonResult<>(HttpStateEnum.SUCCESS, userInfos);
    }

    @GetMapping("/userInfo/{userId}")
    public CommonResult<UserInfo> getUserInfo(@PathVariable long userId) {
        if (userId <= 0) {
            return new CommonResult<>(HttpStateEnum.BAD_PARAMS);
        }
        UserInfo userInfo = commonService.getUserInfoById(userId);
        if (userInfo == null) {
            return new CommonResult<>(HttpStateEnum.NO_DATA);
        }
        return new CommonResult<>(HttpStateEnum.SUCCESS, userInfo);
    }

    @PostMapping("/userInfo")
    public CommonResult<Long> saveUserInfo(@RequestBody UserInfo userInfo) {
        if (userInfo == null) {
            return new CommonResult<>(HttpStateEnum.BAD_PARAMS);
        }
        long userId = commonService.saveUserInfo(userInfo);
        if (userId == 0) {
            return new CommonResult<>(HttpStateEnum.FAIL);
        }
        return new CommonResult<>(HttpStateEnum.SUCCESS, userId);
    }

    @PutMapping("/userInfo/")
    public CommonResult updateUserInfo(@RequestBody UserInfo userInfo) {
        if (userInfo == null) {
            return new CommonResult(HttpStateEnum.BAD_PARAMS);
        }
        boolean result = commonService.updateUserInfo(userInfo);
        if (!result) {
            return new CommonResult(HttpStateEnum.FAIL);
        }
        return new CommonResult(HttpStateEnum.SUCCESS);
    }

    @DeleteMapping("/userInfo/{userId}")
    public CommonResult deleteUserInfo(@PathVariable long userId) {
        if (userId <= 0) {
            return new CommonResult(HttpStateEnum.BAD_PARAMS);
        }
        boolean result = commonService.deleteUserInfo(userId);
        if (!result) {
            return new CommonResult(HttpStateEnum.FAIL);
        }
        return new CommonResult(HttpStateEnum.SUCCESS);
    }
}
