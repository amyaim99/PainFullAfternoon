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

        try {
            for (int i = 0; i < 14; i++) {
                items.add(itemParser.parseStringIntoItem(output, i));
            }
        } catch (ItemParseException e) {
            errorCount++;
        }
        try {
            for (int i = 14; i < 18; i++) {
                items.add(itemParser.parseStringIntoItem(output, i));
            }

        } catch (ItemParseException e) {
            errorCount++;
        }
        try {
            for (int i = 18; i < 24; i++) {
                items.add(itemParser.parseStringIntoItem(output, i));
            }

        } catch (ItemParseException e) {
            errorCount++;
        }
        try {
            for (int i = 24; i < 28; i++) {
                items.add(itemParser.parseStringIntoItem(output, i));
            }

        } catch (ItemParseException e) {
            errorCount++;
        }
        Map<String, Long> counted = items.stream()
                .map(item -> item.getName()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

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
        String errorCount=  Output.itemErrorPrinter(itemParser);

       String  output1 = milk + milkPrice1 + milkPrice2 + bread + breadPrice + cookies + cookiesPrice + apples + applesPrice1 +
                applesPrice2 + errorCount;
        System.out.println(output1);

    }


    // TODO: parse the data in output into items, and display to console.
}

