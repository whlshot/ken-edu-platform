package com.ken.utils;

import com.ken.common.enums.ResultEnum;
import com.ken.common.model.ResultInfo;

/**
 * @author yhq
 * @date 2019/1/16
 */
public class ResultUtils {

    public static ResultInfo success() {
        ResultInfo resultInfo = new ResultInfo<>();
        resultInfo.setStatus(ResultEnum.SUCCESS.getCode());
        resultInfo.setInfo(ResultEnum.SUCCESS.getInfo());
        resultInfo.setSuccess(true);
        return resultInfo;
    }

    public static ResultInfo success(Object obj) {
        ResultInfo resultInfo = success();
        if (obj instanceof String) {
            resultInfo.setInfo(obj.toString());
        } else {
            resultInfo.setData(obj);
        }
        return resultInfo;
    }

    public static ResultInfo error() {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setStatus(ResultEnum.ERROR.getCode());
        resultInfo.setInfo(ResultEnum.ERROR.getInfo());
        resultInfo.setSuccess(false);
        return resultInfo;
    }

    public static ResultInfo error(String msg) {
        ResultInfo resultInfo = error();
        if (EmptyUtils.isNotEmpty(msg)) {
            resultInfo.setInfo(msg);
        }
        return resultInfo;
    }

    public static ResultInfo error(Integer code, String msg) {
        ResultInfo resultInfo = error();
        if (code != null) {
            resultInfo.setStatus(code);
        }
        if (EmptyUtils.isNotEmpty(msg)) {
            resultInfo.setInfo(msg);
        }
        return resultInfo;
    }


}
