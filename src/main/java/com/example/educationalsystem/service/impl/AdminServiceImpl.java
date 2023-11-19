package com.example.educationalsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.educationalsystem.entity.Admin;
import com.example.educationalsystem.mapper.AdminMapper;
import com.example.educationalsystem.service.inter.AdminService;
import org.springframework.stereotype.Service;

/**
 * 功能：
 * 作者： bravekun
 * 日期： 2023/11/8 18:58
 */

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
}
