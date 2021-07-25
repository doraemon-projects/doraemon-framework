package org.doraemon.framework.jaxrs;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.doraemon.framework.core.Constants;

import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.ext.Provider;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2020-07-04 17:38
 */
@Named("unifiedJacksonXMLProvider")
@Provider
@Produces(Constants.ContentType.ALL_VALUE)
@Consumes(Constants.ContentType.ALL_VALUE)
public class UnifiedJacksonJaxbJsonProvider extends JacksonJaxbJsonProvider {
}
