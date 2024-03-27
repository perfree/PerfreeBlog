package com.perfree.controller.api.menu.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Schema(description = "菜单tree Response VO")
@Data
public class MenuTreeRespVO extends MenuBaseVO {

    @Schema(description = "主键")
    private String id;

    @Schema(description = "父级ID")
    private String pid;

    @Schema(description = "子菜单")
    private List<MenuTreeRespVO> childMenu;
}