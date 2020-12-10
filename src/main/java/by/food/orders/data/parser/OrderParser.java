package by.food.orders.data.parser;

import by.food.orders.data.DataException;
import by.food.orders.entity.Order;

import java.sql.Date;
import java.util.*;

public class OrderParser implements Parser<Order> {
    private static final String ID_PREFIX = "orderId";
    private static final String ORDER_DATE_PREFIX = "orderDate";
    private static final String DELIVERY_DATE_PREFIX = "deliveryDate";
    private static final String ORDER_OWNER_ID_PREFIX = "orderOwnerId";
    private static final String FOOD_LIST_ID_PREFIX = "foodListId";
    private static final String DATA_DELIMITER = ":";
    private static final String NOTHING = "";
    private static final String SEMI_COLUMN = ";";
    private static final char SEMI_COLUMN_CHAR = ';';
    private static final String SPLIT_PATTERN = ", ";


    @Override
    public Order parse(String stringForParsing) throws DataException {
        if (!stringForParsing.startsWith(ID_PREFIX)) {
            String message = String.format("Given: %s doesn't fit format: %s",
                    stringForParsing,
                    "orderId:x; orderDate:date; deliveryDate:date; orderOwnerId:x; foodListId:[x...];"
            );
            throw new DataException(message);
        }
        Map<String, String> fieldData = new HashMap<>();
        //prefixes to remove from starting string
        String[] prefixesArray = new String[]{
                ID_PREFIX, ORDER_DATE_PREFIX, DELIVERY_DATE_PREFIX,
                ORDER_OWNER_ID_PREFIX, FOOD_LIST_ID_PREFIX
        };
        fillFieldData(stringForParsing, fieldData, prefixesArray);
        return getOrder(fieldData);
    }


    private void fillFieldData(String stringForParsing, Map<String, String> fieldData, String[] prefixesArray) {
        //do operations for all prefixes
        for (String prefix : prefixesArray) {
            if (stringForParsing.startsWith(prefix)) {
                //remove current prefix from string
                stringForParsing = stringForParsing.replace(prefix + DATA_DELIMITER, NOTHING);
                //find end of the value in string
                int runner = 0;
                while (stringForParsing.charAt(runner) != SEMI_COLUMN_CHAR && (runner < stringForParsing.length() - 1)) {
                    ++runner;
                }
                //copy value to variable from string
                String value = stringForParsing.substring(0, runner);
                //put couple "prefix:value" to fields map
                fieldData.put(prefix, value);
                //delete value from string
                stringForParsing = stringForParsing.replaceFirst(fieldData.get(prefix) + SEMI_COLUMN + " ", NOTHING);
            }
        }
    }


    private Order getOrder(Map<String, String> fieldData) {
        //get  all fields data for Order object
        String stringId = fieldData.get(ID_PREFIX);
        String stringDeliveryDate = fieldData.get(DELIVERY_DATE_PREFIX);
        String stringOrderDate = fieldData.get(ORDER_DATE_PREFIX);
        String stringOwnerId = fieldData.get(ORDER_OWNER_ID_PREFIX);
        String stringFoodListId = fieldData.get(FOOD_LIST_ID_PREFIX);
        // parse all fields data for Order object
        long orderId = Long.parseLong(stringId);
        Date orderDate = new Date(Long.parseLong(stringOrderDate));
        Date deliveryDate = new Date(Long.parseLong(stringDeliveryDate));
        long orderOwnerId = Long.parseLong(stringOwnerId);
        //parse food ids string to list of ids
        List<Long> foodListId = parseFoodIdsToList(stringFoodListId);
        return new Order(orderId, orderDate, deliveryDate, orderOwnerId, foodListId);
    }

    private List<Long> parseFoodIdsToList(String stringFoodListId) {
        //parse [x...] to List<Long>
        stringFoodListId = stringFoodListId.substring(1, stringFoodListId.length() - 1);
        List<Long> foodListId = new ArrayList<>();
        String[] values = stringFoodListId.split(SPLIT_PATTERN);
        for (String value: values) {
            foodListId.add(Long.valueOf(value));
        }
        return foodListId;
    }

}
