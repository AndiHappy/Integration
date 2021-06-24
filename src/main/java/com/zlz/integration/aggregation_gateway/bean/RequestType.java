package com.zlz.integration.aggregation_gateway.bean;

public enum RequestType{
    HTTP(1,"http");
    private  Integer id;
    private  String desc;

    RequestType(Integer id, String desc) {
        this.id=id;
        this.desc=desc;
    }
}
