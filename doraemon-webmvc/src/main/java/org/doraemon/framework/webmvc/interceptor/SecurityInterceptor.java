package org.doraemon.framework.webmvc.interceptor;

import org.doraemon.framework.Constants;
import org.doraemon.framework.webmvc.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2020-05-16 23:03
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private SecurityService securityService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String header = request.getHeader(Constants.WebConstants.X_DORAEMON_TOKEN);
        if (Objects.nonNull(header)) {
            securityService.doCheckPermission(SecurityService.Authority.JWT, request, response);
        } else if (Objects.nonNull(WebUtils.getCookie(request, Constants.WebConstants.X_DORAEMON_SESSION))) {
            return securityService.doCheckPermission(SecurityService.Authority.SESSION, request, response);
        }
        return securityService.doCheckPermission(SecurityService.Authority.SESSION, request, response);
    }
}
