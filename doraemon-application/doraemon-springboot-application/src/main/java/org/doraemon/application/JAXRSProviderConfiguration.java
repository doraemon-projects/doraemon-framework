package org.doraemon.application;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.jaxrs.xml.JacksonJaxbXMLProvider;
import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.ext.logging.slf4j.Slf4jVerboseEventSender;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.swagger.Swagger2Feature;
import org.doraemon.framework.jaxrs.UnifiedExceptionMapperProvider;
import org.doraemon.framework.jaxrs.UnifiedMessageBodyWriterProvider;
import org.doraemon.framework.registry.service.LookupClassifyService;
import org.doraemon.framework.registry.service.LookupItemService;
import org.doraemon.framework.registry.service.RegistryQueryService;
import org.slf4j.event.Level;
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

    @Bean
    public Server registryServer(RegistryQueryService registryQueryService) {
        return this.createJAXRSServer("/registry", registryQueryService).create();
    }

    public Swagger2Feature swagger2Feature() {
        Swagger2Feature swagger2Feature = new Swagger2Feature();
        swagger2Feature.setBasePath("/app/swagger-ui");
        return swagger2Feature;
    }

    public LoggingFeature loggingFeature() {
        Slf4jVerboseEventSender eventSender = new Slf4jVerboseEventSender();
        eventSender.setLoggingLevel(Level.DEBUG);
        LoggingFeature loggingFeature = new LoggingFeature();
        loggingFeature.setSender(eventSender);
        return loggingFeature;
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
        endpoint.setFeatures(Arrays.asList(swagger2Feature(), loggingFeature()));
        return endpoint;
    }
}
