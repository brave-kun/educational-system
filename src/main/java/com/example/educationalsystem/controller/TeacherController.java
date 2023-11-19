package com.example.educationalsystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.educationalsystem.common.Result;
import com.example.educationalsystem.entity.Course;
import com.example.educationalsystem.entity.CourseChoose;
import com.example.educationalsystem.entity.Grade;
import com.example.educationalsystem.entity.Student;
import com.example.educationalsystem.service.inter.*;
import com.example.educationalsystem.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：
 * 作者： bravekun
 * 日期： 2023/11/8 21:09
 */


@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseChooseService courseChooseService;

    @Autowired
    private GradeService gradeService;

    /**
     * 教师开设课程
     */
    @PostMapping("/addCourse")
    public Result addCourse(@RequestBody Course course, @RequestHeader(name = "token", required = true) String token) {
        String teacherId = JwtUtils.getTokenInfo(token).getClaim("teacherId").asString();
        String teacherName = JwtUtils.getTokenInfo(token).getClaim("teacherName").asString();
        String role = JwtUtils.getTokenInfo(token).getClaim("role").asString();
        if(!role.equals("1")){
            return Result.error("你不是老师哦");
        }
        try {
            courseService.save(course);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("请保证正确输入");
        }
        return Result.success("开设课程成功");
    }

    /**
     * 教师添加成绩
     */
    @PostMapping("/addGrade")
    public Result addGrade(@RequestBody Grade grade, @RequestHeader(name = "token", required = true) String token) {
        String role = JwtUtils.getTokenInfo(token).getClaim("role").asString();
        if(!role.equals("1")){
            return Result.error("你不是老师哦");
        }
        try {
            gradeService.save(grade);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("请保证正确输入");
        }
        return Result.success("添加成绩成功");
    }

//
//    @PutMapping("/updateGrade")
//    public Result updateGrade(@RequestBody Grade grade, @RequestHeader(name = "token", required = true) String token) {
//
//        gradeService.update();
//        return Result.success();
//    }

    /***
     * 根据课程id查询课程下的学生
     * @param courseId
     * @param token
     * @return
     */
    @GetMapping("/selectStuByCourse/{courseId}")
    public Result selectStuByCourse(@PathVariable String courseId, @RequestHeader(name = "token", required = true) String token) {
        String role = JwtUtils.getTokenInfo(token).getClaim("role").asString();
        if(!role.equals("1")){
            return Result.error("你不是老师哦");
        }
        ArrayList<Student> students;
        try {
            List<CourseChoose> courseChooseList = courseChooseService.list(new QueryWrapper<CourseChoose>().eq("course_id", courseId));
            students = new ArrayList<>();
            for (CourseChoose courseChoose : courseChooseList) {
                String studentId = courseChoose.getStudentId();
                Student student = studentService.getOne(new QueryWrapper<Student>().eq("student_id", studentId));
                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("请保证正确输入");
        }
        return Result.success(students);
    }




}
