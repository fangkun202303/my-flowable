package com.flowable.myflowable.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *  测试使用的实体类
 * </p>
 * Copyright (C) 2020 Kingstar Winning, Inc. All rights reserved.
 *
 * @author Kun.F Create on 2020/6/28 19:47
 * @version 1.0
 */
@Data
public class MyFlowableDO implements Serializable {

    protected String rid;
    protected String moduleId;
    protected String content;
    protected Date crteTime;
    protected Date updtTime;

}
