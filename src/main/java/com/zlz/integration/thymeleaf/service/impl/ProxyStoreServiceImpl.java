package com.zlz.integration.thymeleaf.service.impl;

import com.zlz.integration.thymeleaf.domain.Proxy;
import com.zlz.integration.thymeleaf.service.ProxyStoreService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProxyStoreServiceImpl implements ProxyStoreService {

    private static Map<Long, Proxy> BOOK_DB = new HashMap<>();

    @Override
    public List<Proxy> findAll() {
        return new ArrayList<>(BOOK_DB.values());
    }

    @Override
    public Proxy insertByBook(Proxy proxy) {
        proxy.setId(BOOK_DB.size() + 1L);
        BOOK_DB.put(proxy.getId(), proxy);
        return proxy;
    }

    @Override
    public Proxy update(Proxy proxy) {
        BOOK_DB.put(proxy.getId(), proxy);
        return proxy;
    }

    @Override
    public Proxy delete(Long id) {
        return BOOK_DB.remove(id);
    }

    @Override
    public Proxy findById(Long id) {
        return BOOK_DB.get(id);
    }
}
