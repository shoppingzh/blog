/**
 * 
 */
package com.xpzheng.blog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author xpzheng
 *
 */
@Component
public class CorsInterceptor implements HandlerInterceptor {

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.
     * http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
     * java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        response.addHeader("Access-Control-Allow-Origin", "*");
//      response.addHeader("Access-Control-Allow-Methods", "POST,PUT,DELETE,PATCH");
        response.addHeader("Access-Control-Allow-Methods", "*");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type,Access-Token,x-token");
        // 放行OPTIONS请求
        HttpServletRequest req = (HttpServletRequest) request;
        if (HttpMethod.OPTIONS.toString().equalsIgnoreCase(req.getMethod())) {
            response.setStatus(HttpStatus.NO_CONTENT.value());
            return false;
        }
        return true;
    }

}
