package com.zlz.ebook;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UrlSetting {
    private String host;
    private String etitle;
    private String pageLink;
    private String contentSelect;

    @Override
    public String toString() {
        return "UrlSetting{" +
                "host='" + host + '\'' +
                ", eTitle='" + etitle + '\'' +
                ", pageLink='" + pageLink + '\'' +
                ", contentSelect='" + contentSelect + '\'' +
                '}';
    }
}
