package org.doraemon.framework.jaxrs;

import org.doraemon.framework.Constants;
import org.doraemon.framework.exception.ApplicationException;
import org.doraemon.framework.exception.ApplicationRuntimeException;
import org.doraemon.framework.exception.BusinessException;
import org.doraemon.framework.response.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Locale;
import java.util.Objects;

/**
 * @description: jax-rs统一的异常处理提供者
 * @author: fengwenping
 * @date: 2020-06-06 17:42
 */
@Named("unifiedExceptionMapperProvider")
@Provider
@Produces(Constants.ContentType.ALL_VALUE)
@Consumes(Constants.ContentType.ALL_VALUE)
public class UnifiedExceptionMapperProvider implements ExceptionMapper<Exception> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UnifiedExceptionMapperProvider.class);

    @Override
    public Response toResponse(Exception e) {
        LOGGER.error("unified exception handle", e);
        Result<String> result;
        if (e instanceof ApplicationException) {
            ApplicationException exception = (ApplicationException) e;
            result = new Result.Builder<String>()
                    .code(exception.getErrorCode())
                    .message(exception.getMessage())
                    .build();
        } else if (e instanceof ApplicationRuntimeException) {
            ApplicationRuntimeException exception = (ApplicationRuntimeException) e;
            result = new Result.Builder<String>()
                    .code(exception.getErrorCode())
                    .message(exception.getMessage())
                    .build();
        } else {
            result = new Result.Builder<String>()
                    .code(Constants.ResultCode.EXP_FAILURE.getErrorCode())
                    .message(this.getErrorMessage(e))
                    .build();
        }
        Response.ResponseBuilder builder = Response.status(Response.Status.OK);
        builder.type(Constants.ContentType.APPLICATION_JSON_UTF_8);
        builder.entity(result);
        builder.language(Locale.SIMPLIFIED_CHINESE);
        return builder.build();
    }

    private String getErrorMessage(Exception exception) {
        boolean showMessage = Objects.equals(Boolean.TRUE.toString(),
                System.getProperty("doraemon.show.exception.message", Boolean.FALSE.toString()));
        if (showMessage) {
            return this.getStackTraceMessage(exception);
        }
        return Constants.ResultCode.EXP_FAILURE.getMessage();
    }


    private String getStackTraceMessage(Exception exception) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        exception.printStackTrace(printWriter);
        return stringWriter.toString();
    }
}
