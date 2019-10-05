package cn.edu.mju.api.controller;

import cn.edu.mju.api.enums.ResultEnum;
import cn.edu.mju.api.vo.ResultMap;
import cn.edu.mju.common.entity.Log;
import cn.edu.mju.common.exception.RestException;
import cn.edu.mju.common.util.ValidateBeanUtils;
import cn.edu.mju.manager.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="https://github.com/haidilaohotpot">cheng</a>
 * @since 1.0.0 2019/9/27
 */
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;


    /**
     * 由于日志较少 不做分页和归类
     * 一次性查询全部的日志信息
     * @return
     */
    @GetMapping("/list")
    public ResultMap list() {

        List<Log> logList = logService.list();

        return ResultMap.ok(logList, ResultEnum.SUCCESS);
    }


    /**
     * 根据id更新
     * @param log
     * @return
     */
    @PostMapping("/update")
    public ResultMap update(@RequestBody Log log){

        if(ValidateBeanUtils.isEmpty(log) || null == log.getLogId()){
            throw new RestException();
        }

        logService.updateById(log);

        return ResultMap.ok(ResultEnum.SUCCESS);
    }



    /**
     * 保存
     */
    @RequestMapping("/save")
    public ResultMap save(@RequestBody Log log) {

        if(ValidateBeanUtils.isEmpty(log)){
            throw new RestException();
        }
        logService.save(log);

        return ResultMap.ok(ResultEnum.SUCCESS);
    }


    /**
     * 批量化删除
     */
    @RequestMapping("/delete")
    public ResultMap delete(@RequestBody Integer[] ids) {

        logService.removeByIds(Arrays.asList(ids));

        return ResultMap.ok(ResultEnum.SUCCESS);
    }



}
