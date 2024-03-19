package com.perfree.controller.api;

import com.perfree.base.BaseController;
import com.perfree.commons.ResponseBean;
import com.perfree.plugin.PluginHolder;
import com.perfree.plugin.PluginInfo;
import com.perfree.service.PluginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.pf4j.PluginState;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Tag(name = "插件相关")
@RequestMapping("/api/plugin")
public class PluginController extends BaseController {
    @Resource
    private PluginService pluginService;

    @GetMapping("/getAllPlugin")
    @Operation(summary = "获取所有插件")
    public ResponseBean getAllPlugin() {
        return ResponseBean.success("success", pluginService.getAll());
    }

    @GetMapping("/getStartPlugin")
    @Operation(summary = "根据插件ID获取已启动的插件")
    public ResponseBean getStartPlugin(@RequestParam("id") String id) {
        PluginInfo plugin = PluginHolder.getPlugin(id);
        if (plugin != null && plugin.getPluginWrapper().getPluginState().equals(PluginState.STARTED)) {
            return ResponseBean.success("success", pluginService.getByName(id));
        }
        return ResponseBean.fail("未获取到插件", null);
    }
}
