package me.lemon.admin.controller;

import lombok.extern.slf4j.Slf4j;
import me.lemon.admin.dto.DeptDto;
import me.lemon.admin.dto.JobDto;
import me.lemon.admin.service.DeptService;
import me.lemon.common.common.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Slf4j
@RequestMapping("/api/dept")
public class DeptController {


    @Autowired
    DeptService deptService;

    @GetMapping
    public ResponseResult queryAll(@RequestParam Integer pageSize,
                                   @RequestParam Integer pageNum,
                                   @RequestParam Integer query){

        Pageable pageable= new PageRequest(pageNum,pageSize);


        List<DeptDto> dtos = deptService.queryAll(pageable);

        return ResponseResult.success(dtos);

    }

    @GetMapping("/pid")
    public ResponseResult query(@RequestParam Integer pid){
       return ResponseResult.success( deptService.findByPid(pid));
    }


}
