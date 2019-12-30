package com.bigduu.acp.service.impl;

import com.bigduu.acp.common.CSRE.service.impl.BaseServiceImpl;
import com.bigduu.acp.entity.Operation;
import com.bigduu.acp.repository.OperationRepository;
import com.bigduu.acp.service.OperationService;
import org.springframework.stereotype.Service;

/**
 * @author mugeng.du
 */
@Service
public class OperationServiceImpl extends BaseServiceImpl<Operation> implements OperationService {
    
    private final OperationRepository operationRepository;
    
    public OperationServiceImpl(OperationRepository operationRepository) {
        super(operationRepository);
        this.operationRepository = operationRepository;
    }
    
}
