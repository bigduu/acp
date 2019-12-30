package com.bigduu.acp.entity;

import com.bigduu.acp.common.CSRE.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author mugeng.du
 */
@EqualsAndHashCode(callSuper = true)
@Document
@Data
public class Operation extends BaseEntity {
    @Id
    private String id;
    private String user;
    private String path;
    private String method;
    private int state;
    private long time;
}
