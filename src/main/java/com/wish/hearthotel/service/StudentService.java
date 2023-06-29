package com.wish.hearthotel.service;

import com.wish.hearthotel.entities.Student;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;


public interface StudentService extends IService<Student> {
    public Map login(String username, String password);
    public List getDorms(Map map);
}

