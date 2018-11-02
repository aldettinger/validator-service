package org.aldettinger.camel.example;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class OrderValidatorTest {

    @Mock
    Order mockOrder;
    @Mock
    Product mockProduct;

    private OrderValidator orderValidator;

    @Before
    public void setup() {

        when(mockProduct.getProductId()).thenReturn("Water");
        List<Product> products = new ArrayList<>();
        products.add(mockProduct);
        when(mockOrder.getCustomerId()).thenReturn("aldettinger");
        when(mockOrder.getProducts()).thenReturn(products);

        orderValidator = new OrderValidator();
    }

    @Test
    public void validOrderShouldSetOkStatus() {
        orderValidator.validate(mockOrder);
        verify(mockOrder).setStatus("OK");
        verify(mockProduct).setStatus("OK");
    }

    @Test
    public void unknownCustomerShouldSetErrorStatus() {
        when(mockOrder.getCustomerId()).thenReturn("unknown customer");

        orderValidator.validate(mockOrder);
        verify(mockOrder).setStatus("The customer is not identified");
        verify(mockProduct).setStatus("OK");
    }

    @Test
    public void outOfStockShouldSetErrorStatus() {
        when(mockProduct.getProductId()).thenReturn("unknown product");

        orderValidator.validate(mockOrder);
        verify(mockOrder).setStatus("OK");
        verify(mockProduct).setStatus("This product does not exist");
    }

}
