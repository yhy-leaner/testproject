package com.sun;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @Configuration：标注此文件为一个配置项
 * @EnableAutoConfiguration：使用自动配置
 * @ComponentScan：可扫描的
 * @SpringBootApplication 包含上面三个
 * @ServletComponentScan 设置启动时spring能够扫描到我们自己编写的servlet和filter, 用于Druid监控
 * @EnableConfigurationProperties 启动时加载配置的类, 该类用于加载配置文件, 会把该类的对象实例化成一个bean,
 * 如果该类再加了实例化成bean的注解,就会出现两个相同的bean,会报错.实例化的bean可以在项目中通过依赖注入使用
 * @MapperScan 扫描的mapper接口
 * application：启动管理器
 * Created by sun on 2017-1-14.
 */
@SpringBootApplication
@ServletComponentScan
//@EnableConfigurationProperties({YmlConfig.class})//不需要了.因为YmlConfig类上有@Component注解
@MapperScan("com.sun.**.mapper") //配置扫描mapper接口的地址
public class Application extends SpringBootServletInitializer implements CommandLineRunner{

    private final Logger logger = Logger.getLogger(Application.class);

    /* Servlet容器默认的Context路径是/
    DispatherServlet匹配的路径(servlet-mapping中的url-patterns)是*//*
    @ComponentScan路径被默认设置为SampleController的同名package，
    也就是该package下的所有@Controller，@Service, @Component, @Repository都会被实例化后并加入Spring Context中。*/
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.run(args);
    }

    // Java EE应用服务器配置，
    // 如果要使用tomcat来加载jsp的话就必须继承SpringBootServletInitializer类并且重写其中configure方法
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }

    // springboot运行后此方法首先被调用
    // 实现CommandLineRunner抽象类中的run方法
    @Override
    public void run(String... args) throws Exception {
        logger.info("项目启动完成！");
    }
}