package com.company.task2;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ira on 11.01.2016.
 */
public class MenuSaxHandler extends DefaultHandler {
    private List<Food> foodList = new ArrayList<Food>();
    private Food food;
    private StringBuilder text;

    public List<Food> getFoodList() {
        return foodList;

    }

    public void startDocument() throws SAXException {
        System.out.println("Parsing started.");
    }

    public void endDocument() throws SAXException {
        System.out.println("Parsing ended.");
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //System.out.println("startElement -> " + "uri: " + uri + ", localName: " + localName + ", qName: " + qName);
        text = new StringBuilder();
        if (qName.equals("food")) {
            food = new Food();
            food.setId((Integer.parseInt(attributes.getValue("id"))));

        }

    }

    public void characters(char[] buffer, int start, int length) {
        text.append(buffer, start, length);
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        MenuTagName tagName =  MenuTagName.getElementTagName(qName);
        switch (tagName) {

            case NAME:
                food.setName(text.toString());
                break;

            case PRICE:
                food.setPrice(text.toString());
                break;

            case DESCRIPTION:
                food.setDescription(text.toString());
                break;

            case CALORIES:
                food.setCalories(Integer.parseInt(text.toString()));
                break;

            case FOOD:
                foodList.add(food);
                food = null;
                break;
            case BREAKFAST_MENU:
                break;

        }

    }

    public void warning(SAXParseException exception) {
        System.err.println("WARNING: line " + exception.getLineNumber() + ": " + exception.getMessage());
    }

    public void error(SAXParseException exception) {
        System.err.println("ERROR: line " + exception.getLineNumber() + ": " + exception.getMessage());
    }

    public void fatalError(SAXParseException exception) throws SAXException {
        System.err.println("FATAL: line " + exception.getLineNumber() + ": " + exception.getMessage());
        throw (exception);

    }
}
