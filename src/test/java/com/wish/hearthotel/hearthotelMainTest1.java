package com.wish.hearthotel;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.wish.hearthotel.entities.Student;
import com.wish.hearthotel.service.impl.AdministratorServiceImpl;
import com.wish.hearthotel.service.impl.StudentServiceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class hearthotelMainTest1 {
    @Autowired
    AdministratorServiceImpl administratorService;
    @Autowired
    StudentServiceImpl studentService;
    @Test
    public void contextLoads() {
        System.out.println("=====================================");
        System.out.println(studentService);
        UpdateWrapper<Student> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id",1);
            Student student = new Student();
            student.setDorm("3051");
            studentService.update(student,updateWrapper);
    }

}