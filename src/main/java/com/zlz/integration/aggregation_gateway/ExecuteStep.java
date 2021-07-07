package com.zlz.integration.aggregation_gateway;

import com.zlz.integration.engine.EngineContext;
import com.zlz.integration.engine.EngineResult;
import org.json.JSONObject;

import java.util.Map;

public interface ExecuteStep {

    public EngineResult extcute(EngineContext engineContext);

    public JSONObject getInput();

    public JSONObject getOutput();

    public void addInputParas(Map<String,Object> paras);

    public void addInputPara(String key,Object value);

    public String getStepId();

}
