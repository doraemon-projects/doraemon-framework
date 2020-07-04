package org.doraemon;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.doraemon.framework.jaxrs.UnifiedExceptionMapperProvider;
import org.doraemon.framework.jaxrs.UnifiedMessageBodyWriterProvider;
import org.doraemon.framework.lookup.service.LookupClassifyService;
import org.doraemon.framework.lookup.service.LookupItemService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import java.util.Arrays;

/**
 * @description: 应用启动类
 * @author: fengwenping
 * @date: 2020-05-16 12:47
 */
@SpringBootApplication
@MapperScan("org.doraemon.**.dao")
public class DoraemonApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoraemonApplication.class, args);
    }
}
