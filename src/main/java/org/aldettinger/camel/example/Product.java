package org.aldettinger.camel.example;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {

    @JsonProperty
    private String productId;

    @JsonProperty
    private String status;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
