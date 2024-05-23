package com.cc.weblogger.controller;


import com.cc.weblogger.annotation.Log;
import com.cc.weblogger.pojo.SysLog;
import com.cc.weblogger.service.SysLogService;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Author: CC
 * E-mail: 203717588@qq.com
 * Date: 2023/2/27
 * Time: 19:24
 * Description:
 */
// 定义切面和切点
@Aspect
@Component
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // 切点方式
    // 定义切点，设置拦截规则
    // 切点表达式(AspectJ)常用函数：execution(<修饰符><返回类型><包.类.⽅法(参数)><异常>)
    // 切点表达式(AspectJ)通配符：*任意，只匹配一个元素（包、类、⽅法、⽅法参数）
    //                        ..任意，可以匹配多个元素
    //              *任意返回类型          拦截包名               类名       *所有方法 (..)所有参数
    @Pointcut("execution(* com.cc.weblogger.controller.UserController.getData(..))")
    public void pointcut(){
    }

    // 定义切点的前置通知
    @Before("pointcut()")
    public void doBefore(JoinPoint jp){

    }

    // 定义切点的后置通知
    @After("pointcut()")
    public void doAfter(JoinPoint jp){

    }

    // 环绕通知实现日志记录
    // 定义切点的环绕通知（方法执行前后都执行的语句）
    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        // 1、前置通知
        // 访问时间
        String dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        // 获取访问的类
        String className = pjp.getSignature().getDeclaringTypeName();
        // 获取访问的方法
        String methodName = pjp.getSignature().getName();
        // 2、执行切入点方法
        Object res = pjp.proceed(pjp.getArgs());// pjp.proceed(): 调用切入点方法
        // 3、后置通知
        // 注解方式：是否标注日志注解：getTarget(): 获取目标类
//        Log logAnn = pjp.getTarget().getClass().getAnnotation(Log.class);
//        if (logAnn != null) {
            // 获取客户端IP
            InetAddress ia = InetAddress.getLocalHost();
            //String ip = request.getRemoteAddr();   // 反向代理的IP(非请求者IP)
            String addressIp = ia.getHostAddress();
            // 获取客户端主机
            //String host = request.getRemoteHost(); // 反向代理的主机(非请求者主机)
            String hostName = ia.getHostName();
            // 获取请求方式
            String reqMethod = request.getMethod();
            // 获取请求协议
            String protocol = request.getProtocol();
            // 获取请求url
            String url = request.getRequestURL().toString();
            // 获取请求体内容类型
            String contentType = request.getContentType();
            // 获取响应状态码
            String statusCode = String.valueOf(response.getStatus());
            // 保存日志
            Boolean b = sysLogService.addLog(new SysLog(null, dt, className, methodName, addressIp, hostName, reqMethod, protocol, url, contentType, statusCode));
            System.out.println(b);
//        }
        return res;
    }

    // 定义切点抛出异常后的通知
    @AfterThrowing("pointcut()")
    public void doAfterThrowing(){

    }

    // 定义切点方法返回结果后的通知
    @AfterReturning("pointcut()")
    public void doAfterReturning(){

    }

}
