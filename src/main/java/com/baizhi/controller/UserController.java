package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2019-12-16 18:03:17
 */
@RestController
public class UserController {
    /**
     * 服务对象
     */
    @Autowired
    private UserService userService;


    @GetMapping("/user/queryOne/{id}")
    public User queryOne(@PathVariable("id") Integer id) {
        return this.userService.queryById(id);
    }

    @PostMapping("/user/addUser")
    public void add(@RequestBody User user) {
        userService.insert(user);
    }

    @DeleteMapping("/user/deleteUser/{id}")
    public void delete(@PathVariable("id") Integer id) {
        userService.deleteById(id);
    }

    @PutMapping("/user/updateUser")
    public void update(@RequestBody User user) {
        userService.update(user);
    }

}