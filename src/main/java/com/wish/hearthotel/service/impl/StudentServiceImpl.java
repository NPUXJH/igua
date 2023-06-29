package com.wish.hearthotel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wish.hearthotel.entities.Student;
import com.wish.hearthotel.mapper.StudentMapper;
import com.wish.hearthotel.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    public  Map<String,Object>  login(String username, String password){
        QueryWrapper<Student> wrapper=new QueryWrapper<>();
        wrapper.eq("studentno",username).eq("pass",password);
        Map<String,Object> map=this.getMap(wrapper);
        if(map==null){
            return null;
        }
        return map;
    }

    @Override
    public List getDorms(Map map) {
        QueryWrapper<Student> wrapper=new QueryWrapper<>();
       String dorm = (String) map.get("dorm");
        wrapper.eq("dorm",dorm);
        List studentmap=this.list(wrapper);;
        if(studentmap==null){
            return null;
        }
        return studentmap;
    }
}
