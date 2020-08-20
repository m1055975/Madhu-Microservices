package com.mindtree.zullproxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import com.mindtree.zullproxy.filters.ErrorFilter;
import com.mindtree.zullproxy.filters.PostFilter;
import com.mindtree.zullproxy.filters.PreFilter;
import com.mindtree.zullproxy.filters.RouteFilter;

@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class ZuulProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulProxyApplication.class, args);
	}
	
	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}
	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}
	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}
	@Bean
	public RouteFilter routeFilter() {
		return new RouteFilter();
	}
	

}
