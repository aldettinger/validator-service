package org.aldettinger.camel.example;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.stereotype.Component;

@Component
public class OrderValidatorRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        JacksonDataFormat format = new JacksonDataFormat(Order.class);
        format.setPrettyPrint(true);

        rest("/order").post("validation").to("direct:rest-in").outType(String.class);

        from("direct:rest-in").to("direct:in").marshal(format);
        from("direct:in").unmarshal(format).bean(new OrderValidator());
    }

}
