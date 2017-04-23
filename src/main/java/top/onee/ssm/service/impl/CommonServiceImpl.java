package top.onee.ssm.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.onee.ssm.dao.mapper.UserInfoMapper;
import top.onee.ssm.entity.UserInfo;
import top.onee.ssm.service.CommonService;

import java.util.ArrayList;
import java.util.List;

/**
 * API接口服务实现
 * Created by VOREVER on 23/04/2017.
 */
@Service
public class CommonServiceImpl implements CommonService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public List<UserInfo> getUserInfoList() {
        try {
            List<UserInfo> userInfos = userInfoMapper.listOfAllUserInfo();
            return userInfos;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return new ArrayList<>();
    }

    @Override
    public UserInfo getUserInfoById(long id) {
        try {
            UserInfo userInfo = userInfoMapper.getUserInfoById(id);
            return userInfo;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public long saveUserInfo(UserInfo userInfo) {
        try {
            int insertCount = userInfoMapper.saveUserInfo(userInfo);
            if (insertCount == 0) {
                return 0;
            }
            return userInfo.getId();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return 0;
    }

    @Override
    public boolean updateUserInfo(UserInfo userInfo) {
        try {
            int updateCount = userInfoMapper.updateUserInfo(userInfo);
            if (updateCount == 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public boolean deleteUserInfo(long id) {
        try {
            int deleteCount = userInfoMapper.deleteUserInfoById(id);
            if (deleteCount == 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }
}
