package com.tool.cloudminds.xmlparse;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

import static org.junit.Assert.*;

public class XmlParseUtilTest {

    @Test
    public void parseXMLWithSAX() {
        String path4 = "cmsdk_keys.xml";
        Map<String, PlatformKey> keys4 = null;
        try {
            keys4 = XmlParseUtil.parseXMLWithSAX(new FileInputStream(path4), PlatformKey.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(keys4.toString());
    }

    @Test
    public void parseXMLWithSourceString() {
        String test = "<xml>\n" +
                "   <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "   <return_msg><![CDATA[OK]]></return_msg>\n" +
                "   <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
                "   <mch_id><![CDATA[10000100]]></mch_id>\n" +
                "   <nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str>\n" +
                "   <sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign>\n" +
                "   <result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "   <prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id>\n" +
                "   <trade_type><![CDATA[APP]]></trade_type>\n" +
                "</xml>";
        WxOrder wxOrder = XmlParseUtil.parseXMLWithSourceString(test, WxOrder.class);
        System.out.println(wxOrder.toString());

    }
}