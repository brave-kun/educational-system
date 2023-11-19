package com.example.educationalsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.educationalsystem.entity.Teacher;
import com.example.educationalsystem.mapper.TeacherMapper;
import com.example.educationalsystem.service.inter.TeacherService;
import org.springframework.stereotype.Service;

/**
 * 功能：
 * 作者： bravekun
 * 日期： 2023/11/8 18:59
 */

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
}
