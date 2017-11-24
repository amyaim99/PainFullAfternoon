package io.zipcoder;

import java.util.ArrayList;
import java.util.Arrays;

public class ItemParser {

    private String toBeChecked = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:2/25/2016##naMe:MiLK;price:3.23;type:Food^expiration:1/11/2016##naMe:Cookies;price:2.25;type:Food%expiration:1/25/2016##naMe:CoOkieS;price:2.25;type:Food*expiration:1/25/2016##naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016##naMe:COOkieS;price:2.25;type:Food;expiration:1/25/2016##NAME:MilK;price:3.23;type:Food;expiration:1/17/2016##naMe:MilK;price:1.23;type:Food!expiration:4/25/2016##naMe:apPles;price:0.25;type:Food;expiration:1/23/2016##naMe:apPles;price:0.23;type:Food;expiration:5/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:1/25/2016##naMe:;price:3.23;type:Food;expiration:1/04/2016##naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food@expiration:1/02/2016##NAMe:BrEAD;price:1.23;type:Food@expiration:2/25/2016##naMe:MiLK;priCe:;type:Food;expiration:1/11/2016##naMe:Cookies;price:2.25;type:Food;expiration:1/25/2016##naMe:Co0kieS;pRice:2.25;type:Food;expiration:1/25/2016##naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016##naMe:COOkieS;Price:2.25;type:Food;expiration:1/25/2016##NAME:MilK;price:3.23;type:Food;expiration:1/17/2016##naMe:MilK;priCe:;type:Food;expiration:4/25/2016##naMe:apPles;prIce:0.25;type:Food;expiration:1/23/2016##naMe:apPles;pRice:0.23;type:Food;expiration:5/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:1/25/2016##naMe:;price:3.23;type:Food^expiration:1/04/2016##\n";

    public ArrayList<String> parseRawDataIntoStringArray(String rawData) {
        String stringPattern = "##";
        ArrayList<String> response = splitStringWithRegexPattern(stringPattern, rawData);
        return response;
    }

    public Item parseStringIntoItem(String rawItem) throws ItemParseException {
        int index = 0;
        String name = parseIngleItemDataIntoName(rawItem, index).toUpperCase();
        Double price = Double.parseDouble(parseIngleItemDataIntoPrice(rawItem, 0));
        String type = parseIngleItemDataIntoType(rawItem, 0);
        String expiration = parseIngleItemDataIntoExpirationDate(rawItem, 0);
        Item item = new Item(name, price, type, expiration);
        return item;
    }

    public Item parseStringIntoItem(String rawItem, int index) throws ItemParseException {

            int errorCount = 0;
            Item item = null;
        try {
            String name = parseIngleItemDataIntoName(rawItem, index).toUpperCase();
            Double price = Double.parseDouble(parseIngleItemDataIntoPrice(rawItem, index));
            String type = parseIngleItemDataIntoType(rawItem, index);
            String expiration = parseIngleItemDataIntoExpirationDate(rawItem, index);
            item = new Item(name, price, type, expiration);
            return item;
        } catch (ItemParseException e) {

            System.out.println("The information isnt complete");
            errorCount++;
            return item;
        }

    }


    public ArrayList<String> findKeyValuePairsInRawItemData(String rawItem) {
        String stringPattern = "[;|^|@|%|*|!]";
        ArrayList<String> response = splitStringWithRegexPattern(stringPattern, rawItem);
        return response;
    }

    private ArrayList<String> splitStringWithRegexPattern(String stringPattern, String inputString) {
        return new ArrayList<String>(Arrays.asList(inputString.split(stringPattern)));
    }

    public ArrayList<String> parseStringIntoItemsNamePriceType(String rawItem) throws ItemParseException {
        String stringPattern = "[:|^]";
        ArrayList<String> response = splitStringWithRegexPattern(stringPattern, rawItem);
        return response;
    }

    public ArrayList<String> parseRawDataIntoSIngleItemData(String rawItem, int dataRaw) throws ItemParseException {

        ArrayList<String> separted = parseRawDataIntoStringArray(rawItem);

        ArrayList<String> itemInList = findKeyValuePairsInRawItemData(separted.get(dataRaw));
        return itemInList;

    }

    public String parseIngleItemDataIntoName(String rawItem, int dataRaw) throws ItemParseException {
          String name =null;
       try {
           //String name = null;

           ArrayList<String> itemData = parseRawDataIntoSIngleItemData(rawItem, dataRaw);

           ArrayList<String> itemInList = parseStringIntoItemsNamePriceType(itemData.get(0));

           name = itemInList.get(1);

           return name;
       }catch (ItemParseException e){
           System.out.println("counldnt find name");
           return "";
       }


    }

    public String parseIngleItemDataIntoPrice(String rawItem, int dataRaw) throws ItemParseException {

        String price = null;

        ArrayList<String> itemData = parseRawDataIntoSIngleItemData(rawItem, dataRaw);

        ArrayList<String> itemInList = parseStringIntoItemsNamePriceType(itemData.get(1));

        price = itemInList.get(1);

        return price;

    }

    public String parseIngleItemDataIntoType(String rawItem, int dataRaw) throws ItemParseException {

        String type = null;

        ArrayList<String> itemData = parseRawDataIntoSIngleItemData(rawItem, dataRaw);

        ArrayList<String> itemInList = parseStringIntoItemsNamePriceType(itemData.get(2));

        type = itemInList.get(1);

        return type;

    }

    public String parseIngleItemDataIntoExpirationDate(String rawItem, int dataRaw) throws ItemParseException {

        String date = null;

        ArrayList<String> itemData = parseRawDataIntoSIngleItemData(rawItem, dataRaw);

        ArrayList<String> itemInList = parseStringIntoItemsNamePriceType(itemData.get(3));

        date = itemInList.get(1);

        return date;

    }

    public String getToBeChecked() {
        return toBeChecked;
    }

}
