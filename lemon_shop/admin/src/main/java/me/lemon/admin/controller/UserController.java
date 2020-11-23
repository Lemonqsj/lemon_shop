package me.lemon.admin.controller;


import lombok.extern.slf4j.Slf4j;
import me.lemon.admin.dto.UserDto;
import me.lemon.admin.dto.UsersQueryParm;
import me.lemon.admin.service.UserService;
import me.lemon.common.common.ResponseResult;
import me.lemon.database.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/user")
public class UserController {


    @Autowired
    UserService userService;

    @GetMapping
    public ResponseResult queryAllUser(@RequestParam Integer pageSize,
                                       @RequestParam Integer pageNum,
                                       @RequestParam Integer query){
        Pageable pageable=new PageRequest(pageNum,pageSize);
        List<UserDto> userDtos = userService.queryAll(pageable);

        return ResponseResult.success(userDtos);
    }


    @PostMapping("/create")
    public ResponseResult create(@RequestParam User user){
        userService.create(user);

        return ResponseResult.success(null);
    }

    @PostMapping("/delete")
    public ResponseResult delete(@RequestParam User user){
        List list=new ArrayList();
        list.add(user.getUserId());
        userService.delete(list);

        return ResponseResult.success(null);
    }
}
