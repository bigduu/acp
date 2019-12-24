package com.bigduu.acp.common.service.impl;

import com.bigduu.acp.common.entity.BaseEntity;
import com.bigduu.acp.common.repository.BaseRepository;
import com.bigduu.acp.common.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {
    
    protected BaseRepository<T> repository;
    
    @Override
    public List<T> getAll() {
        return repository.findAll();
    }
    
    @Override
    public List<T> findAll() {
        return repository.findAll();
    }
    
    @Override
    public T addOne(T instance) {
        return repository.save(instance);
    }
    
    @Override
    public Optional<T> getOne(T instance) throws Exception {
        try {
            Field field = instance.getClass().getDeclaredField("id");
            field.setAccessible(true);
            String id = (String) field.get(instance);
            return getOneById(id);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            log.error("id 不能为空");
            throw new Exception("id 不能为空");
        }
    }
    
    @Override
    public Optional<T> getOneById(String id) {
        return repository.findById(id);
    }
    
    
    @Override
    public T update(T instance) {
        try {
            Field idField = instance.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            String id = (String)idField.get(instance);
            T old = getOneById(id).orElse(null);
            if (old != null){
                Field[] declaredFields = old.getClass().getDeclaredFields();
                for (Field declaredField : declaredFields) {
                    declaredField.setAccessible(true);
                    declaredField.set(old, declaredField.get(instance));
                }
                old.setActive(instance.getActive());
                return addOne(old);
            }else{
                throw new Exception("更新实体失败，该实体未存入");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
    
    @Override
    public void delete(T instance) {
        repository.delete(instance);
    }
    
    
    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
    
    @Override
    public Page<T> findByPageable(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
