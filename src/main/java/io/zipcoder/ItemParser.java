package io.zipcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemParser {
    private int errorCounter;


    private String toBeChecked = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:2/25/2016##naMe:MiLK;price:3.23;type:Food^expiration:1/11/2016##naMe:Cookies;price:2.25;type:Food%expiration:1/25/2016##naMe:CoOkieS;price:2.25;type:Food*expiration:1/25/2016##naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016##naMe:COOkieS;price:2.25;type:Food;expiration:1/25/2016##NAME:MilK;price:3.23;type:Food;expiration:1/17/2016##naMe:MilK;price:1.23;type:Food!expiration:4/25/2016##naMe:apPles;price:0.25;type:Food;expiration:1/23/2016##naMe:apPles;price:0.23;type:Food;expiration:5/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:1/25/2016##naMe:;price:3.23;type:Food;expiration:1/04/2016##naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food@expiration:1/02/2016##NAMe:BrEAD;price:1.23;type:Food@expiration:2/25/2016##naMe:MiLK;priCe:;type:Food;expiration:1/11/2016##naMe:Cookies;price:2.25;type:Food;expiration:1/25/2016##naMe:Co0kieS;pRice:2.25;type:Food;expiration:1/25/2016##naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016##naMe:COOkieS;Price:2.25;type:Food;expiration:1/25/2016##NAME:MilK;price:3.23;type:Food;expiration:1/17/2016##naMe:MilK;priCe:;type:Food;expiration:4/25/2016##naMe:apPles;prIce:0.25;type:Food;expiration:1/23/2016##naMe:apPles;pRice:0.23;type:Food;expiration:5/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:1/25/2016##naMe:;price:3.23;type:Food^expiration:1/04/2016##\n";

    public ArrayList<String> parseRawDataIntoStringArray(String rawData) {
        String stringPattern = "##";
        ArrayList<String> response = splitStringWithRegexPattern(stringPattern , rawData);
        return response;
    }

    public Item parseStringIntoItem(String rawItem) throws ItemParseException {
        int index = 0;
        String name = parseIngleItemDataIntoName(rawItem , index).toUpperCase();
        Double price = Double.parseDouble(parseIngleItemDataIntoPrice(rawItem , 0));
        String type = parseIngleItemDataIntoType(rawItem , 0);
        String expiration = parseIngleItemDataIntoExpirationDate(rawItem , 0);
        Item item = new Item(name , price , type , expiration);
        return item;
    }

    public Item parseStringIntoItem(String rawItem , int index) throws ItemParseException {
        // return null;

        String name = parseIngleItemDataIntoName(rawItem , index).toUpperCase();
        Double price = Double.parseDouble(parseIngleItemDataIntoPrice(rawItem , index));
        String type = parseIngleItemDataIntoType(rawItem , index);
        String expiration = parseIngleItemDataIntoExpirationDate(rawItem , index);
        return new Item(name , price , type , expiration);


    }

    public ArrayList<Item> parseStringIntoItems(String rawItem ) throws ItemParseException {
        int  index = parseRawDataIntoStringArray(rawItem).size();
        Item item = null;
        ArrayList<Item> items = new ArrayList<>();
    for (int i =0; i <index; i++) {
        String name = parseIngleItemDataIntoName(rawItem , index).toUpperCase();
        Double price = Double.parseDouble(parseIngleItemDataIntoPrice(rawItem , index));
        String type = parseIngleItemDataIntoType(rawItem , index);
        String expiration = parseIngleItemDataIntoExpirationDate(rawItem , index);
        item = new Item(name , price , type , expiration);
        items.add(item);

    }
    return items;
    }


    public ArrayList<String> findKeyValuePairsInRawItemData(String rawItem) throws ItemParseException {
        String stringPattern = "[;|^|@|%|*|!|##]";
        ArrayList<String> separatedList = new ArrayList<String>();
        separatedList = parseRawDataIntoStringArray(rawItem);
        ArrayList<String> response = new ArrayList<String>();

        response = splitStringWithRegexPattern(stringPattern , rawItem);

        return response;
    }

    private ArrayList<String> splitStringWithRegexPattern(String stringPattern , String inputString) {
        return new ArrayList<String>(Arrays.asList(inputString.split(stringPattern)));
    }

    public ArrayList<String> parseStringIntoItemsNamePriceType(String rawItem) throws ItemParseException {
        ArrayList<String> separatedList = new ArrayList<String>();
        separatedList = findKeyValuePairsInRawItemData(rawItem);
        String stringPattern = "[:|^]";

        ArrayList<String> response;
        if (!separatedList.get(0).equalsIgnoreCase("name:")){
            response = splitStringWithRegexPattern(stringPattern , rawItem);
        } else {
            errorCounter++;
            response = null;
            throw new ItemParseException(); //
        }


        return response;

    }

    public ArrayList<String> parseRawDataIntoSIngleItemData(String rawItem , int dataRaw) throws ItemParseException {

        ArrayList<String> separted = parseRawDataIntoStringArray(rawItem);

        ArrayList<String> itemInList = findKeyValuePairsInRawItemData(separted.get(dataRaw));
        return itemInList;
    }


    public String parseIngleItemDataIntoName(String rawItem , int dataRaw) throws ItemParseException {
        String name = null;


        ArrayList<String> itemData = parseRawDataIntoSIngleItemData(rawItem , dataRaw);

            ArrayList<String> itemInList = parseStringIntoItemsNamePriceType(itemData.get(0));
        if ((!itemData.get(0).equalsIgnoreCase("name:"))) {
            name = itemInList.get(1);
            return name;
        }else {
            errorCounter++;
            name = null;
            throw new ItemParseException(); //||(!separatedList.get(1).matches(stringPattern3))
        }

    }


    public String parseIngleItemDataIntoPrice(String rawItem , int dataRaw) throws ItemParseException {

        String price = null;

        ArrayList<String> itemData = parseRawDataIntoSIngleItemData(rawItem , dataRaw);

        if (!itemData.get(1).equalsIgnoreCase("price:")) {
            ArrayList<String> itemInList = parseStringIntoItemsNamePriceType(itemData.get(1));

            price = itemInList.get(1);
        } else {
            errorCounter++;
            price = null;
            throw new ItemParseException();

        }

        return price;

    }

    public String parseIngleItemDataIntoType(String rawItem , int dataRaw) throws ItemParseException {

        String type = null;

        ArrayList<String> itemData = parseRawDataIntoSIngleItemData(rawItem , dataRaw);

        ArrayList<String> itemInList = parseStringIntoItemsNamePriceType(itemData.get(2));

        type = itemInList.get(1);

        return type;


    }

    public String parseIngleItemDataIntoExpirationDate(String rawItem , int dataRaw) throws ItemParseException {


        String date = null;
        ArrayList<String> itemData = parseRawDataIntoSIngleItemData(rawItem , dataRaw);

        ArrayList<String> itemInList = parseStringIntoItemsNamePriceType(itemData.get(3));

        date = itemInList.get(1);

        return date;


    }


    public String getNameFromRawItem(String rawItem) throws ItemParseException{
        ArrayList<String> separetedList = findKeyValuePairsInRawItemData(rawItem);
        String namePair = separetedList.get(0);
        String name="";

        try{
            name = namePair.split(":")[1];
        }catch (Exception e){
            new ItemParseException();

        }
        return name;

    }



    public Double getPriceFromRawItem(String rawItem) throws ItemParseException{
        ArrayList<String> keyValuePairs = findKeyValuePairsInRawItemData(rawItem);
        String valuePair = keyValuePairs.get(1);
        Double price = 0.0;
        try{
            String value = valuePair.split(":")[1];
            price = Double.parseDouble(value);
        }catch (Exception e){
            new ItemParseException();

        }
        return price;
    }

    public String getTypeFromRawItem(String rawItem) throws ItemParseException {
        ArrayList<String> keyValuePairs = findKeyValuePairsInRawItemData(rawItem);
        String typePair = keyValuePairs.get(2);
        String type ="";
        try{ type = typePair.split(":")[1];
        }catch (Exception e){
            new ItemParseException();

        }
        return type;
    }

    public String getExpirationDateFromRawItem(String rawItem) throws ItemParseException {
        ArrayList<String> keyValuePairs = findKeyValuePairsInRawItemData(rawItem);
        String expirationPair = keyValuePairs.get(3);
        String expiration="";
        try{
            expiration = expirationPair.split(":")[1];
        }catch(Exception e){
            new ItemParseException();

        }
        return expiration;
    }

    public Item parseStringIntoItem2(String rawItem) throws ItemParseException {

        try {
            String name = getNameFromRawItem(rawItem).toLowerCase();
            Double price = getPriceFromRawItem(rawItem);
            String type = getTypeFromRawItem(rawItem).toLowerCase();
            String expiration = getExpirationDateFromRawItem(rawItem).toLowerCase();
            Item item = new Item(name , price , type , expiration);
            return item;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ItemParseException();
        }
    }




    public String getToBeChecked() {
        return toBeChecked;
    }

    public int getErrorCounter() {
        return errorCounter;
    }
}
