package com.wish.hearthotel.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.wish.hearthotel.entities.*;
import com.wish.hearthotel.service.RepairsService;
import com.wish.hearthotel.service.impl.CleanServiceImpl;
import com.wish.hearthotel.service.impl.HousemasterServiceImpl;
import com.wish.hearthotel.service.impl.StudentServiceImpl;
import com.wish.hearthotel.util.ResultBean;
import org.apache.coyote.OutputBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/housemaster")
public class HousemasterController {
    @Autowired
    HousemasterServiceImpl housemasterService;
    @Autowired
    StudentServiceImpl studentService;
    @Autowired
    CleanServiceImpl cleanService;
    @Autowired
    private RepairsService repairsService;
    //根据id更改学生寝室
    @ResponseBody
    @RequestMapping("/roledormm")
    public ResultBean roleDorm(@RequestParam("id")Integer id){
        UpdateWrapper<Student> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",1);
        Student student = new Student();
        student.setDorm("3051");
        studentService.update(student,updateWrapper);
       return new ResultBean();
    }

    //根据宿舍号（id）打分
    @ResponseBody
    @RequestMapping("/check")
    public ResultBean check(Clean clean){
        cleanService.save(clean);
        return new ResultBean();
    }

    //去往宿舍管理员登录页面
    @RequestMapping(value="/")
    public String login(){
        return "/login/masterlogin";
    }
    //校验宿舍管理员登录
    @ResponseBody
    @RequestMapping("/loginadmin")
    public ResultBean logintest(@RequestParam("username")String name,
                                @RequestParam("password")String pass,HttpServletRequest request){
        QueryWrapper<Housemaster> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("workno", name);
        Housemaster master = housemasterService.getOne(queryWrapper);
        if (master==null){
            return new ResultBean(1);
        }else {
            if (master.getPass().equals(pass)) {
                HttpSession session=request.getSession();
                session.setAttribute("username",name);
                session.setAttribute("role","studentmaster");
                return new ResultBean();
            }else {
                return new ResultBean(1);
            }
        }
    }
    //账号验证成功，进入domain,
    @RequestMapping("/todomain")
    public String todomain(HttpServletRequest request){
        List<Student> list = studentService.list();
        request.setAttribute("list",list);
        return "/domain/Mstudent";
    }


    //删除用户
    @RequestMapping("/deletestudent")
    @ResponseBody
    public ResultBean deleteStudent(@RequestParam("id")Integer id){
        studentService.removeById(id);
        return new ResultBean();
    }
    //增加用户
    @RequestMapping("/addstudent")
    @ResponseBody
    public ResultBean addStudent(Student student){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        student.setAdmission(sf.format(c.getTime()));
        c.add(Calendar.YEAR,4);
        student.setGraduation(sf.format(c.getTime()));
        studentService.save(student);
        return new ResultBean();
    }
    //通过id查找用户
    @RequestMapping("/byid")
    @ResponseBody
    public ResultBean findStudent(@RequestParam("id")Integer id){
        Student byId = studentService.getById(id);
        return new ResultBean(byId);
    }
    //通过id查找宿舍卫生
    @RequestMapping("/byidclean")
    @ResponseBody
    public ResultBean findclean(@RequestParam("id")Integer id){
        Clean byId = cleanService.getById(id);
        return new ResultBean<>(byId);
    }
    //通过id修改学生信息
    @RequestMapping("/updatestudent")
    @ResponseBody
    public ResultBean updateStudent(@RequestBody Student student){
       studentService.updateById(student);
        return new ResultBean();
    }
    //通过id修改学生信息
    @RequestMapping("/updateclean")
    @ResponseBody
    public ResultBean cleanupdate(@RequestBody Clean student){
        System.out.println(student);
        cleanService.updateById(student);
        return new ResultBean();
    }

    //去往卫生页面
    @RequestMapping("/toclean")
    public String toclean(HttpServletRequest request){
        List<Clean> list = cleanService.list();
        request.setAttribute("list",list);
        return "/domain/Mclean";
    }

    @RequestMapping("/deleteclean")
    @ResponseBody
    public ResultBean deleteclean(@RequestParam("id")Integer id){
        cleanService.removeById(id);
        return new ResultBean();
    }
    //增加用户
    @RequestMapping("/addclean")
    @ResponseBody
    public ResultBean addClean(Clean clean){
        cleanService.save(clean);
        return new ResultBean();
    }
    //获取学生填报的维修信息
    @RequestMapping(value="/getrepairs")
    public String getrepairs(HttpServletRequest request){
    request.setAttribute("repairslist",repairsService.list());
        return "/domain/repairs";
    }
    //进行维修后修改维修信息
    @RequestMapping("/updaterepairs")
    @ResponseBody
    public ResultBean updaterepairs(@RequestBody Repairs repairs){
        System.out.println(repairs.toString());
        return new ResultBean( repairsService.updateById(repairs));
    }
    //通过id查找维修信息
    @RequestMapping("/byidrepairs")
    @ResponseBody
    public ResultBean findrepairs(@RequestParam("id")Integer id){
        Repairs byId = repairsService.getById(id);
        return new ResultBean(byId);
    }

}

