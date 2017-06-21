package top.onee.ssm.service;

import top.onee.ssm.modal.DO.UserInfo;

import java.util.List;

/**
 * API接口服务
 * Created by VOREVER on 23/04/2017.
 */
public interface CommonService {

    /**
     * 获取所有用户信息
     * @return
     */
    List<UserInfo> getUserInfoList();

    /**
     * 获取指定ID用户信息
     * @param id
     * @return
     */
    UserInfo getUserInfoById(long id);

    /**
     * 新增用户信息
     * @param userInfo
     * @return
     */
    long saveUserInfo(UserInfo userInfo);

    /**
     * 更新用户信息
     * @param userInfo
     * @return
     */
    boolean updateUserInfo(UserInfo userInfo);

    /**
     * 删除用户信息
     * @param id
     * @return
     */
    boolean deleteUserInfo(long id);
}
