package com.oa8000.appservice.htentrance.method;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ZZQ on 2017/1/19.
 */
public class MethodListModel {
    private final ConcurrentHashMap<MethodInfo, Method> methodMap = new ConcurrentHashMap<MethodInfo, Method>();
    Method getMethod(Class[] classes, String className, String methodName) throws ClassNotFoundException, NoSuchMethodException {
        MethodInfo methodInfo = new MethodInfo(classes);
        Method method = methodMap.get(methodInfo);
        if(method == null) {
            synchronized (methodMap) {
                method = methodMap.get(methodInfo);
                if(method == null) {
                    Class c = Class.forName(className);
                    try {
                        method = c.getDeclaredMethod(methodName, classes);
                    } catch (NoSuchMethodException e) {
                        method = c.getMethod(methodName, classes);
                    }
                    if(!method.isAccessible()) {
                        method.setAccessible(true);
                    }
                    this.setMethod(methodInfo, method);
                }
            }
        }
        return method;
    }

    private void setMethod(MethodInfo methodInfo, Method method) {
        this.methodMap.put(methodInfo, method);
    }
}
