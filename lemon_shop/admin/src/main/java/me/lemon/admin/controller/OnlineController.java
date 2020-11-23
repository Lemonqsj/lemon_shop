package me.lemon.admin.controller;


import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lemon.admin.service.OnlineUserService;
import me.lemon.common.common.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/online")
@Slf4j
public class OnlineController {

    @Autowired
    OnlineUserService onlineUserService;

    @ApiOperation("查询在线用户")
    @GetMapping
    public ResponseResult queryAll(@RequestParam Integer pageSize,
                                @RequestParam Integer pageNum,
                                @RequestParam Integer query){

        Pageable pageable= new PageRequest(pageNum,pageSize);

        List all = onlineUserService.getAll(pageable);
        log.info(all.toString());

        return ResponseResult.success(all);
    }
}
