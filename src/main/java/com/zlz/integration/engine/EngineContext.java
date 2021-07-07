package com.zlz.integration.engine;

import com.zlz.integration.aggregation_gateway.ExecuteStep;
import com.zlz.integration.aggregation_gateway.bean.GateConfig;

import java.util.LinkedList;

/**
 * 上下文环境
 * */
public class EngineContext {

    private GateConfig gateConfig;

    private EngineResult result;

    private LinkedList<ExecuteStep> executed = new LinkedList<ExecuteStep>();
    private ExecuteStep curStep;
    private ExecuteStep preStep;

    /**
     * 执行step
     * */
    public void execute(ExecuteStep step) {
        if(getCurStep() == null){
            setCurStep(step);
        }else{
            setPreStep(getCurStep());
            executed.add(getCurStep());
            setCurStep(step);
        }
        step.extcute(this);
    }

    public void setGateConfig(GateConfig config) {
        this.gateConfig =config;
    }

    public EngineResult getResult() {
        return result;
    }

    public void setResult(EngineResult result) {
        this.result = result;
    }

    public ExecuteStep getCurStep() {
        return curStep;
    }

    public void setCurStep(ExecuteStep curStep) {
        this.curStep = curStep;
    }

    public GateConfig getGateConfig() {
        return gateConfig;
    }

    public ExecuteStep getPreStep() {
        return preStep;
    }

    public void setPreStep(ExecuteStep preStep) {
        this.preStep = preStep;
    }
}
