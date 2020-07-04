package org.doraemon.framework.annotation;

import java.lang.annotation.*;

/**
 * @description: 资源操作描述
 * @author: fengwenping
 * @date: 2019/1/11 11:48
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthOperation {

    /**
     * 权限码
     *
     * @return
     */
    String code();

    /**
     * 权限描述
     *
     * @return
     */
    String desc();
}
