package ir.vira.travelagency.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AppInterceptor implements HandlerInterceptor {
    
    private static final Logger logger = LoggerFactory.getLogger(AppInterceptor.class);
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        
        logger.warn(String.format("preHandle==> request uri: %s , handler: %s", request.getRequestURI(), handler.getClass().getName()));
        return true;
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.warn(String.format("afterCompletion==> request uri: %s , handler: %s", request.getRequestURI(), handler.getClass().getName()));
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        logger.warn(String.format("postHandle==> request uri: %s , handler: %s, view: %s", request.getRequestURI(), handler.getClass().getName(), modelAndView.getViewName()));
    }
    
}
