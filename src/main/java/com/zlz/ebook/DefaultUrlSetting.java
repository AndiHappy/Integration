package com.zlz.ebook;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DefaultUrlSetting extends UrlSetting {
    public DefaultUrlSetting(){
        this.setEtitle("#info > h1");
        this.setPageLink("#list > dl > dd a");
        this.setContentSelect("#content");
    }

}
