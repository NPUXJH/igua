package com.wish.hearthotel.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.wish.hearthotel.entities.Clean;
import com.wish.hearthotel.entities.Notice;
import com.wish.hearthotel.entities.Repairs;
import com.wish.hearthotel.entities.Student;
import com.wish.hearthotel.service.CleanService;
import com.wish.hearthotel.service.NoticeService;
import com.wish.hearthotel.service.RepairsService;
import com.wish.hearthotel.service.StudentService;
import com.wish.hearthotel.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private RepairsService repairsService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private CleanService cleanService;
    //登录
    @ResponseBody
    @PostMapping(value="/loginstudent")
    public ResultBean loginstudent(HttpServletRequest request){
        String username=request.getParameter("username");	//获取用户名
        String password=request.getParameter("password");	//获取用户的密码
        if(studentService.login(username,password)==null){	//若密码为空返回
            return new ResultBean<>(1);
        }else{
            HttpSession userSession= request.getSession();		//若密码输入正确则正常登录系统
            userSession.setAttribute("username",studentService.login(username,password));
            userSession.setAttribute("role","student");
            return  new ResultBean<>(0);
        }
    }
    @RequestMapping(value="/")
    public String login(){
        return "/login/studentlogin";	//该方法返回登录页面地址
    }
    //个人信息查询
    @RequestMapping(value="/getuser")
    public String getuser(HttpServletRequest request){
        HttpSession session=   request.getSession();
        Map<String,Object> map = (Map<String, Object>) session.getAttribute("username");	//获取username
        System.out.println(map.toString());
        QueryWrapper<Student> wrapper=new QueryWrapper<>();
        wrapper.eq("studentno",map.get("studentno"));	//从数据库中通过学生序号获取对应学生信息
        request.setAttribute("list",studentService.list(wrapper));
        return  "/douser/userinfo";	//在这里返回学生信息页面
    }

    //公告填写
    @ResponseBody
    @PostMapping (value="/setNotice")
    public ResultBean<List> setNotice(Notice notice){
        return  new ResultBean<List>(noticeService.save(notice)?0:1) ;	//设置公告
    }
    //报修查询
    @GetMapping(value="/getrepairs")
    public String  getrepairs(HttpServletRequest request){
        HttpSession session=   request.getSession();
        Map<String,Object> map = (Map<String, Object>) session.getAttribute("username");	//获取username
        System.out.println(map.toString());
        QueryWrapper<Repairs> wrapper=new QueryWrapper<>();
        wrapper.eq("dorm",map.get("dorm"));		//从数据库中通过dorm获取对应报修信息
        request.setAttribute("repairslist",repairsService.list(wrapper));	//返回报修信息表
        return "/douser/getrepair";	//在这里返回报修信息页面
    }
    //报修填写
    @ResponseBody
    @PostMapping(value="/tianxierepairs")
    public ResultBean setrepairs(HttpServletRequest request){
        String dorm=request.getParameter("dorm");
        String dormitorys=request.getParameter("dormitorys");
        String tel=request.getParameter("tel");
        Repairs repairs=new Repairs();
        repairs.setWhether("否");
        repairs.setTime(String.valueOf(new Date()));
        repairs.setDorm(dorm);
        repairs.setDormitorys(dormitorys);
        repairs.setTel(tel);
        return new ResultBean<>(repairsService.save(repairs) ? 0:1);
    }
    //报修填写界面
    @GetMapping(value="/setrepairs")
    public String  setrepairs(){
        return "/douser/setrepair";
    }
    //报修修改
    @ResponseBody
    @PostMapping(value="/updaterepairs")
    public ResultBean updaterepairs(Repairs repairs){
        QueryWrapper<Repairs> wrapper=new QueryWrapper<>();
        wrapper.eq("id",repairs.getId());
        return new ResultBean<>(repairsService.update(repairs,wrapper));
    }
    //卫生情况查询
    @RequestMapping(value="/getclean")
    public String getclean(HttpServletRequest request){
        HttpSession session=   request.getSession();
        System.out.println(session.getAttribute("username"));
        Map<String,Object> map = (Map<String, Object>) session.getAttribute("username");
        System.out.println(map);
        QueryWrapper<Clean> wrapper=new QueryWrapper<>();
        wrapper.eq("dorm",map.get("dorm"));
        Clean clean = cleanService.getOne(wrapper);
        if(clean==null){
            clean=new Clean();
        }
        System.out.println(clean);

        Integer count=clean.getBed()+clean.getDesk()+clean.getFloor();
        request.setAttribute("clean",clean);
        request.setAttribute("count",count);
        return "/douser/clean";
    }
    @RequestMapping(value="/outlogin")
    public String outlogin(HttpServletRequest request){
        request.getSession().removeAttribute("username");
        request.getSession().removeAttribute("role");
        return "/index";
    }

}

