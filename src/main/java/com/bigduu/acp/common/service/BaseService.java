package com.bigduu.acp.common.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BaseService<T>{
    List<T> getAll();
    List<T> findAll();
    Page<T> findByPageable(Pageable pageable);
    T addOne(T instance);
    Optional<T> getOne(T instance) throws Exception;
    Optional<T> getOneById(String id);
    T update(T instance);
    void delete(T instance);
    void delete(String id);
}
