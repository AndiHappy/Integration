package com.zlz.integration.aggregation_gateway.bean;

import com.zlz.integration.Util.Constants;
import com.zlz.integration.aggregation_gateway.AbstractExecuteStep;
import com.zlz.integration.aggregation_gateway.ExecuteStep;
import com.zlz.integration.engine.EngineContext;
import com.zlz.integration.engine.EngineResult;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;

import java.util.ArrayList;
import java.util.List;

public class JsoupStep extends AbstractExecuteStep {

    @Override
    public EngineResult extcute(EngineContext engineContext) {
        iniInput(engineContext);
        ExecuteStep pre = engineContext.getPreStep();
        String key = getInputKey(Constants.HTML_DATA,pre);
        Document doc = (Document) input.get(key);

        String cssQuery = input.getString(getInputKey(Constants.JSOUP_SELECTOR,this));
        Elements elements = doc.select(cssQuery);
        List<String> pagas = new ArrayList<>();
        for (Element node:elements) {
            String listsUrl = node.absUrl("href");
            pagas.add(listsUrl);
        }
        this.output.put(Constants.JSOUP_PAGE_LIST,pagas);

        return EngineResult.INI;
    }




}
