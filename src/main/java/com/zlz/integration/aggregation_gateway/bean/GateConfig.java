package com.zlz.integration.aggregation_gateway.bean;

import com.zlz.integration.aggregation_gateway.ExecuteStep;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * JSON配置的整体的信息
 * */

@Setter
@Getter
public class GateConfig  implements Serializable {
    private JSONObject input;
    private ExecuteStep[] steps;
    private JSONObject output;
}
