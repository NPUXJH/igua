package com.wish.hearthotel.config;

import com.wish.hearthotel.config.Filter.AdminFilter;
import com.wish.hearthotel.config.Filter.MasterFilter;
import com.wish.hearthotel.config.Filter.StudentFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FilterConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new StudentFilter())
                .addPathPatterns("/student/**")
                .excludePathPatterns("/student/")
                .excludePathPatterns("/student/loginstudent");
        registry.addInterceptor(new MasterFilter())
                .addPathPatterns("/housemaster/**")
                .excludePathPatterns("/housemaster/")
                .excludePathPatterns("/housemaster/loginadmin");
        registry.addInterceptor(new AdminFilter())
                .addPathPatterns("/administrator/**")
                .excludePathPatterns("/administrator/")
                .excludePathPatterns("/administrator/loginadmin");
    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }
//    public void addViewControllers(ViewControllerRegistry viewControllerRegistry) {
//        viewControllerRegistry.addViewController("/").setViewName("index");
//        //设置ViewController的优先级,将此处的优先级设为最高,当存在相同映射时依然优先执行
//        viewControllerRegistry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        super.addViewControllers(viewControllerRegistry);
//    }
}
