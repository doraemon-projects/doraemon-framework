package org.doraemon.framework.webmvc.response;

import org.doraemon.framework.base.BaseCode;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2020-05-16 22:30
 */
public class ResultCode extends BaseCode implements IHttpCodeProvider {
    public ResultCode(Integer code) {
        super(code);
    }

    public ResultCode(Integer code, String name) {
        super(code, name);
    }

    @Override
    public int getHttpCode() {
        return this.code / 100;
    }
}
