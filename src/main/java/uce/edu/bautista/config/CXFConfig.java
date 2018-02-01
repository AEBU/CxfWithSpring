package uce.edu.bautista.config;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.endpoint.EndpointImpl;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.model.UserResource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import uce.edu.bautista.services.ExceptionResource;
import uce.edu.bautista.services.HolaService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.ext.RuntimeDelegate;
import java.util.Arrays;


/**
 * Created by Alexis on 31/01/2018.
 */
@Configuration
public class CXFConfig {

    @ApplicationPath("/")
    public class JaxRsApiApplication extends Application { }

    @Bean(destroyMethod = "shutdown")
    public SpringBus cxf() {
        return new SpringBus();
    }

    @Bean
    @DependsOn("cxf")
    public Server jaxRsServer(ApplicationContext appContext) {
        JAXRSServerFactoryBean factory = RuntimeDelegate.getInstance().createEndpoint(jaxRsApiApplication(), JAXRSServerFactoryBean.class);
        factory.setServiceBeans(Arrays.<Object>asList(userResource(), exceptionResource()));
        factory.setAddress("/rest" );
        factory.setServiceBean(HolaService.class);
        factory.setProvider(jsonProvider());
        return factory.create();
    }

    @Bean
    public JaxRsApiApplication jaxRsApiApplication() {
        return new JaxRsApiApplication();
    }

    @Bean
    public JacksonJsonProvider jsonProvider() {
        return new JacksonJsonProvider();
    }

    @Bean
    public UserResource userResource() {
        return new UserResource();
    }
    @Bean
    public ExceptionResource exceptionResource() {
        return new ExceptionResource();
    }
}
