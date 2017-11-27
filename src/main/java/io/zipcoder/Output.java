package io.zipcoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public static String itemsPrinter(ArrayList<Item> items){


        String milk = Output.itemNamePrinter(items,"milk");
        String cookies= Output.itemNamePrinter(items,"cookies");
        String bread= Output.itemNamePrinter(items,"bread");
        String apples= Output.itemNamePrinter(items,"apples");
        String milkPrice1=  Output.itemPricePrinter(items,"milk",3.23);
        String milkPrice2=  Output.itemPricePrinter(items,"milk",1.23);
        String breadPrice=  Output.itemPricePrinter(items,"bread",1.23);
        String cookiesPrice=  Output.itemPricePrinter(items,"cookies",2.25);
        String applesPrice1=  Output.itemPricePrinter(items,"apples",0.25);
        String applesPrice2=  Output.itemPricePrinter(items,"apples",0.23);

        String  output1 = milk + milkPrice1 + milkPrice2 + bread + breadPrice + cookies + cookiesPrice + apples + applesPrice1 +
                applesPrice2 ;

       return output1;
    }

}


