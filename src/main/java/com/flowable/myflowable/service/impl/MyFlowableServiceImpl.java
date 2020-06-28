package com.flowable.myflowable.service.impl;

import com.flowable.myflowable.dao.MyFlowableMapper;
import com.flowable.myflowable.entity.MyFlowableDO;
import com.flowable.myflowable.service.MyFlowableService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *
 * </p>
 * Copyright (C) 2020 Kingstar Winning, Inc. All rights reserved.
 *
 * @author Kun.F Create on 2020/6/28 20:12
 * @version 1.0
 */
@Service
public class MyFlowableServiceImpl implements MyFlowableService {

    @Resource
    private MyFlowableMapper myFlowableMapper;

    @Override
    public List<MyFlowableDO> listMyFlowables() {
        return myFlowableMapper.listMyFlowables();
    }
}
