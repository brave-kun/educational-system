package com.example.educationalsystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.educationalsystem.common.Result;
import com.example.educationalsystem.entity.Admin;
import com.example.educationalsystem.entity.Student;
import com.example.educationalsystem.entity.Teacher;
import com.example.educationalsystem.entity.User;
import com.example.educationalsystem.service.inter.AdminService;
import com.example.educationalsystem.service.inter.StudentService;
import com.example.educationalsystem.service.inter.TeacherService;
import com.example.educationalsystem.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能：
 * 作者： bravekun
 * 日期： 2023/11/8 21:11
 */

@RestController
//@RequestMapping("/login")
public class LoginController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private AdminService adminService;

//    @Override
//    @GetMapping ("/login")
//    protected void service(@RequestBody HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//    }

    /**
     * 实现用户登录识别身份，然后跳转到相关操作层
     */
    @GetMapping("/login")
    public Result login(@RequestBody User user) {

        String role = user.getRole();
        String userId = user.getUserId();
        String userPassword = user.getUserPassword();


        if (role.equals("0")) {
            Student student = studentService.getOne(new QueryWrapper<Student>().eq("student_id", userId).eq("student_password", userPassword));
            Map<String, String> payload = new HashMap<>();
            payload.put("studentId", student.getStudentId());
            payload.put("studentName", student.getStudentName());
            payload.put("role", "0");
            String token = JwtUtils.getToken(payload);
            return Result.success(token);
            //return "redirect:/student?token=" + token;
        } else if (role.equals("1")) {
            Teacher teacher = teacherService.getOne(new QueryWrapper<Teacher>().eq("teacher_id", userId).eq("teacher_password", userPassword));
            Map<String, String> payload = new HashMap<>();
            payload.put("teacherId", teacher.getTeacherId());
            payload.put("teacherName", teacher.getTeacherName());
            payload.put("role", "1");
            String token = JwtUtils.getToken(payload);
            return Result.success(token);
            //return "redirect:/teacher";
        } else if (role.equals("2")) {
            Admin admin = adminService.getOne(new QueryWrapper<Admin>().eq("admin_id", userId).eq("admin_password", userPassword));
            Map<String, String> payload = new HashMap<>();
            payload.put("adminId", admin.getAdminId());
            payload.put("role", "2");
            //payload.put("userId", admin.getAdminId());
            String token = JwtUtils.getToken(payload);
            return Result.success(token);
            //return "redirect:/admin";
        } else {
            return Result.error("不存在当前用户");
            //return "/login";
        }


    }

}
