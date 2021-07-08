package com.zlz.ebook;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageHtml extends BaseHtml {

    private String pageTitle;

    public PageHtml(String url) {
        super(url);
    }
}
