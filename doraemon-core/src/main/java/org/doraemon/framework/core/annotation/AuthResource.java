package org.doraemon.framework.core.annotation;

import java.lang.annotation.*;

/**
 * @description: 资源描述注解
 * @author: fengwenping
 * @date: 2019/1/11 11:44
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthResource {

    /**
     * 资源码
     *
     * @return
     */
    String code();

    /**
     * 资源描述
     *
     * @return
     */
    String desc();
}
