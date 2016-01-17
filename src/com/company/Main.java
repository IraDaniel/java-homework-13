package com.company;

import com.company.task2.Food;
import com.company.task2.MenuSaxHandler;
import com.company.task2.StAXMenuParser;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();

        System.out.println("---- StAX Parser ------");
        StAXMenuParser stAXMenuParser = new StAXMenuParser();

        try {

            InputStream input = new FileInputStream("test.xml");
            XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
            List<Food> menu = stAXMenuParser.process(reader);
            System.out.println("-- Show food list ");
            for (Food food : menu) {
                System.out.println(food.getName());
               // System.out.println(food.getCalories());
            }

        } catch (XMLStreamException e) {
            e.printStackTrace();

        }
        XMLReader reader = XMLReaderFactory.createXMLReader();

        System.out.println("---- SAX Parser ------");
        MenuSaxHandler handler = new MenuSaxHandler();
        reader.setContentHandler(handler);

        reader.parse(new InputSource("test.xml"));

        System.out.println("-- Show food list ");
        List<Food> menu = handler.getFoodList();
        for (Food food : menu) {
            System.out.println(food.getName());

        }
	// write your code here
    }
}