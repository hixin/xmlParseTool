## xmlParseTool

解析xml的工具类, 借鉴Retrofit的思想, 对属性不同的xml, 只需要定义不同的目标Bean类。做项目时自己编写的，支持解析常见的两种xml形式，开源出来！

## 如何引入

You can use Gradle:
```
implementation 'com.cloudminds:xmlParse:0.1.3-preview'
```

## 使用方法（可参考测试用例）
支持解析两种形式的xml， 定义一个bean类，自动装箱。

### value位于标签属性中
```
<?xml version="1.0" encoding="utf-8"?>

<keyInfo>

    <QQ
        AppKey="101512302"
        AppSecret=""
        RedirectUrl=""
        Extra=""
        Enable="true"/>

    <Wechat
        AppKey="wx9d746ce5d6537a32"
        AppSecret=""
        RedirectUrl=""
        Extra=""
        Enable="true"/>

    <SinaWeibo
        AppKey="3602874645"
        AppSecret=""
        RedirectUrl="https://api.weibo.com/oauth2/default.html"
        Extra=""
        Enable="true"/>

    <Alipay
        AppKey="2018102561869072"
        AppSecret="2088421745354073"
        RedirectUrl=""
        Extra=""
        Enable="false"/>

</keyInfo>
```
1. 定义bean类：
```
public class PlatformKey {
    private String AppKey;
    private String AppSecret;
    private String RedirectUrl;
    private String Extra;
    private String Enable;
}
```
2. 直接解析：
```
  Map<String, PlatformKey> keys = XmlParseUtil.parseXMLWithSAX(new FileInputStream("xml路径"), PlatformKey.class);
  System.out.println(keys.toString());
```
3. 输出结果
 ```
 {QQ=PlatformKey{AppKey='101512302', AppSecret='', RedirectUrl='', Extra='', Enable='true'}, Alipay=PlatformKey{AppKey='2018102561869072', AppSecret='2088421745354073', RedirectUrl='', Extra='', Enable='false'}, Wechat=PlatformKey{AppKey='wx9d746ce5d6537a32', AppSecret='', RedirectUrl='', Extra='', Enable='true'}, SinaWeibo=PlatformKey{AppKey='3602874645', AppSecret='', RedirectUrl='https://api.weibo.com/oauth2/default.html', Extra='', Enable='true'}}

 ```

### value位于标签之间的
微信开放平台很多API都是以这种形式的xml返回
```
<xml>
   <return_code><![CDATA[SUCCESS]]></return_code>
   <return_msg><![CDATA[OK]]></return_msg>
   <appid><![CDATA[wx2421b1c4370ec43b]]></appid>
   <mch_id><![CDATA[10000100]]></mch_id>
   <nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str>
   <sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign>
   <result_code><![CDATA[SUCCESS]]></result_code>
   <prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id>
   <trade_type><![CDATA[APP]]></trade_type>
</xml>
```
1. 定义bean类：
```
public class WxOrder {
    private String return_code;
    private String return_msg;
    private String appid;
    private String mch_id; //商户号
    private String nonce_str;
    private String sign;
    private String result_code;
    private String prepay_id;
    private String trade_type;
}
```
2. 直接解析：
```
 WxOrder wxOrder = XmlParseUtil.parseXMLWithSourceString("xml对应的string", WxOrder.class);
 System.out.println(wxOrder.toString());
 ```
 3. 输出结果：
 ```
WxOrder{return_code='SUCCESS', return_msg='OK', appid='wx2421b1c4370ec43b', mch_id='10000100', nonce_str='IITRi8Iabbblz1Jc', sign='7921E432F65EB8ED0CE9755F0E86D72F', result_code='SUCCESS', prepay_id='wx201411101639507cbf6ffd8b0779950874', trade_type='APP'}
 ```
