package by.food.orders.data.reader;


import by.food.orders.exception.DataException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class OrderDataReaderTest {
    private static final String SRC_TEST_ORDERS = "src/test/resources/orders.txt";
    private static final String INVALID_PATH = "nothing";

    private static final String  FIRST_ORDER = "id:1, orderDate:2020-10-09, deliveryDate:2020-10-15, orderOwnerId:1, foodListId:[1, 2]";
    private static final String  SECOND_ORDER = "id:2, orderDate:2020-11-03, deliveryDate:2020-11-11, orderOwnerId:2, foodListId:[2, 3]";
    private static final String  THIRD_ORDER = "id:3, orderDate:2020-11-09, deliveryDate:2020-11-29, orderOwnerId:3, foodListId:[1, 3]";
    private static final String  FOURTH_ORDER = "id:4, orderDate:2020-11-21, deliveryDate:2020-11-30, orderOwnerId:1, foodListId:[1, 2, 3]";

    private static final List<String> EXPECTED_ORDERS = Arrays.asList(FIRST_ORDER, SECOND_ORDER, THIRD_ORDER, FOURTH_ORDER);


    @Test
    public void testReadDataShouldReadDataFromFile() throws DataException {
	   OrderDataReader dataReader = new OrderDataReader();
	   List<String> actualOrders = dataReader.readData(SRC_TEST_ORDERS);
	   Assert.assertEquals(actualOrders, EXPECTED_ORDERS);
    }

    @Test(expectedExceptions = DataException.class)
    public void testReadDataShouldThrowDataException() throws DataException {
	   OrderDataReader dataReader = new OrderDataReader();
	   List<String> actualOrders = dataReader.readData(INVALID_PATH);
	   Assert.assertEquals(actualOrders, EXPECTED_ORDERS);
    }
}
