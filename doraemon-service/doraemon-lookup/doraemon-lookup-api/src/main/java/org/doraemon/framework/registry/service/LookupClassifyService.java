package org.doraemon.framework.registry.service;

import org.doraemon.framework.Constants;
import org.doraemon.framework.exception.ApplicationException;
import org.doraemon.framework.registry.vo.LookupClassifyVO;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2020-04-12 16:17
 */
@Path("/classifies/query")
@Produces({Constants.ContentType.APPLICATION_JSON_UTF_8, Constants.ContentType.APPLICATION_XML_UTF_8})
@Consumes({Constants.ContentType.APPLICATION_JSON_UTF_8, Constants.ContentType.APPLICATION_XML_UTF_8})
public interface LookupClassifyService extends LookupClassifyQueryService {

    @POST
    @Path("")
    LookupClassifyVO create(LookupClassifyVO lookupClassify) throws ApplicationException;
}
