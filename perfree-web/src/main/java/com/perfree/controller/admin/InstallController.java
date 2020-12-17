package com.perfree.controller.admin;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.setting.dialect.Props;
import com.perfree.common.ResponseBean;
import com.perfree.controller.BaseController;
import com.perfree.model.Database;
import com.perfree.model.User;
import com.perfree.service.InstallService;
import com.perfree.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.io.File;

@Controller
public class InstallController extends BaseController {
    private final static Logger LOGGER = LoggerFactory.getLogger(InstallController.class);
    @Autowired
    private InstallService installService;

    @Autowired
    private UserService userService;

    @RequestMapping("/install")
    public String installPage() {
        return view("static/admin/pages/install/install.html");
    }

    @RequestMapping("/install/step2")
    public String installStep2Page() {
        return view("static/admin/pages/install/install-step2.html");
    }

    @RequestMapping("/install/step3")
    public String installStep3Page() {
        return view("static/admin/pages/install/install-step3.html");
    }

    @RequestMapping("/install/addDatabase")
    @ResponseBody
    public ResponseBean addDatabase(@RequestBody @Valid Database database) {
        try {
            installService.addDatabase(database);
            return ResponseBean.success("配置成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return ResponseBean.fail("数据库连接或创建数据库失败", e.getMessage());
        }
    }

    @RequestMapping("/install/addAdminUser")
    @ResponseBody
    public ResponseBean addAdminUser(@RequestBody User user) {
        user.setUserName(user.getAccount());
        user.setRoleId(1L);
        user.setStatus(0);
        if (StringUtils.isBlank(user.getPassword()) || user.getPassword().length() < 6 || user.getPassword().length() > 12){
            return ResponseBean.fail("密码不能为空且在6-12字符之间", null);
        }
        if (userService.getUserByAccount(user.getAccount()) != null){
            return ResponseBean.fail("账户已存在", null);
        }
        if (userService.add(user) > 0) {
            File file = new File("resources/db.properties");
            Props setting = new Props(FileUtil.touch(file), CharsetUtil.CHARSET_UTF_8);
            setting.setProperty("installStatus","success");
            setting.store(file.getAbsolutePath());
            return ResponseBean.success("账户创建成功", null);
        }
        return ResponseBean.fail("账户创建失败", null);
    }

}
