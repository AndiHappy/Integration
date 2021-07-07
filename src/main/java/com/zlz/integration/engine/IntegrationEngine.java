package com.zlz.integration.engine;

import com.zlz.integration.Util.Constants;
import com.zlz.integration.aggregation_gateway.ExecuteStep;
import com.zlz.integration.aggregation_gateway.bean.GateConfig;
import com.zlz.integration.aggregation_gateway.bean.HtmlStep;
import com.zlz.integration.aggregation_gateway.bean.JsoupStep;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class IntegrationEngine {

    public IntegrationEngine(){
        ini();
    }

    /**
     * 初始化
     * */
    private void ini() {
    }

    /**
     * 顺序执行
     *  执行的动作，全部的放在Step中
     * */
    public static EngineResult execute(GateConfig config){
        EngineContext context = new EngineContext();
        context.setGateConfig(config);
        context.setResult(EngineResult.INI);
        ExecuteStep[] steps = config.getSteps();
        if(steps != null && steps.length > 0 ){
            for (int i = 0; i < steps.length; i++) {
                ExecuteStep step = steps[i];
                context.execute(step);
            }
        }
        return context.getResult();
    }

    public static void main(String[] args) {
        System.out.println("keep Happy boy");
        GateConfig config = new GateConfig();
        JSONObject input = new JSONObject();
        input.put(Constants.HTML_URL,"http://www.520xsb.com/read/10981/index.html");
        config.setInput(input);
        ExecuteStep[] steps = new ExecuteStep[2];

        HtmlStep list = new HtmlStep();
        list.setStepId("listpage");
        list.addInputParas(input.toMap());

        JsoupStep jsoupStep = new JsoupStep();
        jsoupStep.setStepId("jsoupStep");
        jsoupStep.addInputPara(Constants.JSOUP_SELECTOR,"table#at a");

        steps[0] =list;
        steps[1] = jsoupStep;
        config.setSteps(steps);
        execute(config);


    }
}
