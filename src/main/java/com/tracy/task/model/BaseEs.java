package com.tracy.task.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * Created by lurenjie on 2017/1/8
 */
@Data
public class BaseEs {
    @Id
    protected String id;
}
