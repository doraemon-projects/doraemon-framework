package org.doraemon.framework.webmvc.service;

import org.doraemon.framework.core.enums.IDescriptionProvider;
import org.doraemon.framework.core.enums.IEnumProvider;
import org.doraemon.framework.core.exception.SystemException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2020-05-16 23:15
 */
public interface SecurityService {

    public static enum Authority implements IEnumProvider<Integer>, IDescriptionProvider {
        SESSION(10, "session", "session"),
        JWT(20, "jwt", "jwt");
        private Integer code;
        private String name;
        private String desc;

        Authority(Integer code, String name, String desc) {
            this.code = code;
            this.name = name;
            this.desc = desc;
        }


        @Override
        public Integer getCode() {
            return this.code;
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public String getDesc() {
            return this.desc;
        }
    }

    boolean doCheckPermission(Authority authority, HttpServletRequest request, HttpServletResponse response) throws SystemException;
}
