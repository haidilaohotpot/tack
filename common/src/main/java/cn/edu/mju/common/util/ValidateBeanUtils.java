package cn.edu.mju.common.util;

import cn.edu.mju.common.exception.RestException;

import java.util.Set;

/**
 *
 * 对bean进行校验
 * @author <a href="https://github.com/haidilaohotpot">cheng</a>
 * @since 1.0.0 2019/9/26
 */
public class ValidateBeanUtils {


    /**
     * 校验bean是否为空
     * @param bean
     * @return
     * @throws RestException
     */
    public static boolean isEmpty(Object bean)
            throws RestException {

        return null==bean;

    }




}
