package com.vin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
public class WsdlConfig {

    @Bean(name = "user-service")   // IMPORTANT → matches URL
    public DefaultWsdl11Definition wsdl11Definition(XsdSchema userSchema) {

        DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();

        wsdl.setPortTypeName("UserPort");

        // must match servlet mapping
        wsdl.setLocationUri("/user-service.svc");

        wsdl.setTargetNamespace("http://vin.com/user");
        wsdl.setSchema(userSchema);

        return wsdl;
    }
}