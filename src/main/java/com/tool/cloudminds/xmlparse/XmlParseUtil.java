package com.tool.cloudminds.xmlparse;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.SAXParserFactory;

/**
 * created by sain on 11/7/18
 */
public class XmlParseUtil {
    private static final String TAG = "XmlParseUtil";

    /**
     * @param inputStream  xml文件打开之后的文件流, 该方法主要用于无法获取文件的绝对地址时, 如Android平台的assert目录
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> Map<String, T> parseXMLWithSAX(InputStream inputStream, Class<?> cls) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            XMLReader xmlReader = factory.newSAXParser().getXMLReader();
            ContentHandler<T> handler = new ContentHandler(cls);
            xmlReader.setContentHandler(handler);
            //开始执行解析
            xmlReader.parse(new InputSource(inputStream));
            return handler.getKeyMap();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @param filePath  xml文件名, 需要能找到该文件
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> Map<String, T> parseXMLWithSAX(String filePath, Class<?> cls) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            XMLReader xmlReader = factory.newSAXParser().getXMLReader();
            ContentHandler<T> handler = new ContentHandler(cls);
            xmlReader.setContentHandler(handler);
            //开始执行解析
            xmlReader.parse(filePath);
            return handler.getKeyMap();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     *
     * @param source xml文件对应的字符串
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T parseXMLWithSourceString(String source, Class<?> cls) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            XMLReader xmlReader = factory.newSAXParser().getXMLReader();
            ContentHandler<T> handler = new ContentHandler(cls);
            xmlReader.setContentHandler(handler);
            //开始执行解析
            xmlReader.parse(new InputSource(new StringReader(source)));
            return handler.getKey();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private static class ContentHandler<T> extends DefaultHandler {

        private Map<String, T> keyMap = new HashMap<>();
        private T key;
        private Class<T> clazz;
        String tagName;

        public Map<String, T> getKeyMap() {
            return keyMap;
        }

        public T getKey() {
            return key;
        }

        public ContentHandler(Class<T> clazz) {
            this.clazz = clazz;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            tagName = qName;
            if (attributes.getLength() > 0) {
                try {
                    key = clazz.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                int num = attributes.getLength();
                for (int i = 0; i < num; i++) {
                    setValues(key, attributes.getQName(i), attributes.getValue(i));
                    keyMap.put(qName, key);
                }
            }
        }


        /**
         *
         * 解析这一类字符串时,会走characters方法, 实际中发现该方法会走多次,要避免被复写
         * @param ch
         * @param start
         * @param length
         * @throws SAXException
         */
        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            if (key == null) {
                try {
                    key = clazz.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            if (length > 0 ) {
                String string = new String(ch,start,length);
                if (string.trim().length() > 0) {
                    setValues(key, tagName, string);
                }
            }
        }
    }

    private static void setValues(Object f, String attribute, String value) {
        // 获取f对象对应类中的所有属性域
        Field[] fields = f.getClass().getDeclaredFields();
        for(int i = 0 , len = fields.length; i < len; i++) {
            // 对于每个属性，获取属性名
            String varName = fields[i].getName();
            if (varName.equals(attribute)) {
                try {
                    // 获取原来的访问控制权限
                    boolean accessFlag = fields[i].isAccessible();
                    // 修改访问控制权限
                    fields[i].setAccessible(true);
                    fields[i].set(f, value);
                    fields[i].setAccessible(accessFlag);
                } catch (IllegalArgumentException ex) {
                    ex.printStackTrace();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}








