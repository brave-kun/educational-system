package com.example.educationalsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.educationalsystem.entity.User;
import com.example.educationalsystem.mapper.UserMapper;
import com.example.educationalsystem.service.inter.UserService;
import org.springframework.stereotype.Service;

/**
 * 功能：
 * 作者： bravekun
 * 日期： 2023/11/9 15:06
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
