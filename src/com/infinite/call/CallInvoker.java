package com.oa8000.appservice.htentrance;

import com.oa8000.appservice.comm.filter.HtServiceCodeCreator;
import com.oa8000.appservice.comm.filter.HtServiceDecode;
import com.oa8000.appservice.comm.filter.HtServiceEncode;
import com.oa8000.appservice.htentrance.method.ClassCallList;
import com.oa8000.proxy.comm.HiUserInfo;
import com.oa8000.proxy.exception.OaException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by ZZQ on 2017/1/19.
 */
public class CallInvoker<T> {
    private final Method method;
    private final Object[] objects;
    private final Object target;
    private final String targetClassSimpleName;
    public CallInvoker(String className, String methodName, Class[] classes, Object[] objects) throws
            ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        target = Class.forName(className).newInstance();
        this.objects = objects;
        this.method = ClassCallList.getMethod(className, methodName, classes);
        targetClassSimpleName = getClassSimpleName(className);
    }
    private String getClassSimpleName(String className) {
        String[] classNames = className.split("\\.");
        return classNames[classNames.length - 1];
    }
    public CallInvoker(Object target, String methodName, Class[] classes, Object[] objects) throws
            ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        this.target = target;
        this.objects = objects;
        String className = target.getClass().getName();
        this.method = ClassCallList.getMethod(className, methodName, classes);
        this.targetClassSimpleName = this.getClassSimpleName(className);
    }

    public Method method() {
        return this.method;
    }

    public T call() throws InvocationTargetException, IllegalAccessException, OaException {
        return invokeMethod();
    }

    private Object[] newOs = null;

    String serviceCall() throws InvocationTargetException, IllegalAccessException, OaException, ClassNotFoundException, InstantiationException {
        HtServiceCodeCreator serviceCodeCreator = new HtServiceCodeCreator(targetClassSimpleName);
        List<Class> encodeList = serviceCodeCreator.getEncodeClassList();
        List<Class> decodeList = serviceCodeCreator.getDecodeClassList();
        if(decodeList != null) {
            for(Class c : decodeList) {
                HtServiceDecode decode = (HtServiceDecode) c.newInstance();
                decode.outputMessage(this.method, (HiUserInfo) objects[0], this.initParam());
            }
        }
        String retStr = (String) invokeMethod();
        if(encodeList != null) {
            for(Class c : encodeList) {
                HtServiceEncode encode = (HtServiceEncode) c.newInstance();
                encode.inputMessage(this.method, (HiUserInfo) objects[0], this.initParam(), retStr);
            }
        }
        return retStr;
    }
    private Object[] initParam() {
        if(newOs != null) return newOs;
        newOs = new Object[objects.length - 1];
        System.arraycopy(objects, 1, newOs, 0, newOs.length);
        return newOs;
    }

    private T invokeMethod() throws InvocationTargetException, IllegalAccessException {
        return (T) method.invoke(target, objects);
    }
}
