package com.wei.filter;

import com.wei.service.ITUserService;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Order(1)
//@WebFilter(filterName = "xFilter1", urlPatterns = "/*")
@WebFilter(filterName = "xFilter1", urlPatterns = "/*")
@Logger
public class XFilter implements Filter {



@Override
public void doFilter(ServletRequest req, ServletResponse out, FilterChain chain)
 throws IOException, ServletException {

 HttpServletRequest r = (HttpServletRequest) req;
 System.out.println("---#######---xFilter-----------");
 System.out.println("---#######---xFilter------"
                     + "\n-#### getRequestURI:" + r.getRequestURI()
                     + "\n-#### getContextPath:" + r.getContextPath()
 );

 chain.doFilter(req, out);
}
}
