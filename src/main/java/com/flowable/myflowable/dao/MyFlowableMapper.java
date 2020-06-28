package com.flowable.myflowable.dao;

import com.flowable.myflowable.entity.MyFlowableDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  测试dao
 * </p>
 * Copyright (C) 2020 Kingstar Winning, Inc. All rights reserved.
 *
 * @author Kun.F Create on 2020/6/28 20:04
 * @version 1.0
 */
@Mapper
public interface MyFlowableMapper {

    @Select("select * from my_flowable_t")
    public List<MyFlowableDO> listMyFlowables();
}
