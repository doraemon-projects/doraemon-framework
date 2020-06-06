package org.doraemon.framework.jaxrs;

import org.doraemon.framework.Constants;
import org.doraemon.framework.response.Result;
import org.doraemon.framework.util.JSON;

import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * @description: jax-rs统一的响应格式处理提供者
 * @author: fengwenping
 * @date: 2020-06-06 17:56
 */
@Named("unifiedMessageBodyWriterProvider")
@Provider
@Produces(Constants.ContentType.APPLICATION_JSON_UTF_8)
@Consumes(Constants.ContentType.APPLICATION_JSON_UTF_8)
public class UnifiedMessageBodyWriterProvider implements MessageBodyWriter<Object> {
    @Override
    public boolean isWriteable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return !aClass.isAssignableFrom(Result.class);
    }

    @Override
    public void writeTo(Object o, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> multivaluedMap, OutputStream outputStream) throws IOException, WebApplicationException {
        outputStream.write(JSON.toJSONString(Result.success(o)).getBytes(Constants.CharsetConfig.utf8Charset()));
    }
}
