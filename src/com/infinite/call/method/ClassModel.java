package com.oa8000.appservice.htentrance.method;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ZZQ on 2017/1/19.
 */
public class ClassModel {
    /**
     * key ��methodName
     * value �Ƿ�����Ӧ����������γɵķ�����������
     */
    private final ConcurrentHashMap<String, MethodListModel> classMap = new ConcurrentHashMap<String, MethodListModel>();

    MethodListModel getMethodListModel(String methodName) {
        MethodListModel methodListModel = classMap.get(methodName);
        if(methodListModel == null) {
            synchronized (classMap) {
                methodListModel = classMap.get(methodName);
                if(methodListModel == null) {
                    methodListModel = new MethodListModel();
                    classMap.put(methodName, methodListModel);
                }
            }
        }
        return methodListModel;
    }

}
