package com.oa8000.appservice.htentrance.method;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ZZQ on 2017/1/19.
 */
public class ClassCallList {
    /**
     * key 是class名称
     * value是class下面的方法集合
     */
    private static final ConcurrentHashMap<String, ClassModel> callList = new ConcurrentHashMap<String, ClassModel>();

    public static Method getMethod(String className, String methodName, Class[] classes) throws ClassNotFoundException, NoSuchMethodException {
        ClassModel classModel = callList.get(className);
        if(classModel == null) {
            synchronized (callList) {
                classModel = callList.get(className);
                if(classModel == null) {
                    classModel = new ClassModel();
                    callList.put(className, classModel);
                }
            }
        }
        MethodListModel methodListModel = classModel.getMethodListModel(methodName);
        return methodListModel.getMethod(classes, className, methodName);
    }
}
