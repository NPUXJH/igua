/*
文件名称：AdministratorController.js
当前版本：V1.0
作者：灰灰吃不饱项目组
完成日期：2023年6月30日
*/
package com.wish.hearthotel.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wish.hearthotel.entities.*;
import com.wish.hearthotel.service.impl.*;
import com.wish.hearthotel.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
@Controller
@RequestMapping("/administrator")
public class AdministratorController {
   @Autowired
   StudentServiceImpl studentService;
   @Autowired
   HousemasterServiceImpl housemasterService;
   @Autowired
   CleanServiceImpl cleanService;

   @Autowired
   AdministratorServiceImpl administratorService;
   //去往管理员登录页面
    @RequestMapping(value="/")
    public String login(){
        return "/login/adminlogin";
    }
    /*
    函数名：logintest
    输入参数：用户名和密码的字符串
    返回值：传给前台数据判断是否登陆成功
    函数功能：检验用户名和密码是否与数据库中的匹配
     */
    @ResponseBody
    @RequestMapping("/loginadmin")
    public ResultBean logintest(@RequestParam("username")String name,
                                @RequestParam("password")String pass,HttpServletRequest request){
        QueryWrapper<Administrator> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("adminno", name);
        Administrator one = administratorService.getOne(queryWrapper);
        if (one==null){
            return new ResultBean(1);
        }else {
            if (one.getPass().equals(pass)) {
                HttpSession session=request.getSession();
                session.setAttribute("username",name);
                session.setAttribute("role","studentmasteradmin");
                return new ResultBean();
            }else {
                return new ResultBean(1);
            }
        }
    }
    //账号验证成功，进入domain,
    @RequestMapping("/todomain")
    public String todomain(HttpServletRequest request){
        List<Housemaster> list = housemasterService.list();
        request.setAttribute("list",list);
        return "/domain/yonghu";
    }

    //退出回到选择登录页面
    @RequestMapping("/logout")
    public String logout(){
            return "index";
    }
   //进入管理员添加删除用户页面，首先需要显示学生页面
   @ResponseBody
   @RequestMapping("/getStudent")
   public ResultBean getStudent (){
       List<Student> list = studentService.list();
       return new ResultBean<>(list);
   }

   //查找所有宿舍管理员
   @RequestMapping("/getMaster")
   @ResponseBody
   public ResultBean getMaster(){
       List<Housemaster> list = housemasterService.list();
       return new ResultBean<>(list);
   }

   //增加学生
    @RequestMapping("/addstudent")
    @ResponseBody
    public ResultBean add(Student student){
       studentService.save(student);
       return new ResultBean();
    }
    //增加宿舍管理员
    @ResponseBody
    @RequestMapping("/addmaster")
    public ResultBean add(Housemaster housemaster){
        System.out.println("==================");
        System.out.println(housemaster);
        housemasterService.save(housemaster);
        return new ResultBean();
    }
    //删除宿舍管理员
    @ResponseBody
    @RequestMapping("/deletemaster")
    public ResultBean deletemaster(@RequestParam("id")Integer id){
        housemasterService.removeById(id);
        return new ResultBean();
    }
    //根据id查找宿舍管理员
    @ResponseBody
    @RequestMapping("/masterbyid")
    public ResultBean findmaster(@RequestParam("id")Integer id){
        Housemaster byId = housemasterService.getById(id);
        return new ResultBean<>(byId);
    }

    //修改宿舍管理员
    @ResponseBody
    @RequestMapping("/updatemaster")
    public ResultBean deletemaster(@RequestBody Housemaster housemaster){
        System.out.println("==================================================");
        System.out.println(housemaster);
        housemasterService.updateById(housemaster);
        return new ResultBean();
    }

    //删除学生
    @RequestMapping("/deletestudent/{id}")
    @ResponseBody
    public ResultBean deleteStudent(@PathVariable("id")Integer id){
        studentService.removeById(id);
        return new ResultBean();
    }
    //修改学生
    @RequestMapping("/updatestudent")
    @ResponseBody
    public ResultBean updateStudent(Student student){
        studentService.updateById(student);
        return new ResultBean();
    }
    //根据id查找学生
    @RequestMapping("/getStudentByid/{id}")
    @ResponseBody
    public ResultBean getStudentById(@PathVariable("id")Integer id){
        Student byId = studentService.getById(id);
        return new ResultBean<>(byId);
    }


    //根据宿舍号，查看卫生分数、
    @RequestMapping("/getclean")
    @ResponseBody
    public ResultBean getClean(@RequestParam("dorm")String dorm){
        QueryWrapper<Clean> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dorm", dorm);
        Clean one = cleanService.getOne(queryWrapper);
        return new ResultBean<>(one);
    }

    //根据宿舍号，修改卫生分数、
    @RequestMapping("/updateclean")
    @ResponseBody
    public ResultBean updateClean(Clean clean){
        cleanService.updateById(clean);
        return new ResultBean<>();
    }

}

