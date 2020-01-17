package com.xpzheng.blog;

import com.xpzheng.blog.filter.RequestFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan(basePackages = {"com.xpzheng.blog.mapper"})
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean filterConf(){
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new RequestFilter());
		registration.addUrlPatterns("/*");
		return registration;
	}

}
