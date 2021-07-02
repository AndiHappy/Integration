package com.zlz.integration.thymeleaf.service;

import com.zlz.integration.thymeleaf.domain.Proxy;

import java.util.List;

public interface ProxyStoreService {

    List<Proxy> findAll();

    Proxy insertByBook(Proxy proxy);


    Proxy update(Proxy proxy);


    Proxy delete(Long id);


    Proxy findById(Long id);
}
