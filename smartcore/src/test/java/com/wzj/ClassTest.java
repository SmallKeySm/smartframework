package com.wzj;

import com.wzj.util.ClassUtil;
import org.junit.Test;

import java.util.Set;

/**
 * Created by jerry on 2017/8/31.
 */
public class ClassTest {

    @Test
    public void testLoadClass() {
        Set<Class<?>> classes = ClassUtil.getClassSet("com.wzj");
        if (classes != null && classes.size() > 0) {
            for (Class<?> aClass : classes) {
                System.out.println(aClass.getName());
            }
        }
    }
}
