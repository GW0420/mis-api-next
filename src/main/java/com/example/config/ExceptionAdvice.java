package com.example.config;


import cn.dev33.satoken.stp.StpUtil;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.result.R;
import io.minio.errors.MinioException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackages={"com.example.mis.controller"})
public class ExceptionAdvice {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionAdvice.class);

    // 校验异常统一处理
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public R validExceptionHandler(BindException e) {
        R result = new R();
        LOG.warn("参数校验失败：{}", e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        result.setCode(ResultCodeEnum.SUCCESS_VALIDATE.code);
        result.setData(false);
        result.setMsg(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return result;
    }

    //统一异常处理@ExceptionHandler,主要用于Exception
    @ExceptionHandler(Exception.class)
    @ResponseBody//返回json串
    public R error(HttpServletRequest request, Exception e) throws MinioException {
        long activityTimeout = StpUtil.getTokenActivityTimeout();
        R result = new R();
        if(activityTimeout < 0) {
            LOG.error("异常错误:",e);
            result.setCode(ResultCodeEnum.TOKEN_TIMEOUT.code);
            result.setData(e.getMessage());
            result.setMsg(e.getMessage());
            return result;
        }else {
            LOG.error("异常错误:",e);
            result.setCode(ResultCodeEnum.SYSTEM_ERROR.code);
            result.setData(String.valueOf(e));
            result.setMsg(ResultCodeEnum.SYSTEM_ERROR.msg);
            return result;

        }

    }

}

