package com.monkey.android.utils;

public class Enviroment {
    public static String CONFFILEPATH = "conf.xml";

    public static String getConf(String nodeName){
        String nodeValue = "";
        try {
            nodeValue = ParseXml.parse(CONFFILEPATH,nodeName);
        }catch (Exception e){
            e.printStackTrace();
        }
        return nodeValue;
    }
}
