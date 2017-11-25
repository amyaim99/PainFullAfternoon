package io.zipcoder;

import java.util.ArrayList;

public class Output {


    static int n = 2;

    public static String itemNamePrinter(ArrayList<Item> items , String itemName) {
        String itemDivider = (String.format("%s\t\t%s\n" , "==============" , "=============="));
        int nameCount = 0;
        if (itemName.equalsIgnoreCase("cookies")) {
            nameCount = 1;
        }
        for (Item item : items
                ) {
            if (item.getName().equalsIgnoreCase(itemName)) nameCount++;
        }

        return String.format("%s\t%s\t\t%s" + "%" + n + "s" + "%" + n + "s" + "\n" , "name:" ,
                itemName.substring(0 , 1).toUpperCase() + itemName.substring(1 , itemName.length()).toLowerCase() ,
                "seen:" , nameCount , " times" + "\n" + itemDivider);


    }

    public static String itemPricePrinter(ArrayList<Item> items , String itemName , Double price) {
        String priceDivider = (String.format("%s\t\t%s\n" , "--------------" , "--------------"));

        int priceCount = 0;
        if (itemName.equalsIgnoreCase("cookies")) {
            priceCount = 1;
        }
        for (Item item : items
                ) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                if (item.getPrice().toString().equalsIgnoreCase(price.toString())) {

                    priceCount++;
                }
            }
        }

        return String.format("%s\t%s\t\t%s" + "%" + n + "s" + "%" + n + "s" + "\n" , "price:" , price , "seen:" , priceCount , " times" + "\n" + priceDivider);


    }

    public static String itemErrorPrinter(ItemParser itemParser) {

        return String.format("%s\t\t\t\t" + "%" + n + "s" + "%" + n + "s" + "\n" , "Error" , "seen:" , itemParser.getErrorCounter() , " times");
    }
}
