package com.oa8000.appservice.htentrance.method;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZZQ on 2017/2/7.
 */
public class MethodInfo {
    private int hashCode;
    public MethodInfo(Class[] classes) {
        StringBuilder sb = new StringBuilder();
        if(classes != null && classes.length > 0) {
            for(int i = 0; i < classes.length; i ++) {
                String name = classes[i].getName();
                sb.append(name);
            }
        }
        hashCode = sb.toString().hashCode();
    }

    @Override
    public int hashCode() {
        return hashCode;
    }
}
