package io.zipcoder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ItemParserTest {

    private String rawSingleItem = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##";

    private String rawSingleItemIrregularSeperatorSample = "naMe:MiLK;price:3.23;type:Food^expiration:1/11/2016##";

    private String rawBrokenSingleItem = "naMe:;price:3.23;type:Food;expiration:1/25/2016##";

    private String rawMultipleItems = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##"
            + "naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##"
            + "NAMe:BrEAD;price:1.23;type:Food;expiration:2/25/2016##";
    private ItemParser itemParser;

    @Before
    public void setUp() {
        itemParser = new ItemParser();
    }

    @Test
    public void parseRawDataIntoStringArrayTest() {
        Integer expectedArraySize = 3;
        ArrayList<String> items = itemParser.parseRawDataIntoStringArray(rawMultipleItems);
        Integer actualArraySize = items.size();
        assertEquals(expectedArraySize, actualArraySize);
    }

    @Test
    public void parseStringIntoItemTest() throws ItemParseException {
        Item expected = new Item("milk", 3.23, "food", "1/25/2016");
        Item actual = itemParser.parseStringIntoItem(rawSingleItem);
        Item actual2 = itemParser.parseStringIntoItem2(rawSingleItem);
        assertEquals(expected.toString(), actual.toString());
        assertEquals(expected.toString(), actual2.toString());
    }

    @Test(expected = ItemParseException.class)
    public void parseBrokenStringIntoItemTest() throws ItemParseException {
        itemParser.parseStringIntoItem(rawBrokenSingleItem);
        itemParser.parseStringIntoItem2(rawBrokenSingleItem);


    }

    @Test
    public void findKeyValuePairsInRawItemDataTest() throws ItemParseException {
        Integer expected = 4;
        Integer actual = itemParser.findKeyValuePairsInRawItemData(rawSingleItem).size();
        String actual1 = itemParser.findKeyValuePairsInRawItemData(rawSingleItem).get(0);
        System.out.println(actual1);

        assertEquals(expected, actual);
        System.out.println(itemParser.findKeyValuePairsInRawItemData(rawSingleItem).get(1));
    }

    @Test
    public void findKeyValuePairsInRawItemDataTestIrregular() throws ItemParseException {
        Integer expected = 4;
        Integer actual = itemParser.findKeyValuePairsInRawItemData(rawSingleItemIrregularSeperatorSample).size();
        assertEquals(expected, actual);
    }


    @Test
    public void parseStringIntoItemsNameTest() throws ItemParseException {
        ArrayList<String> separtedArrayList = itemParser.parseRawDataIntoStringArray(rawMultipleItems);


        ArrayList<String> separtedList1 = itemParser.findKeyValuePairsInRawItemData(separtedArrayList.get(0));
        ArrayList<String> separtedList2 = itemParser.findKeyValuePairsInRawItemData(separtedArrayList.get(1));
        ArrayList<String> separtedList3 = itemParser.findKeyValuePairsInRawItemData(separtedArrayList.get(2));

        String expected1 = "MILK";
        String expected2 = "BREAD";
        String expected3 = "BREAD";

        String actual1 = itemParser.parseStringIntoItemsNamePriceType(separtedList1.get(0)).get(1).toUpperCase();
        String actual2 = itemParser.parseStringIntoItemsNamePriceType(separtedList2.get(0)).get(1).toUpperCase();
        String actual3 = itemParser.parseStringIntoItemsNamePriceType(separtedList3.get(0)).get(1).toUpperCase();

        Assert.assertEquals(expected1, actual1);
        Assert.assertEquals(expected2, actual2);
        Assert.assertEquals(expected3, actual3);

    }

    @Test
    public void parseStringIntoItemPriceTest() throws ItemParseException {
        ArrayList<String> separtedArrayList = itemParser.parseRawDataIntoStringArray(rawMultipleItems);
        ArrayList<String> separtedList1 = itemParser.findKeyValuePairsInRawItemData(separtedArrayList.get(0));
        ArrayList<String> separtedList2 = itemParser.findKeyValuePairsInRawItemData(separtedArrayList.get(1));
        ArrayList<String> separtedList3 = itemParser.findKeyValuePairsInRawItemData(separtedArrayList.get(2));

        String expected1 = "3.23";
        String expected2 = "1.23";
        String expected3 = "1.23";

        String actual1 = itemParser.parseStringIntoItemsNamePriceType(separtedList1.get(1)).get(1);
        String actual2 = itemParser.parseStringIntoItemsNamePriceType(separtedList2.get(1)).get(1);
        String actual3 = itemParser.parseStringIntoItemsNamePriceType(separtedList3.get(1)).get(1);

        Assert.assertEquals(expected1, actual1);
        Assert.assertEquals(expected2, actual2);
        Assert.assertEquals(expected3, actual3);

    }

    @Test
    public void parseStringIntoItemTypeTest() throws ItemParseException {
        ArrayList<String> separtedArrayList = itemParser.parseRawDataIntoStringArray(rawMultipleItems);
        ArrayList<String> separtedList1 = itemParser.findKeyValuePairsInRawItemData(separtedArrayList.get(0));
        ArrayList<String> separtedList2 = itemParser.findKeyValuePairsInRawItemData(separtedArrayList.get(1));
        ArrayList<String> separtedList3 = itemParser.findKeyValuePairsInRawItemData(separtedArrayList.get(2));


        String expected1 = "FOOD";
        String expected2 = "FOOD";
        String expected3 = "FOOD";

        String actual1 = itemParser.parseStringIntoItemsNamePriceType(separtedList1.get(2)).get(1).toUpperCase();
        String actual2 = itemParser.parseStringIntoItemsNamePriceType(separtedList2.get(2)).get(1).toUpperCase();
        String actual3 = itemParser.parseStringIntoItemsNamePriceType(separtedList3.get(2)).get(1).toUpperCase();

        Assert.assertEquals(expected1, actual1);
        Assert.assertEquals(expected2, actual2);
        Assert.assertEquals(expected3, actual3);

    }

    @Test
    public void parseStringIntoItemExpirationTest() throws ItemParseException {
        ArrayList<String> separtedArrayList = itemParser.parseRawDataIntoStringArray(rawMultipleItems);
        ArrayList<String> separtedList1 = itemParser.findKeyValuePairsInRawItemData(separtedArrayList.get(0));
        ArrayList<String> separtedList2 = itemParser.findKeyValuePairsInRawItemData(separtedArrayList.get(1));
        ArrayList<String> separtedList3 = itemParser.findKeyValuePairsInRawItemData(separtedArrayList.get(2));


        String expected1 = "1/25/2016";
        String expected2 = "1/02/2016";
        String expected3 = "2/25/2016";

        String actual1 = itemParser.parseStringIntoItemsNamePriceType(separtedList1.get(3)).get(1);
        String actual2 = itemParser.parseStringIntoItemsNamePriceType(separtedList2.get(3)).get(1);
        String actual3 = itemParser.parseStringIntoItemsNamePriceType(separtedList3.get(3)).get(1);

        Assert.assertEquals(expected1, actual1);
        Assert.assertEquals(expected2, actual2);
        Assert.assertEquals(expected3, actual3);

    }
    @Test
    public void parseRawDataIntoSIngleItemDataTest()throws ItemParseException{
        ArrayList<String> separtedArrayList = itemParser.parseRawDataIntoStringArray(rawMultipleItems);
        ArrayList<String> separtedList = itemParser.findKeyValuePairsInRawItemData(separtedArrayList.get(0));

        String expected = "[naMe:Milk, price:3.23, type:Food, expiration:1/25/2016]";
        ArrayList<String> separatedPerItem = itemParser.parseRawDataIntoSIngleItemData(rawMultipleItems,0);

       String actual = separatedPerItem.toString();

       Assert.assertEquals(expected, actual);




    }


    @Test
    public void parseIngleItemDataIntoNameTest() throws ItemParseException {

        String expected1 = "MILK";
        String expected2 = "BREAD";
        String expected3 = "BREAD";

        String actual1 = itemParser.parseIngleItemDataIntoName(rawMultipleItems, 0).toUpperCase();
        String actual2 = itemParser.parseIngleItemDataIntoName(rawMultipleItems, 1).toUpperCase();
        String actual3 = itemParser.parseIngleItemDataIntoName(rawMultipleItems, 2).toUpperCase();

        Assert.assertEquals(expected1, actual1);
        Assert.assertEquals(expected2, actual2);
        Assert.assertEquals(expected3, actual3);

    }

    @Test
    public void parseIngleItemDataIntoPriceTest() throws ItemParseException {

        String expected1 = "3.23";
        String expected2 = "1.23";
        String expected3 = "1.23";

        String actual1 = itemParser.parseIngleItemDataIntoPrice(rawMultipleItems, 0).toUpperCase();
        String actual2 = itemParser.parseIngleItemDataIntoPrice(rawMultipleItems, 1).toUpperCase();
        String actual3 = itemParser.parseIngleItemDataIntoPrice(rawMultipleItems, 2).toUpperCase();

        Assert.assertEquals(expected1, actual1);
        Assert.assertEquals(expected2, actual2);
        Assert.assertEquals(expected3, actual3);

    }

    @Test
    public void parseSingleItemDataIntoTypeTest() throws ItemParseException {

        String expected = "FOOD";
        String expected1 = "FOOD";
        String expected2 = "FOOD";

        String actual = itemParser.parseIngleItemDataIntoType(rawMultipleItems, 0).toUpperCase();
        String actual1 = itemParser.parseIngleItemDataIntoType(rawMultipleItems, 1).toUpperCase();
        String actual2 = itemParser.getTypeFromRawItem(rawMultipleItems).toUpperCase();

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expected1, actual1);
        Assert.assertEquals(expected2, actual2);

    }

    @Test
    public void parseIngleItemDataIntoExpirationDateTest() throws ItemParseException {

        String expected = "1/25/2016";
        String expected1 = "1/02/2016";
        String expected2 = "2/25/2016";

        String actual = itemParser.parseIngleItemDataIntoExpirationDate(rawMultipleItems, 0).toUpperCase();
        String actual1 = itemParser.parseIngleItemDataIntoExpirationDate(rawMultipleItems, 1).toUpperCase();
        String actual2 = itemParser.parseIngleItemDataIntoExpirationDate(rawMultipleItems,2).toUpperCase();

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expected1, actual1);
        Assert.assertEquals(expected2, actual2);


    }

    @Test
    public void getNameFromRawDataTest() throws ItemParseException {

        String expected1 = "MILK";

        String actual1 = itemParser.getNameFromRawItem(rawSingleItem).toUpperCase();

        Assert.assertEquals(expected1, actual1);

    }

    @Test
    public void getPriceFromRawDataTest() throws ItemParseException {

        String expected1 = "3.23";

        String actual1 = itemParser.getPriceFromRawItem(rawMultipleItems).toString();

        Assert.assertEquals(expected1, actual1);


    }

    @Test
    public void getTypeFromRawDataTest() throws ItemParseException {

        String expected = "FOOD";

        String actual = itemParser.getTypeFromRawItem(rawMultipleItems).toUpperCase();

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void getExpirationDateFromRawDataTest() throws ItemParseException {

        String expected = "1/25/2016";


        String actual = itemParser.getExpirationDateFromRawItem(rawMultipleItems).toUpperCase();


        Assert.assertEquals(expected, actual);
    }


}
