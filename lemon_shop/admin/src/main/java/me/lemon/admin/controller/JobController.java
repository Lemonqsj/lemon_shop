package me.lemon.admin.controller;

import lombok.extern.slf4j.Slf4j;
import me.lemon.admin.dto.JobDto;
import me.lemon.admin.service.JobService;
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
@RequestMapping("/api/job")
public class JobController {


    @Autowired
    JobService jobService;


    @GetMapping
    public ResponseResult queryAll(@RequestParam Integer pageSize,
                                   @RequestParam Integer pageNum,
                                   @RequestParam Integer query){
        Pageable pageable= new PageRequest(pageNum,pageSize);


        List<JobDto> dtos = jobService.queryAll(pageable);

        return ResponseResult.success(dtos);

    }
}
