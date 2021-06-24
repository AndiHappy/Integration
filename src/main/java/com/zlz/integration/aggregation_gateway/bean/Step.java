package com.zlz.integration.aggregation_gateway.bean;

/**
 * 准确描述每一步需要的信息
 * */
public class Step {

    private RequestType requestType;

    private String stepId;

    /**
     * 每一步对应的操作的索引
     * */
    private String proxyId;


    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public String getStepId() {
        return stepId;
    }

    public void setStepId(String stepId) {
        this.stepId = stepId;
    }

    public String getProxyId() {
        return proxyId;
    }

    public void setProxyId(String proxyId) {
        this.proxyId = proxyId;
    }
}
