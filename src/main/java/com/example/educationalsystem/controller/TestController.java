package com.example.educationalsystem.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能：
 * 作者： bravekun
 * 日期： 2023/11/9 17:16
 */

@RestController
@RequestMapping("/test")
public class TestController {
    @ResponseBody
    @GetMapping("/index")
    public String index(HttpServletRequest request) {
        return "重定向访问! ";
    }

    @GetMapping("/r1")
    public String r1() {
        return "redirect:/s002";
    }

    @GetMapping()
    public String r2() {
        return "???";
    }
}


