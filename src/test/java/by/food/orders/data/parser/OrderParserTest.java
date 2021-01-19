package by.food.orders.data.parser;

/*
import by.food.orders.exception.DataException;
import by.food.orders.data.dao.parser.OrderParser;
import by.food.orders.data.dao.parser.Parser;
import by.food.orders.entity.Order;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

public class OrderParserTest {
    private static final String ORDER = "orderId:1; price:15.5; orderDate:2021-10-12; deliveryDate:2021-11-12; orderOwnerId:1; foodListId:[1, 2];";
    private static final Order EXPECTED = new Order(1, LocalDate.parse("2021-10-12"), LocalDate.parse("2021-11-12"), 1, Arrays.asList(1L, 2L), new BigDecimal("15.5"));


    @Test
    public void testParseShouldParseStringToOrder() throws DataException {
        Parser<Order> parser = new OrderParser();
        Order actual = parser.parse(ORDER);
        Assert.assertEquals(actual, EXPECTED);
    }
}

*/
