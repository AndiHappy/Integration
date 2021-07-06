package com.zlz.integration.loadresource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Resource {

    private String id;
    private String name;
    private long size;
    private String suffix;

    @Override
    public String toString() {
        return "Resource{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", suffix='" + suffix + '\'' +
                '}';
    }
}
