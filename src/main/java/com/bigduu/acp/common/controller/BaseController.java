package com.bigduu.acp.common.controller;

import com.bigduu.acp.common.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
public class BaseController<T> {
    
    private BaseService<T> baseService;
    
    public BaseController(BaseService<T> baseService){
        this.baseService = baseService;
    }

    @GetMapping("/all")
    public List<T> getAll(){
        return baseService.getAll();
    }
    
    @GetMapping("/page")
    public Page<T> getAllByPageable(Pageable pageable){
        return baseService.findByPageable(pageable);
    }
    
    @GetMapping("/{id}")
    public T getById(@PathVariable String id){
       return baseService.getOneById(id).orElse(null);
    }
    
    @PostMapping
    public T addOne(@RequestBody T instance){
        return baseService.addOne(instance);
    }
    
    @PatchMapping
    public T updateOne(@RequestBody T instance){
        return baseService.update(instance);
    }
    
    @DeleteMapping
    public void deleteOne(@RequestBody T instance){
        baseService.delete(instance);
    }
    
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id){
        baseService.delete(id);
    }
    
    
}
