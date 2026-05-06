package com.vin.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.vin.soap.user.CreateUserRequest;
import com.vin.soap.user.CreateUserResponseType;
import com.vin.soap.user.UserService;
import com.vin.soap.user.UserSoapServiceImplService;

import jakarta.xml.ws.BindingProvider;

@Component
public class UserRoute extends RouteBuilder {

    @Value("${soap.backend.url}")
    private String backendUrl;

    @Override
    public void configure() {

        from("cxf:/user-service-new?serviceClass=com.vin.soap.user.UserService")
            .routeId("xml-to-xml-route")

            .log("Incoming Request: ${body}")

            .process(exchange -> {

                CreateUserRequest request =
                        exchange.getIn().getBody(CreateUserRequest.class);

                // Create client
                UserSoapServiceImplService service =
                        new UserSoapServiceImplService();

                UserService port =
                        service.getUserSoapServiceImplPort();

                // ✅ Override endpoint from config
                ((BindingProvider) port)
                        .getRequestContext()
                        .put(
                            BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                            backendUrl
                        );

                CreateUserResponseType response =
                        port.createUser(request);

                exchange.setProperty("serviceResponse", response);
                exchange.getMessage().setBody(response);
            })

            .log("Outgoing Response: ${body}");
    }
}