package com.zlz.integration.aggregation_gateway;

import com.zlz.integration.aggregation_gateway.bean.RequestType;
import com.zlz.integration.engine.EngineContext;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;

public abstract class AbstractExecuteStep implements ExecuteStep {

    protected RequestType requestType;

    protected String stepId;

    protected JSONObject input = new JSONObject();

    protected JSONObject output = new JSONObject();

    protected static final String spit = ".";

    /**
     * 初始化执行前的参数
     * */
    protected void iniInput(EngineContext engineContext) {

        if(this.input.isEmpty() && engineContext.getPreStep() == null){
            this.addInputParas(engineContext.getGateConfig().getInput().toMap());
        }

        if(engineContext.getPreStep() != null){
            ExecuteStep pre = engineContext.getPreStep();
            JSONObject preInput = pre.getInput();
            JSONObject preOutput = pre.getOutput();

            for (Iterator<String> it = preInput.keys(); it.hasNext(); ) {
                String key = it.next();
                this.input.put(key,preInput.get(key));
            }

            for (Iterator<String> it = preOutput.keys(); it.hasNext(); ) {
                String key = it.next();
                this.input.put(key,preOutput.get(key));
            }
        }

    }

    public String getInputKey(String key, ExecuteStep step) {
        return step.getStepId()+spit+key;
    }

    public String getStepId() {
        return stepId;
    }

    public void setStepId(String stepId) {
        this.stepId = stepId;
    }

    public void addInputParas(Map<String,Object> paras){
        for (String key:
             paras.keySet()) {
            this.input.put(getInputKey(key,this),paras.get(key));
        }
    }

    public void addInputPara(String key,Object value){
        this.input.put(getInputKey(key,this),value);
    }

    @Override
    public JSONObject getInput() {
        return input;
    }

    public JSONObject getOutput() {
        return output;
    }

    public void setOutput(JSONObject output) {
        this.output = output;
    }
}
