package org.doraemon.framework.exception;

/**
 * Created with IntelliJ IDEA.
 * Description: 描述
 * Author:      fengwenping
 * Date:        2019/12/29 16:29
 */
public interface IExceptionCodeProvider {

    /**
     * 异常编码
     *
     * @return
     */
    String getErrorCode();

    /**
     * 异常描述
     *
     * @return
     */
    String getMessage();
}
