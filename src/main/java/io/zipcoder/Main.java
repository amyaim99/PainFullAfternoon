package io.zipcoder;

import org.apache.commons.io.IOUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


public class Main {
    private static Integer errorCount = 0;

    public String readRawDataToString() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        return result;
    }

    public static void main(String[] args) throws Exception {
        String output = (new Main()).readRawDataToString();

        ItemParser itemParser = new ItemParser();
        final ArrayList<Item> items = new ArrayList<Item>();

        itemParser.parseStringIntoItem(output);


        for (int i = 0; i < 28; i++) {
            try {
                items.add(itemParser.parseStringIntoItem(output , i));

            } catch (ItemParseException e) {
                errorCount++;
                continue;
            }
        }

        final ArrayList<Item> items2 = new ArrayList<Item>();
        ArrayList<String> itemsList = itemParser.parseRawDataIntoStringArray(output);
        for (int i = 0; i < itemsList.size(); i++) {
            String separatedItemList = itemsList.get(i);
            try {
                items2.add(itemParser.parseStringIntoItem2(separatedItemList));

            } catch (ItemParseException e) {
                errorCount++;
                continue;
            }
        }


        String milk = Output.itemNamePrinter(items , "milk");
        String cookies = Output.itemNamePrinter(items , "cookies");
        String bread = Output.itemNamePrinter(items , "bread");
        String apples = Output.itemNamePrinter(items , "apples");
        String milkPrice1 = Output.itemPricePrinter(items , "milk" , 3.23);
        String milkPrice2 = Output.itemPricePrinter(items , "milk" , 1.23);
        String breadPrice = Output.itemPricePrinter(items , "bread" , 1.23);
        String cookiesPrice = Output.itemPricePrinter(items , "cookies" , 2.25);
        String applesPrice1 = Output.itemPricePrinter(items , "apples" , 0.25);
        String applesPrice2 = Output.itemPricePrinter(items , "apples" , 0.23);
        String errorCount = Output.itemErrorPrinter(itemParser);

        output = milk + milkPrice1 + milkPrice2 + bread + breadPrice + cookies + cookiesPrice +
                apples + applesPrice1 + applesPrice2 + errorCount;
        System.out.println(output);


        String milk2 = Output.itemNamePrinter(items2 , "milk");
        String cookies2 = Output.itemNamePrinter2(items2 , "cookies");
        String bread2 = Output.itemNamePrinter(items2 , "bread");
        String apples2 = Output.itemNamePrinter(items2 , "apples");
        String milkPrice12 = Output.itemPricePrinter(items2 , "milk" , 3.23);
        String milkPrice22 = Output.itemPricePrinter(items2 , "milk" , 1.23);
        String breadPrice2 = Output.itemPricePrinter(items2 , "bread" , 1.23);
        String cookiesPrice2 = Output.itemPricePrinter2(items2 , "cookies" , 2.25);
        String applesPrice12 = Output.itemPricePrinter(items2 , "apples" , 0.25);
        String applesPrice22 = Output.itemPricePrinter(items2 , "apples" , 0.23);
        String errorCount2 = Output.itemErrorPrinter(itemParser);

        String output2 = milk2 + milkPrice12 + milkPrice22 + bread2 + breadPrice2 + cookies2 + cookiesPrice2 +
                apples2 + applesPrice12 + applesPrice22 + errorCount2;
        System.out.println(output2);


    }


}

