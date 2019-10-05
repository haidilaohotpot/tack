package cn.edu.mju.api.vo;


import cn.edu.mju.api.enums.ResultEnum;

import java.util.HashMap;

/**
 * 返回给前台的数据模板
 *
 * @author <a href="https://github.com/haidilaohotpot">cheng</a>
 * @since 1.0.0 2019/9/26
 */
public class ResultMap extends HashMap<String,Object> {


     private ResultMap(Object data, ResultEnum resultEnum){

        this.put("code",resultEnum.getCode());

        this.put("msg",resultEnum.getMsg());

        this.put("data", data);

    }

     private ResultMap(ResultEnum resultEnum){

        this.put("code",resultEnum.getCode());

        this.put("msg",resultEnum.getMsg());

    }


    private ResultMap(Object data, Object page, ResultEnum resultEnum){

        this.put("code",resultEnum.getCode());

        this.put("msg",resultEnum.getMsg());

        this.put("data", data);

        this.put("page",page);

    }


    private ResultMap() {

    }

    /**
     * 成功
     * @param data
     * @param resultEnum
     * @return
     */
    public static ResultMap ok(Object data, ResultEnum resultEnum){

        return new ResultMap(data, resultEnum);

    }


    /**
     * 成功
     * @param data
     * @param resultEnum
     * @return
     */
    public static ResultMap ok(Object data, Object page,ResultEnum resultEnum){

        return new ResultMap(data, page,resultEnum);

    }

    /**
     * 成功
     * @param resultEnum
     * @return
     */
    public static ResultMap ok(ResultEnum resultEnum){

        return new ResultMap(resultEnum);

    }

    /**
     * 失败
     * @param resultEnum
     * @return
     */
    public static ResultMap error(ResultEnum resultEnum){

        return new ResultMap(resultEnum);

    }



}
