package kr.co.demo;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.ErrorPageFilter;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SpringBootApplication
@ComponentScan({"kr.co"})
@EnableRedisHttpSession
@EnableAutoConfiguration
public class DemoApplication extends SpringBootServletInitializer {

    /**
     * Spring Framework는 Servlet 3.0 이상 환경에서 web.xml 대신하여 ServletContext를 프로그래밍적으로 다룰 수 있게 WebApplicationInitializer 인터페이스를 제공
     * */
    @Override
    public void onStartup(final ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DemoApplication.class);
    }

    /**
     *  errorPageFilter 미설정시 기본 spring boot 제공 whitelabel Error page 이동
     *  spring boot 2.1 부터 bean overriding 불가능 ErrorPageFilter exception 발생
     *  application.yml 추가
     *  --------------------------------------------------------------------------
     *  spring:
     *    main:
     *      allow-bean-definition-overriding: true
     *  --------------------------------------------------------------------------
     * */
    @Bean
    public ErrorPageFilter errorPageFilter() {
        return new ErrorPageFilter();
    }

    @Bean
    public FilterRegistrationBean disableSpringBootErrorFilter(ErrorPageFilter filter) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(filter);
        filterRegistrationBean.setEnabled(false);
        return filterRegistrationBean;
    }

}
