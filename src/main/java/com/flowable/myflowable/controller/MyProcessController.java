package com.flowable.myflowable.controller;

import org.flowable.engine.HistoryService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  流程实例的简单api
 * </p>
 * Copyright (C) 2020 Kingstar Winning, Inc. All rights reserved.
 *
 * @author Kun.F Create on 2020/6/28 21:24
 * @version 1.0
 */
@RestController
@RequestMapping("/flowable")
public class MyProcessController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyProcessController.class);
    private static final String SUCCESS_CODE="0";
    private static final String FAIL_CODE="0";

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ProcessEngine processEngine;
    /**
     * @description: 添加流程实例
     *
     * @author: Fang Kun
     * @param
     * @date: 2020/6/28 21:31
     * @return: java.lang.Object
     */
    @PostMapping("/addProcessInstance/{str}")
    public Object addProcessInstance(@PathVariable String str){
        LOGGER.debug("=====================>>>>>>>>>>>>>>"+str);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holidayRequest", str);
        return "提交成功,流程ID为：" + processInstance.getId();
    }

    /**
     * @description: 终止流程实例
     *
     * @author: Fang Kun
     * @param processInstanceId 流程实例id
     * @param stopReason 为啥？
     * @date: 2020/6/29 21:29
     * @return: java.lang.String
     */
    @PostMapping("/stopProcessInstanceById/{processInstanceId}/{stopReason}")
    public Object stopProcessInstanceById(@PathVariable String processInstanceId, @PathVariable String stopReason) {
        // ""这个参数本来可以写删除原因
        runtimeService.deleteProcessInstance(processInstanceId, stopReason);
        return SUCCESS_CODE;
    }

    /**
     * @description: 挂起流程实例
     *
     * @author: Fang Kun
     * @param processInstanceId 当前流程实例id
     * @date: 2020/6/29 21:32
     * @return:
     */
    @PostMapping("/handUpProcessInstance/{processInstanceId}")
    public String handUpProcessInstance(@PathVariable String processInstanceId) {
        runtimeService.suspendProcessInstanceById(processInstanceId);
        return "挂起流程成功...";
    }

    /**
     * @description: 恢复（唤醒）被挂起的流程实例
     *
     * @author: Fang Kun
     * @param processInstanceId 当前流程实例id
     * @date: 2020/6/29 21:32
     * @return:
     */
    @PostMapping("/activateProcessInstance/{processInstanceId}")
    public String activateProcessInstance(@PathVariable String processInstanceId) {
        runtimeService.activateProcessInstanceById(processInstanceId);
        return "恢复流程成功...";
    }

    /**
     * @description: 判断传入流程实例在运行中是否存在
     *
     * @author: Fang Kun
     * @param processInstanceId 当前流程实例id
     * @date: 2020/6/29 21:32
     * @return:
     */
    @PostMapping("/isExistProcIntRunning/{processInstanceId}")
    public Boolean isExistProcIntRunning(@PathVariable String processInstanceId) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        if (processInstance == null) {
            return false;
        }
        return true;
    }

    /**
     * 驳回流程实例
     *
     * @param taskId
     * @param targetTaskKey
     * @return
     */
    @PostMapping("rollbask")
    public String rollbaskTask(String taskId, String targetTaskKey) {
        Task currentTask = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (currentTask == null) {
            return "节点不存在";
        }
        List<String> key = new ArrayList<>();
        key.add(currentTask.getTaskDefinitionKey());


        runtimeService.createChangeActivityStateBuilder()
                .processInstanceId(currentTask.getProcessInstanceId())
                .moveActivityIdsToSingleActivityId(key, targetTaskKey)
                .changeState();
        return "驳回成功...";
    }

    /**
     * @description: 判断流程实例在历史记录中是否存在
     *
     * @author: Fang Kun
     * @param processInstanceId 当前流程实例id
     * @date: 2020/6/29 21:32
     * @return:
     */
    @PostMapping("/isExistProcInHistory/{processInstanceId}")
    public Boolean isExistProcInHistory(@PathVariable String processInstanceId) {
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        if (historicProcessInstance == null) {
            return false;
        }
        return true;
    }

    /**
     * @description: 我发起的流程实例列表
     *
     * @author: Fang Kun
     * @param userId 用户id
     * @date: 2020/6/29 21:32
     * @return:
     */
    @PostMapping("/myTasks/{userId}")
    public List<HistoricProcessInstance> getMyStartProcint(@PathVariable String userId) {
        List<HistoricProcessInstance> list = historyService
                .createHistoricProcessInstanceQuery()
                .startedBy(userId)
                .orderByProcessInstanceStartTime()
                .asc()
                .list();
        return list;
    }
}
