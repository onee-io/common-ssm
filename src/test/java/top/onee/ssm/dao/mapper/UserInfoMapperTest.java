package top.onee.ssm.dao.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.onee.ssm.entity.UserInfo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * UserInfo DAO 单元测试
 * Created by VOREVER on 23/04/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml"
})
public class UserInfoMapperTest {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    public void listOfAllUserInfo() throws Exception {
        List<UserInfo> userInfos = userInfoMapper.listOfAllUserInfo();
        System.out.println(userInfos);
    }

    @Test
    public void getUserInfoById() throws Exception {
        long userId = 1;
        UserInfo userInfo = userInfoMapper.getUserInfoById(userId);
        System.out.println(userInfo);
    }

    @Test
    public void testUserInfo() throws Exception {
        /*新建用户信息*/
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("test");
        userInfo.setAge(66);
        userInfo.setBalance(BigDecimal.valueOf(666.88));
        userInfo.setVip(true);
        userInfo.setGmtCreate(new Date());
        userInfo.setGmtModified(new Date());
        int insertCount = userInfoMapper.saveUserInfo(userInfo);
        Assert.assertEquals(insertCount, 1);
        /*更新用户信息*/
        userInfo.setAge(88);
        userInfo.setGmtModified(new Date());
        int updateCount = userInfoMapper.updateUserInfo(userInfo);
        Assert.assertEquals(updateCount, 1);
        /*删除用户信息*/
        int deleteCount = userInfoMapper.deleteUserInfoById(userInfo.getId());
        Assert.assertEquals(deleteCount, 1);
    }

}