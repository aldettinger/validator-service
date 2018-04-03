package org.aldettinger.camel.example;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Order {

    @JsonProperty
    private String customerId;

    @JsonProperty
    private List<Product> products;

    @JsonProperty
    private String status;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
