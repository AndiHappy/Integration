package com.zlz.integration.db.mongodb.dao;

import com.zlz.integration.db.mongodb.domain.Customer;
import com.zlz.integration.task.IntegraTask;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface IntegraTaskRepository extends MongoRepository<IntegraTask, String> {

    public Optional<IntegraTask> findById(String s);

    public <S extends IntegraTask> S insert(S s);

}
