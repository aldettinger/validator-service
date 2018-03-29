package org.aldettinger.camel.example;

import java.util.Arrays;
import java.util.List;

import org.apache.camel.Handler;

public class OrderValidator {

    private static final List<String> validProductIds = Arrays.asList(new String[] {"Water", "Wine", "Beer"});

    @Handler
    public void validate(Order order) {
        if ("aldettinger".equals(order.getCustomerId())) {
            order.setStatus("OK");
        } else {
            order.setStatus("The customer is not identified");
        }

        order.getProducts().stream().forEach(this::validate);
    }

    public void validate(Product product) {

        final String productId = product.getProductId();

        if (validProductIds.contains(productId)) {
            if (productId.length() <= 4) {
                product.setStatus("This product is out of stock");
            } else {
                product.setStatus("OK");
            }
        } else {
            product.setStatus("This product does not exist");
        }
    }
}
