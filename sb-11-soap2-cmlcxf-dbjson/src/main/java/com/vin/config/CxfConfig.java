package com.vin.config;

import org.apache.cxf.Bus;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.jaxws.EndpointImpl;   // ✅ CORRECT
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vin.soap.UserSoapServiceImpl;

import jakarta.xml.ws.Endpoint;

@Configuration
public class CxfConfig {

    @Bean
    public Endpoint userEndpoint(Bus bus, UserSoapServiceImpl service) {

        EndpointImpl endpoint = new EndpointImpl(bus, service);

        endpoint.publish("/v1/user-service");

        endpoint.getFeatures().add(new LoggingFeature());

        return endpoint;
    }
}