package org.aldettinger.camel.example;

import java.io.File;
import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.CamelExecutionException;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.DisableJmx;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@RunWith(CamelSpringBootRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@DisableJmx(true)
public class OrderValidatorRouteBuilderTest extends CamelTestSupport {

    @Autowired
    private CamelContext camelContext;

    @Override
    protected CamelContext createCamelContext() throws Exception {
        return camelContext;
    }

    private static File toTestFile(String name) {
        return new File("src/test/resources/json-orders/", name);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void okOrderShouldSucceed() {
        Order response = template.requestBody("direct:in", toTestFile("ok.json"), Order.class);

        Assert.assertNotNull(response);
        Assert.assertEquals("aldettinger", response.getCustomerId());
        Assert.assertEquals("OK", response.getStatus());
        List<Product> products = response.getProducts();
        Assert.assertNotNull(products);
        Assert.assertEquals(1, products.size());
        Product first = products.get(0);
        Assert.assertNotNull(first);
        Assert.assertEquals("Water", first.getProductId());
        Assert.assertEquals("OK", first.getStatus());
    }

    @Test
    public void outOfStockShouldSetErrorStatus() {
        Order response = template.requestBody("direct:in", toTestFile("out-of-stock.json"), Order.class);

        Assert.assertNotNull(response);
        Assert.assertEquals("aldettinger", response.getCustomerId());
        Assert.assertEquals("OK", response.getStatus());
        List<Product> products = response.getProducts();
        Assert.assertNotNull(products);
        Assert.assertEquals(2, products.size());
        Product first = products.get(0);
        Assert.assertNotNull(first);
        Assert.assertEquals("Wine", first.getProductId());
        Assert.assertEquals("This product is out of stock", first.getStatus());
        Product second = products.get(1);
        Assert.assertNotNull(second);
        Assert.assertEquals("Beer", second.getProductId());
        Assert.assertEquals("This product is out of stock", second.getStatus());
    }

    @Test
    public void unknownCustomerShouldSetErrorStatus() {
        Order response = template.requestBody("direct:in", toTestFile("unknown-customer.json"), Order.class);

        Assert.assertNotNull(response);
        Assert.assertEquals("UnknownCustomer", response.getCustomerId());
        Assert.assertEquals("The customer is not identified", response.getStatus());
    }

    @Test
    public void unparseableRequestShouldThrow() {
        thrown.expect(CamelExecutionException.class);

        template.requestBody("direct:in", toTestFile("parse-error.json"), Order.class);
    }

}
