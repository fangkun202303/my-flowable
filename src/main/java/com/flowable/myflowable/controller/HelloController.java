package com.flowable.myflowable.controller;

import com.flowable.myflowable.entity.MyFlowableDO;
import com.flowable.myflowable.service.MyFlowableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *
 * </p>
 * Copyright (C) 2020 Kingstar Winning, Inc. All rights reserved.
 *
 * @author Kun.F Create on 2020/6/28 19:40
 * @version 1.0
 */
@RestController
public class HelloController {

    @Autowired
    private MyFlowableService myFlowableService;

    @PostMapping("listMyFlowables")
    public List<MyFlowableDO> listMyFlowables(){
        return myFlowableService.listMyFlowables();
    }
}
