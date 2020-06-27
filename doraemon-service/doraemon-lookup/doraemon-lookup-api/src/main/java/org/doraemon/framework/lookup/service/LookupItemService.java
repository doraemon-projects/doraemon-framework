package org.doraemon.framework.lookup.service;

import org.doraemon.framework.Constants;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2020-04-12 18:10
 */
@Produces({Constants.ContentType.APPLICATION_JSON_UTF_8, Constants.ContentType.APPLICATION_XML_UTF_8})
@Consumes({Constants.ContentType.APPLICATION_JSON_UTF_8, Constants.ContentType.APPLICATION_XML_UTF_8})
public interface LookupItemService extends LookupItemQueryService {
}
