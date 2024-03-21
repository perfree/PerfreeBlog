package com.perfree.base;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.setting.dialect.Props;
import com.perfree.cache.OptionCacheService;
import com.perfree.commons.Constants;
import com.perfree.commons.MultipleSiteUtil;
import com.perfree.enums.OptionEnum;
import com.perfree.security.SecurityFrameworkUtils;
import com.perfree.security.vo.LoginUserVO;
import com.perfree.shared.api.user.UserApi;
import com.perfree.shared.api.user.dto.UserDTO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.File;

@Controller
public class BaseController {
    @Resource
    private UserApi userApi;

    @Resource
    private OptionCacheService optionCacheService;

    /**
     * 获取已登录用户信息
     *
     * @return User
     */
    public LoginUserVO getUser() {
        return SecurityFrameworkUtils.getLoginUser();
    }

    /**
     * 获取当前启用的主题
     *
     * @return String
     */
    public String currentTheme() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String previewTheme = request.getParameter("previewTheme");
        if (StringUtils.isNotBlank(previewTheme)) {
            return previewTheme;
        }
        return optionCacheService.getOptionValue(OptionEnum.WEB_THEME.getKey(), MultipleSiteUtil.currentSite());
    }


    /**
     * 获取当前启用的主题
     *
     * @return String
     */
    public String currentThemePage() {
        return "static/themes/" + currentTheme();
    }

    /**
     * 多出这个方法是因为直接返回的话,idea报黄线且不能自动链接至该文件(该死的强迫症)
     *
     * @param viewPath viewPath
     * @return String
     */
    public String view(String viewPath) {
        File file = new File(Constants.PROD_RESOURCES_PATH + Constants.SEPARATOR + viewPath);
        File devFile = com.perfree.commons.FileUtil.getClassPathFile(Constants.DEV_RESOURCES_PATH + Constants.SEPARATOR + viewPath);
        if (!file.exists() && (devFile == null || !devFile.exists())) {
            return "static/admin/pages/exception/page.html";
        }
        return viewPath;
    }

    /**
     * 渲染page
     *
     * @return String
     */
    public String pageView(String viewPath) {
        File file = new File(Constants.PROD_THEMES_PATH + Constants.SEPARATOR + currentTheme()
                + Constants.SEPARATOR + viewPath);
        File devFile = com.perfree.commons.FileUtil.getClassPathFile(Constants.DEV_THEMES_PATH + Constants.SEPARATOR + currentTheme()
                + Constants.SEPARATOR + viewPath);
        if (!file.exists() && (devFile == null || !devFile.exists())) {
            return universalPage();
        }
        return view(currentThemePage() + Constants.SEPARATOR + viewPath);
    }

    /**
     * 返回通用的page
     *
     * @return String
     */
    public String universalPage() {
        File file = new File(Constants.PROD_THEMES_PATH + Constants.SEPARATOR + currentTheme() + "/page.html");
        File devFile = com.perfree.commons.FileUtil.getClassPathFile(Constants.DEV_THEMES_PATH +
                Constants.SEPARATOR + currentTheme() + "/page.html");
        if (!file.exists() && (devFile == null || !devFile.exists())) {
            return view("static/admin/pages/exception/page.html");
        }
        return currentThemePage() + "/page.html";
    }

    /**
     * 自动判断返回哪个页面
     *
     * @param validPath     要判断的地址
     * @param themeViewPath 该页面在主题中的路径
     * @param adminViewPath 该页面在系统中的路径
     * @return String
     */
    public String view(String validPath, String themeViewPath, String adminViewPath) {
        File file = new File(Constants.PROD_THEMES_PATH + Constants.SEPARATOR + currentTheme() + validPath);
        File devFile = com.perfree.commons.FileUtil.getClassPathFile(Constants.DEV_THEMES_PATH +
                Constants.SEPARATOR + currentTheme() + validPath);
        if (file.exists() || (devFile != null && devFile.exists())) {
            return view("static/themes/" + currentTheme() + themeViewPath);
        } else {
            return view(adminViewPath);
        }
    }


    /**
     * 自动判断返回哪个页面(插件专用)
     *
     * @param validPath     要判断的地址
     * @param themeViewPath 该页面在主题中的路径
     * @param adminViewPath 该页面在系统中的路径
     * @return String
     */
    public String pluginView(String validPath, String themeViewPath, String adminViewPath) {
        File file = new File(Constants.PROD_THEMES_PATH + Constants.SEPARATOR + currentTheme() + validPath);
        File devFile = com.perfree.commons.FileUtil.getClassPathFile(Constants.DEV_THEMES_PATH +
                Constants.SEPARATOR + currentTheme() + validPath);
        if (file.exists() || (devFile != null && devFile.exists())) {
            return view("static/themes/" + currentTheme() + themeViewPath);
        } else {
            return adminViewPath;
        }
    }

    /**
     * 获取安装进度
     *
     * @return String
     */
    public String getInstallStatus() {
        File file = new File(Constants.DB_PROPERTIES_PATH);
        if (!file.exists()) {
            return null;
        }
        Props dbSetting = new Props(FileUtil.touch(file), CharsetUtil.CHARSET_UTF_8);
        return dbSetting.getStr("installStatus");
    }
}
