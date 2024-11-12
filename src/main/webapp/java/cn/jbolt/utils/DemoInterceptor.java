package cn.jbolt.utils;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * <p> TODO</p >
 *
 * @author gong
 * @version V1.8.0
 * @date 2024/11/5 15:18
 */
public class DemoInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation invocation) {
        System.out.println("Before method invoking");
        invocation.invoke();
        System.out.println("After method invoking");
    }
}
