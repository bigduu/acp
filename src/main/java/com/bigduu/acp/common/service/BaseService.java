package com.bigduu.acp.common.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @author mugeng.du
 */
public interface BaseService<T>{
    /**
     * 查找全部的实体
     * @return allList
     */
    List<T> getAll();
    
    /**
     * 查找全部的实体 同名
     * @return 公共的查找全部
     */
    List<T> findAll();
    
    /**
     * 分页查找
     * @param pageable 分页对象
     * @return 返回分页
     */
    Page<T> findByPageable(Pageable pageable);
    
    /**
     * 保存一个对象
     * @param instance 需要保存的对象
     * @return 返回从数据库读出来的数据
     */
    T addOne(T instance);
    
    /**
     *  根据传入的实体获得对象，这种一般用作只知道ID的
     *  需要获得详细的对象时使用
     * @param instance 必须包含Id的对象
     * @return Optional<T>详细信息的optional包裹的对象
     * @throws Exception 用到了反射
     */
    Optional<T> getOne(T instance) throws Exception;
    
    /**
     * 根据Id获得实体
     * @param id id
     * @return Optional<T>
     */
    Optional<T> getOneById(String id);
    
    /**
     * @param instance
     * @return
     */
    T update(T instance);
    
    /**
     * @param instance
     */
    void delete(T instance);
    
    /**
     * @param id
     */
    void delete(String id);
    
}
