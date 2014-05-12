package com.monkey.android.utils;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ParseXml {
    private static SAXReader reader;
    private static Document document;
    private static Element root;
    private static String nodeValue;

    public static String parse(String path,String name) throws Exception{
        reader = new SAXReader();
        document = reader.read(new File(path));
        root = document.getRootElement();
        for(Iterator i = root.elementIterator();i.hasNext();) {
            Element el = (Element) i.next();
            Element node = el.element(name);
            if(node != null){
                nodeValue = node.getText();
            }
        }
        return nodeValue;
    }
}
