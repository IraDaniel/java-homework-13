package com.company.task2;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ira on 12.01.2016.
 */
public class StAXMenuParser {

    public List<Food> process(XMLStreamReader reader) throws XMLStreamException {

        List<Food> menu = new ArrayList<Food>();

        Food food = null;

        MenuTagName elementName = null;

        while (reader.hasNext()) {
            // определение типа "прочтённого" элемента (тега)
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    elementName = MenuTagName.getElementTagName(reader.getLocalName());
                    switch (elementName) {
                        case FOOD:
                            food = new Food();
                            Integer id = Integer.parseInt(reader.getAttributeValue(null, "id"));
                            food.setId(id);
                            break;
                    }
                    break;

                case XMLStreamConstants.CHARACTERS:
                    String text = reader.getText().trim();
                    if (text.isEmpty()) {
                        break;
                    }
                    switch (elementName) {
                        case NAME:
                            food.setName(text);
                            break;

                        case PRICE:
                            food.setPrice(text);
                            break;

                        case DESCRIPTION:
                            food.setDescription(text);
                            break;

                        case CALORIES:
                            Integer calories = Integer.parseInt(text);
                            food.setCalories(calories);
                            break;
                    }

                    break;

                case XMLStreamConstants.END_ELEMENT:
                    elementName = MenuTagName.getElementTagName(reader.getLocalName());
                    switch (elementName) {
                        case FOOD:
                            menu.add(food);
                    }
            }
        }
        return menu;


    }

}
