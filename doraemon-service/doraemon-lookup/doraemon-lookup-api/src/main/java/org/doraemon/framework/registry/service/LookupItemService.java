package org.doraemon.framework.registry.service;

import org.doraemon.framework.Constants;
import org.doraemon.framework.exception.ApplicationException;
import org.doraemon.framework.registry.vo.LookupItemVO;

import javax.ws.rs.*;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2020-04-12 18:10
 */
@Path("/items")
@Produces({Constants.ContentType.APPLICATION_JSON_UTF_8, Constants.ContentType.APPLICATION_XML_UTF_8})
@Consumes({Constants.ContentType.APPLICATION_JSON_UTF_8, Constants.ContentType.APPLICATION_XML_UTF_8})
public interface LookupItemService extends LookupItemQueryService {

    @POST
    @Path("")
    LookupItemVO create(LookupItemVO lookupItemVO) throws ApplicationException;

    @PUT
    @Path("")
    void modify(LookupItemVO lookupItemVO) throws ApplicationException;

    @DELETE
    @Path("/{id}")
    void delete(@PathParam("id") String id) throws ApplicationException;
}
