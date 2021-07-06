package com.zlz.integration.loadresource;


import java.util.List;

public interface ResourceService {

    List<Resource> findAll();

    Resource insertByBook(Resource proxy);


    Resource update(Resource proxy);


    Resource delete(Long id);


    Resource findById(Long id);
}
