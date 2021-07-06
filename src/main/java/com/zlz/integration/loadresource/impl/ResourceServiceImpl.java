package com.zlz.integration.loadresource.impl;

import com.zlz.integration.Util.Constants;
import com.zlz.integration.loadresource.Resource;
import com.zlz.integration.loadresource.ResourceService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResourceServiceImpl  implements ResourceService {

    @SneakyThrows
    @Override
    public List<Resource> findAll() {
        List<Resource> resources = new ArrayList<>();
        File file = new File(Constants.fileResource);
        if(!file.exists()){
            file.createNewFile();
        }else{
          File[] files =   file.listFiles();
            for (int i = 0; i < files.length; i++) {
                File f = files[i];
                Resource r = new Resource();
                String name = f.getName();
                String[] tmp =  name.split("\\.");
                String suffix = tmp[tmp.length-1];
                r.setSuffix(suffix);
                r.setName(name.substring(0,name.length()-suffix.length()-1));
                r.setSize(file.length());
                r.setId(name);
                resources.add(r);
            }

        }

        return resources;
    }

    @Override
    public Resource insertByBook(Resource proxy) {
        return null;
    }

    @Override
    public Resource update(Resource proxy) {
        return null;
    }

    @Override
    public Resource delete(Long id) {
        return null;
    }

    @Override
    public Resource findById(Long id) {
        return null;
    }

}
