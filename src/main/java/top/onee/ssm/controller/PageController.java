package top.onee.ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.onee.ssm.entity.UserInfo;
import top.onee.ssm.service.impl.CommonServiceImpl;

import java.util.List;

/**
 * 页面控制器（只演示传递数据到JSP页面）
 * Created by VOREVER on 23/04/2017.
 */
@Controller
@RequestMapping("/page")
public class PageController {

    @Autowired
    private CommonServiceImpl commonService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listOfAllUserInfo(Model model) {
        List<UserInfo> userInfos = commonService.getUserInfoList();
        model.addAttribute("userInfos", userInfos);
        return "user_info_list";
    }

}
