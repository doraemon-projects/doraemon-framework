package org.doraemon.application;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.jaxrs.xml.JacksonJaxbXMLProvider;
import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.doraemon.framework.jaxrs.UnifiedExceptionMapperProvider;
import org.doraemon.framework.jaxrs.UnifiedMessageBodyWriterProvider;
import org.doraemon.framework.lookup.service.LookupClassifyService;
import org.doraemon.framework.lookup.service.LookupItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2020-07-04 15:05
 */
@Configuration
public class JAXRSProviderConfiguration {

    @Autowired
    private Bus bus;
    @Autowired
    private JacksonJaxbJsonProvider unifiedJacksonJsonProvider;
    @Autowired
    private JacksonJaxbXMLProvider unifiedJacksonXMLProvider;
    @Autowired
    private UnifiedExceptionMapperProvider unifiedExceptionMapperProvider;
    @Autowired
    private UnifiedMessageBodyWriterProvider unifiedMessageBodyWriterProvider;

    @Bean
    public Server lookupServer(LookupClassifyService lookupClassifyService,
                               LookupItemService lookupItemService) {
        return this.createJAXRSServer("/lookup", lookupClassifyService, lookupItemService).create();
    }

    public JAXRSServerFactoryBean createJAXRSServer(String address, Object... args) {
        JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
        endpoint.setBus(bus);
        endpoint.setServiceBeans(Arrays.asList(args));
        endpoint.setAddress(address);
        endpoint.setProviders(Arrays.asList(unifiedJacksonJsonProvider,
                unifiedJacksonXMLProvider,
                unifiedExceptionMapperProvider,
                unifiedMessageBodyWriterProvider));
        endpoint.setFeatures(Arrays.asList(new LoggingFeature()));
        return endpoint;
    }
}
