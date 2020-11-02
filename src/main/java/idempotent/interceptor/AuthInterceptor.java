package idempotent.interceptor;

import idempotent.annotation.Idempotent;
import idempotent.service.IdeMarkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * theMeng
 * 拦截器处理器，处理拦截幂等性注解 @Idempotent
 */
@Slf4j
public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private IdeMarkService ideMarkService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        //获取到方法
        Method method = handlerMethod.getMethod();
        //被@Idempotent注解标记的扫描
        Idempotent methodAnnotation = method.getAnnotation(Idempotent.class);
        if (methodAnnotation != null) {
            ideMarkService.checkIdeMark(request);// 幂等性校验, 校验通过则放行, 校验失败则抛出异常
        }
        return true;
    }

}
