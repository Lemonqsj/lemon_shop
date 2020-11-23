package me.lemon.admin.controller;

import com.alibaba.fastjson.JSON;
import me.lemon.admin.dto.MenuDto;
import me.lemon.admin.service.MenuService;
import me.lemon.common.common.ResponseResult;
import me.lemon.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.table.TableRowSorter;
import java.util.List;

@RestController
@RequestMapping("/api/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;




    @GetMapping
    public ResponseResult  getMenu(@RequestParam String pid){

        return ResponseResult.success( menuService.getMenus(pid));
    }

    @GetMapping("/build")
    public ResponseResult  buildMenu(){

        List<MenuDto> menuDtos = menuService.findByUser(SecurityUtils.getCurrentUserId());

        List<MenuDto> tree = menuService.buildTree(menuDtos, null);

        return ResponseResult.success(tree);
    }
}
