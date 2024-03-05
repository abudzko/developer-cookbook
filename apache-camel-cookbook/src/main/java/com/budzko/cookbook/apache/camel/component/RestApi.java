package com.budzko.cookbook.apache.camel.component;

import com.budzko.cookbook.apache.camel.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.support.DefaultMessage;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
public class RestApi extends RouteBuilder {
    private static void buildResponseMessage(Exchange exchange) {
        log.info("Got exchange {}", extractMessageBody(exchange));
        var message = new DefaultMessage(exchange.getContext());
        var user = new User();
        user.setName(UUID.randomUUID().toString());
        message.setBody(user);
        exchange.setMessage(message);
    }

    private static String extractMessageBody(Exchange exchange) {
        return Optional.ofNullable(exchange)
                .map(Exchange::getMessage)
                .map(message -> message.getBody(String.class))
                .filter(StringUtils::hasLength)
                .orElse("Empty");
    }

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.json);

        rest("/user").description("User REST service")
                .consumes(MediaType.APPLICATION_JSON_VALUE)
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .get().description("Find user").outType(User.class)
                .to("direct:user");

        from("direct:user")
                .process(RestApi::buildResponseMessage);
    }
}
