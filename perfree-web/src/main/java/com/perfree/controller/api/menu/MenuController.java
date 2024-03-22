package com.perfree.controller.api.menu;

import com.perfree.base.BaseApiController;
import com.perfree.commons.CommonResult;
import com.perfree.controller.api.menu.vo.MenuListReqVO;
import com.perfree.controller.api.menu.vo.MenuRespVO;
import com.perfree.convert.MenuConvert;
import com.perfree.model.Menu;
import com.perfree.service.menu.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Tag(name = "菜单相关")
@RequestMapping("/api/menu")
public class MenuController extends BaseApiController {

    @Resource
    private MenuService menuService;

    @PostMapping("/queryList")
    @Operation(summary = "菜单列表(可搜索)")
    public CommonResult<List<MenuRespVO>> queryList(@RequestBody MenuListReqVO menuListReqVO) {
        List<Menu> menuList = menuService.menuList(menuListReqVO);
        return CommonResult.success(MenuConvert.INSTANCE.convertListRespVO(menuList));
    }
}
