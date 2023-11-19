package com.example.educationalsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.educationalsystem.entity.Student;
import com.example.educationalsystem.mapper.StudentMapper;
import com.example.educationalsystem.service.inter.StudentService;
import org.springframework.stereotype.Service;

/**
 * 功能：
 * 作者： bravekun
 * 日期： 2023/11/8 18:59
 */

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
}
