package com.infinite.filter;

import java.lang.reflect.Method;

/**
 * Created by ZZQ on 2017/2/14.
 */
public interface ServiceEncoder {
    /**
     * ���شӿͻ��˽��պ���ִ�о����������ǰ���õķ���
     * �����жԸ��������޸ģ����
     * @param method ��ǰ�����ĵ��÷����ķ�������
     * @param objects Ҫ���÷����ĳ�userInfo��������������Ĳ������飬ע�Ȿ�����е�objects������������Ҫִ���޸ģ�
     *                Ӧ�����޸ĺ��滻�����еĶ������ã����ڲ��������������ͣ���ֵ��һ������󣬲����޸������еĲ�������
     * @param method ��ǰ�����ĵ��÷����ķ�������
     * @throws Exception �׳��Զ����쳣
     */
    public void outputMessage(Method method, Object[] objects, Object retObject) throws Exception;//TODO �쳣
}
