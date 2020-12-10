package by.food.orders.data.parser;


import by.food.orders.data.DataException;
import by.food.orders.entity.Order;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;

public class OrderParserTest {
    private static final String ORDER = "orderId:1; orderDate:1607629789309; deliveryDate:1607629789309; orderOwnerId:1; foodListId:[1, 2];";
    private static final Order EXPECTED = new Order(1, new Date(1607629789309L), new Date(1607629789309L), 1, Arrays.asList(1L, 2L));


    @Test
    public void testParseShouldParseStringToOrder() throws DataException {
        Parser<Order> parser = new OrderParser();
        Order actual = parser.parse(ORDER);
        Assert.assertEquals(actual, EXPECTED);
    }

}
