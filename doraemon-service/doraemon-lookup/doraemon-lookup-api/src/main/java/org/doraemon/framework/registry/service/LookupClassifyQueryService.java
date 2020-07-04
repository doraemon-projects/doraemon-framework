package org.doraemon.framework.registry.service;

import org.doraemon.framework.Constants;
import org.doraemon.framework.exception.ApplicationException;
import org.doraemon.framework.registry.vo.LookupClassifyVO;

import javax.ws.rs.*;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2020-04-12 16:17
 */
@Path("/classifies")
@Produces({Constants.ContentType.APPLICATION_JSON_UTF_8, Constants.ContentType.APPLICATION_XML_UTF_8})
@Consumes({Constants.ContentType.APPLICATION_JSON_UTF_8, Constants.ContentType.APPLICATION_XML_UTF_8})
public interface LookupClassifyQueryService {

    /**
     * 根据主键查询快码信息
     *
     * @param id
     * @return
     * @throws ApplicationException
     */
    @GET
    @Path("/id/{id}")
    LookupClassifyVO findById(@PathParam("id") String id) throws ApplicationException;

    /**
     * 根据业务code查询快码信息
     *
     * @param classifyCode
     * @return
     * @throws ApplicationException
     */
    @GET
    @Path("/code/{classifyCode}")
    LookupClassifyVO findByCode(@PathParam("classifyCode") String classifyCode) throws ApplicationException;
}
