package io.zipcoder;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class OutputTest {
    private String toBeChecked = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:2/25/2016##naMe:MiLK;price:3.23;type:Food^expiration:1/11/2016##naMe:Cookies;price:2.25;type:Food%expiration:1/25/2016##naMe:CoOkieS;price:2.25;type:Food*expiration:1/25/2016##naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016##naMe:COOkieS;price:2.25;type:Food;expiration:1/25/2016##NAME:MilK;price:3.23;type:Food;expiration:1/17/2016##naMe:MilK;price:1.23;type:Food!expiration:4/25/2016##naMe:apPles;price:0.25;type:Food;expiration:1/23/2016##naMe:apPles;price:0.23;type:Food;expiration:5/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:1/25/2016##naMe:;price:3.23;type:Food;expiration:1/04/2016##naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food@expiration:1/02/2016##NAMe:BrEAD;price:1.23;type:Food@expiration:2/25/2016##naMe:MiLK;priCe:;type:Food;expiration:1/11/2016##naMe:Cookies;price:2.25;type:Food;expiration:1/25/2016##naMe:Co0kieS;pRice:2.25;type:Food;expiration:1/25/2016##naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016##naMe:COOkieS;Price:2.25;type:Food;expiration:1/25/2016##NAME:MilK;price:3.23;type:Food;expiration:1/17/2016##naMe:MilK;priCe:;type:Food;expiration:4/25/2016##naMe:apPles;prIce:0.25;type:Food;expiration:1/23/2016##naMe:apPles;pRice:0.23;type:Food;expiration:5/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:1/25/2016##naMe:;price:3.23;type:Food^expiration:1/04/2016##\n";


    @Test

    public void itemNamePrinterTest() throws ItemParseException {
        ItemParser itemParser = new ItemParser();
        final ArrayList<Item> items = new ArrayList<Item>();
        try {
            for (int i = 0; i < 14; i++) {
                items.add(itemParser.parseStringIntoItem(toBeChecked, i));
            }
        } catch (ItemParseException e) {

        }

        String expected = "name:\tBread\t\tseen: 3 times\n" +
                "==============\t\t==============";
        String actual = Output.itemNamePrinter(items,"Bread").trim();
        Assert.assertEquals(expected, actual);
    }

    @Test

    public void itemPricePrinterTest() throws ItemParseException {
        ItemParser itemParser = new ItemParser();
        final ArrayList<Item> items = new ArrayList<Item>();
        try {
            for (int i = 0; i < 14; i++) {
                items.add(itemParser.parseStringIntoItem(toBeChecked, i));
            }
        } catch (ItemParseException e) {

        }

        String expected = "price:	1.23		seen: 1 times\n--------------		--------------";
        String actual = Output.itemPricePrinter(items,"milk", 1.23).trim();

        Assert.assertEquals(expected, actual);
    }

    @Test

    public void itemErrorPrinterTest() throws ItemParseException {
        ItemParser itemParser = new ItemParser();
        final ArrayList<Item> items = new ArrayList<Item>();
        try {
            for (int i = 0; i < 14; i++) {
                items.add(itemParser.parseStringIntoItem(toBeChecked, i));
            }
        } catch (ItemParseException e) {

        }

        String expected = "Error\t\t\t\tseen: 1";
        String actual = Output.itemErrorPrinter(itemParser).trim();
        Assert.assertEquals(expected, actual);



    }



}
