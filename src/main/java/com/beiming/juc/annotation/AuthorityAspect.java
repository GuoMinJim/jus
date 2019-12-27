/**
 * AuthorityAspect.java 2019/12/18 15:07 Copyright ©2019 www.bmsoft.com.cn All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.beiming.juc.annotation;

import com.alibaba.fastjson.JSONObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.lang.model.element.UnknownAnnotationValueException;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

//import com.bmsoft.evidence.common.AppException;
//import com.bmsoft.evidence.common.ErrorCode;
//import com.bmsoft.evidence.utils.ClientHelp;
//import com.bmsoft.evidence.utils.HttpClientResult;

/**
 * File：AuthorityAspect.java<br> Title: <br> Description: <br> Company: www.bmsoft.com.cn <br>
 *
 * @author heyouchi
 */
@Aspect
@Component
public class AuthorityAspect {

  @Value("${authority.open}")
  private Boolean open;

  @Value("${authority.authorityUrl}")
  private String authorityUrl;


  @Pointcut("@annotation(com.bmsoft.evidence.aop.AuthorityCheck)")
  public void annotationPoinCut() {
  }


  @After("annotationPoinCut()")
  public void after(JoinPoint joinPoint) {
  }

  @Before("annotationPoinCut()")
  public void before(JoinPoint joinPoint) {
    if (!open) {
      return;
    }
    RequestAttributes ra = RequestContextHolder.getRequestAttributes();
    ServletRequestAttributes sra = (ServletRequestAttributes) ra;
    HttpServletRequest request = sra.getRequest();
    String token = request.getHeader("Authorization");
    if (token == null) {
//      throw new AppException(ErrorCode.GET_DATA_FAILED, "请求头Authorization为空！");
    }
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Object[] args = joinPoint.getArgs();
    String[] argNames = signature.getParameterNames();
    Method method = signature.getMethod();
    AuthorityCheck action = method.getAnnotation(AuthorityCheck.class);
    String value = action.value();
    List<String> paths = Arrays.asList(value.split("\\."));
    if (paths.size() == 0) {
      throw new UnknownAnnotationValueException(null, action.value());
    }
    String firstName = paths.get(0);
    String caseId = "";
    for (int i = 0; i < argNames.length; i++) {
      String argName = argNames[i];
      Object arg = args[i];
      Class<?> aClass = arg.getClass();
      if (argName.equals(firstName)) {
        if (paths.size() > 1) {
          caseId = takeCaseId(aClass, arg, paths, 1);
        } else if (arg instanceof String) {
          caseId = (String) arg;
        } else {
          throw new RuntimeException("caseId字段类型错误");
        }
      }
    }
    token = token.replace("bearer ", "");
    JSONObject result;
    String url = String.format("%s", authorityUrl);
    Map<String, Object> checkPost = new HashMap<>();
    checkPost.put("ajbs", caseId);
    checkPost.put("accessToken", token);
//    try {
//      HttpClientResult httpClientResult = ClientHelp.doPost(url, checkPost);
//      if (httpClientResult.getCode() != 200) {
//        throw new AppException(ErrorCode.GET_DATA_FAILED, "权限接口调用出错");
//      }
//      String content = httpClientResult.getContent();
//      result = JSON.parseObject(content);
//    } catch (Exception e) {
//      throw new AppException(ErrorCode.GET_DATA_FAILED, "获取权限失败");
//    }
//    if (result.getInteger("code") != 1000) {
//      throw new AppException(ErrorCode.GET_DATA_FAILED, "权限接口调用出错");
//    }
//    if (!result.getBoolean("data")) {
//      throw new AppException(ErrorCode.GET_DATA_FAILED, "您不是承办人，无操作权限！");
//    }
  }

  private String takeCaseId(Class<?> clazz, Object object, List<String> paths, Integer pathNum) {
    if (paths.get(pathNum) == null) {
      return null;
    }
    String s = paths.get(pathNum);
    try {
      Field field = clazz.getDeclaredField(s);
      field.setAccessible(true);
      Object o = field.get(object);
      if (paths.size() > pathNum + 1) {
        return takeCaseId(o.getClass(), o, paths, pathNum + 1);
      } else if (o instanceof String) {
        String caseId = (String) o;
        return caseId;
      } else {
        throw new RuntimeException("caseId字段类型错误");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }
}
