package com.ken.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

/**
 * @author yhq
 * @date 2018/12/24
 */
public class WebInitializer {//implements WebApplicationInitializer {

    //@Override
    //public void onStartup(ServletContext servletContext) throws ServletException {
    //    AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
    //    ctx.register(MvcConfiguration.class);
    //    ctx.register(DataSourceConfiguration.class);
    //    ctx.setServletContext(servletContext);
    //
    //    Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
    //    servlet.addMapping("/");
    //    servlet.setLoadOnStartup(1);
    //
    //    //CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
    //    //characterEncodingFilter.setEncoding("utf-8");
    //
    //}

}
