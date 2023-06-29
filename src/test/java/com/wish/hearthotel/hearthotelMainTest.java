package com.wish.hearthotel;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;


@SpringBootTest
public class hearthotelMainTest {
    @Test
    public void contextLoads() {
        //1.全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setActiveRecord(false) //是否支持AR模式
                .setAuthor("yangazhang") //作者
                // 生成路径
                .setOutputDir(System.getProperty("user.dir") + "/src/main/java")
                .setFileOverride(true) //是否进行文件覆盖
                .setIdType(IdType.AUTO) //设置主键策略
                //设置生成的service接口的名字的首字母是否有I，默认会是IUserService
                .setServiceName("%sService")
                .setBaseColumnList(true)
                .setBaseColumnList(true);
        //2.数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/hotel?serverTimezone=UTC");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        //3.策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setInclude("student"); //需要根据哪张表生成代码
        //4.包名策略配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.wish.hearthotel")
                .setMapper("mapper") //mapper生成的路径
                .setService("service")
                .setEntity("entities")
                .setController("controller")
                .setXml("mapper"); //xml映射文件
        //5.整合配置
        AutoGenerator mpg = new AutoGenerator();
        mpg.setGlobalConfig(globalConfig)
                .setDataSource(dsc)
                .setStrategy(strategy)
                .setPackageInfo(pc);
        //6.执行
        mpg.execute();
    }

}