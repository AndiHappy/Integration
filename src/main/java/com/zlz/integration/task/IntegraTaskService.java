package com.zlz.integration.task;


import com.zlz.integration.loadresource.Resource;

import java.util.List;

public interface IntegraTaskService {

    List<IntegraTask> findAll();

    IntegraTask insertByBook(IntegraTask proxy);


    IntegraTask update(IntegraTask proxy);


    IntegraTask delete(Long id);


    IntegraTask findById(Long id);
}
