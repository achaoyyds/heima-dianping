package com.atguigu.vod.controller;

import com.atguigu.R;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping("/admin/user")
public class UserController {

    @PostMapping("login")
    public R login() {

        Map<String, Object> map = new HashMap<>();
        map.put("token", "admin-token");
        return R.ok(map).code(20000);
    }

    @GetMapping("/info")
    public R info(){

        Map<String,Object> map = new HashMap<>();
        map.put("roles", "admin");
        map.put("introduction","I am a super administrator");
        map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("name", "Super Admin");
        return R.ok(map).code(20000);
    }
}
