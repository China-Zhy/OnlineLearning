package nxu.utils;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 实现一个Servlet处理多个请求的工具类 (张宏业)
 */
public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        String methodName = req.getParameter("method");
        System.out.println("【BaseServlet中收到的method = " + methodName + "】");
        try {
            Class<? extends BaseServlet> clazz = this.getClass();
            Method method = clazz.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, req, resp);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            System.out.println("【BaseServlet捕获到了异常】");
        }
    }
}