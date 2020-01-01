package com.bigduu.acp.common.baseprocesshandler.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Version;


/**
 * @author mugeng.du
 */
@Data
@NoArgsConstructor
public class BaseEntity {
    @Version
    protected Long version;
    protected Boolean active;
    protected Boolean delete;
}
