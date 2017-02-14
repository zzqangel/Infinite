package com.infinite.filter;

import java.lang.reflect.Method;

/**
 * Created by ZZQ on 2017/2/14.
 */
public interface ServiceDecoder {
    /**
     * 返回从客户端接收后，在执行具体操作方法前调用的方法
     * 方法中对各参数的修改，会对
     * @param method 当前操作的调用方法的方法对象
     * @param objects 要调用方法的除userInfo以外的其他参数的参数数组，注意本方法中的objects数组的内容如果要执行修改，
     *                应该在修改后替换数组中的对象引用，由于参数包含基本类型，赋值给一般参数后，不会修改数组中的参数内容
     * @throws Exception 抛出自定义异常
     */
    public void outputMessage(Method method, Object[] objects) throws Exception;//TODO 异常
}
