package io.zipcoder;

import org.apache.commons.io.IOUtils;

import java.util.ArrayList;


public class Main {

    public String readRawDataToString() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        return result;
    }

    public static void main(String[] args) throws Exception{
        String output = (new Main()).readRawDataToString();
        System.out.println(output);
        ItemParser itemParser = new ItemParser();
        ArrayList<Item> items = new ArrayList<Item>();

        itemParser.parseStringIntoItem(output);

        for(int i=0;   i<14; i++) {
            items.add(itemParser.parseStringIntoItem(output,i));
        }
        for (Item item:items) {
            System.out.println(item);

        }
        System.out.println(items.get(14));
//            String name = itemParser.parseIngleItemDataIntoName(output, i).toUpperCase();
//            Double price = Double.parseDouble(itemParser.parseIngleItemDataIntoPrice(output, i));
//            String type = itemParser.parseIngleItemDataIntoType(output, i);
//            String expiration = itemParser.parseIngleItemDataIntoExpirationDate(output, i);
//
//            Item item = new Item(name, price, type, expiration);
//            items.add(item);
//        }
//        for (Item item:items) {
//            System.out.println(item.toString());
//
//        }
//        System.out.println();
//       // System.out.println(item.toString());// TODO: parse the data in output into items, and display to console.
    }
}
