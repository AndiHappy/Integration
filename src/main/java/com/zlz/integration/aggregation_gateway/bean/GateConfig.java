package com.zlz.integration.aggregation_gateway.bean;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * JSON配置的整体的信息
 * */
public class GateConfig  implements Serializable {
    private JSONObject input;
    private Step[] steps;
    private JSONObject output;
}
