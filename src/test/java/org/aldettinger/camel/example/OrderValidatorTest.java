package org.aldettinger.camel.example;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

@RunWith(org.mockito.runners.MockitoJUnitRunner.class)
public class OrderValidatorTest {

    @Mock
    Order mockOrder;
    @Mock
    Product mockProduct;

    private OrderValidator ov;

    @Before
    public void setup() {

        when(mockProduct.getProductId()).thenReturn("Water");
        List<Product> products = new ArrayList<>();
        products.add(mockProduct);
        when(mockOrder.getCustomerId()).thenReturn("aldettinger");
        when(mockOrder.getProducts()).thenReturn(products);

        ov = new OrderValidator();
    }

    @Test
    public void validOrderShouldSetOkStatus() {
        ov.validate(mockOrder);
        verify(mockOrder).setStatus("OK");
        verify(mockProduct).setStatus("OK");
    }

    @Test
    public void unknownCustomerShouldSetErrorStatus() {
        when(mockOrder.getCustomerId()).thenReturn("unknown customer");

        ov.validate(mockOrder);
        verify(mockOrder).setStatus("The customer is not identified");
        verify(mockProduct).setStatus("OK");
    }

    @Test
    public void outOfStockShouldSetErrorStatus() {
        when(mockProduct.getProductId()).thenReturn("unknown product");

        ov.validate(mockOrder);
        verify(mockOrder).setStatus("OK");
        verify(mockProduct).setStatus("This product does not exist");
    }

}
