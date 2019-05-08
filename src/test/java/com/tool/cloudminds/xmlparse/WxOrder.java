package com.tool.cloudminds.xmlparse;

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

    public String getReturn_code() {
        return return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public String getAppid() {
        return appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public String getResult_code() {
        return result_code;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public String getTrade_type() {
        return trade_type;
    }

    @Override
    public String toString() {
        return "WxOrder{" +
                "return_code='" + return_code + '\'' +
                ", return_msg='" + return_msg + '\'' +
                ", appid='" + appid + '\'' +
                ", mch_id='" + mch_id + '\'' +
                ", nonce_str='" + nonce_str + '\'' +
                ", sign='" + sign + '\'' +
                ", result_code='" + result_code + '\'' +
                ", prepay_id='" + prepay_id + '\'' +
                ", trade_type='" + trade_type + '\'' +
                '}';
    }
}
