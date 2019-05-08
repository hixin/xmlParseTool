package com.tool.cloudminds.xmlparse;


public class PlatformKey {
    private String AppKey;
    private String AppSecret;
    private String RedirectUrl;
    private String Extra;
    private String Enable;

    @Override
    public String toString() {
        return "PlatformKey{" +
                "AppKey='" + AppKey + '\'' +
                ", AppSecret='" + AppSecret + '\'' +
                ", RedirectUrl='" + RedirectUrl + '\'' +
                ", Extra='" + Extra + '\'' +
                ", Enable='" + Enable + '\'' +
                '}';
    }
}
