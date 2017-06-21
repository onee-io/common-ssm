package top.onee.ssm.dao.mapper;

import org.springframework.stereotype.Repository;
import top.onee.ssm.modal.DO.UserInfo;

import java.util.List;

/**
 * 用户信息Info
 * Created by VOREVER on 23/04/2017
 */
@Repository
public interface UserInfoMapper {

    /**
     * 获取所有用户信息列表
     * @return
     */
    List<UserInfo> listOfAllUserInfo();

    /**
     * 获取指定ID的用户信息
     * @param id
     * @return
     */
    UserInfo getUserInfoById(long id);

    /**
     * 新增用户信息
     * @param userInfo
     * @return
     */
    int saveUserInfo(UserInfo userInfo);

    /**
     * 更新用户信息
     * @param userInfo
     * @return
     */
    int updateUserInfo(UserInfo userInfo);

    /**
     * 删除用户信息
     * @param id
     * @return
     */
    int deleteUserInfoById(long id);
}
