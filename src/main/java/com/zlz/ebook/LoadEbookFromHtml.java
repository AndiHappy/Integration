package com.zlz.ebook;

import com.zlz.integration.Util.Constants;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LoadEbookFromHtml {


    public static void main(String[] args) throws IOException {

        System.out.println("keep Happy boy");
        String url = "https://www.biquge5200.cc/1_1224/";
        UrlSetting setting = FileUtil.instance().getProperties(new URL(url).getHost());
        if(setting == null) return;

        String object = new JSONObject(setting).toString();
        System.out.println(object);

        BaseHtml page = new BaseHtml(url);
        LoadConditionPoolUtil.waitLoadDoc(page, 1000);
        Document document = page.getDoc();


        Elements title = document.select(setting.getEtitle());
        String bookT = title.text();


        List<PageHtml> pages = new ArrayList<>();
        Elements elements = document.select(setting.getPageLink());
        if (elements.size() > 10) {
            for (Element e :
                    elements) {
                String pageUrl = e.absUrl("href");
                PageHtml p = new PageHtml(pageUrl);
                p.setPageTitle(e.text());
                pages.add(p);
            }
        }


        File file = new File(Constants.fileResource + "/" + bookT + ".txt");
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();


        for (int i = 0; i < pages.size(); i++) {
            PageHtml contentPage = pages.get(i);
            LoadConditionPoolUtil.waitLoadDoc(contentPage, 100);
            String pageTitle = contentPage.getPageTitle();
            System.out.println(pageTitle);
            FileUtils.write(file, pageTitle, "UTF-8", true);

            Document pageDoc = contentPage.getDoc();
            Elements pageContent = pageDoc.select(setting.getContentSelect());

            String content = pageContent.text();
            System.out.println(content);

            FileUtils.write(file, content, "UTF-8", true);
        }
    }
}
