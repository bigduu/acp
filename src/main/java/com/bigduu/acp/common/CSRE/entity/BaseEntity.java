package com.bigduu.acp.common.CSRE.entity;

import lombok.Data;
import org.springframework.data.annotation.Version;


/**
 * @author mugeng.du
 */
@Data
public class BaseEntity {
    @Version
    protected Long version;
    protected Boolean active;
    protected Boolean delete;
}
