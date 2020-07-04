package org.doraemon.framework.registry.service;

import org.doraemon.framework.Constants;
import org.doraemon.framework.exception.ApplicationException;

import javax.ws.rs.*;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2020-04-12 18:10
 */
@Path("/registries/query")
@Produces({Constants.ContentType.APPLICATION_JSON_UTF_8, Constants.ContentType.APPLICATION_XML_UTF_8})
@Consumes({Constants.ContentType.APPLICATION_JSON_UTF_8, Constants.ContentType.APPLICATION_XML_UTF_8})
public interface RegistryQueryService {

    /**
     * 根据应用ID和配置项Code获取配置信息
     *
     * @param appId
     * @param configCode
     * @return
     * @throws ApplicationException
     */
    @GET
    @Path("/{appId}/{configCode}")
    Object queryConfig(@PathParam("appId") String appId, @PathParam("configCode") String configCode) throws ApplicationException;
}
