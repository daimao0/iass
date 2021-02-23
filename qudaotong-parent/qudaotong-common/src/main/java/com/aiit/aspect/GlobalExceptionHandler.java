package com.aiit.aspect;

import com.aiit.api.CommonResult;
import com.baomidou.mybatisplus.extension.api.IErrorCode;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 呆毛
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 1 内部API调用的异常处理
     */
    @ExceptionHandler(value = ApiException.class)
    public CommonResult handlerApiException(ApiException exception){
        IErrorCode errorCode = exception.getErrorCode();
        if(errorCode!=null){
            return CommonResult.failed(errorCode.toString()) ;
        }
        return CommonResult.failed(exception.getMessage()) ;
    }

    /**
     * 2 方法参数校验失败的异常
     * MethodArgumentNotValidException
     *
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        BindingResult bindingResult = exception.getBindingResult();
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError!=null){
                return CommonResult.failed(fieldError.getField()+fieldError.getDefaultMessage()) ;
            }
        }
        return CommonResult.failed(exception.getMessage()) ;
    }

    /**
     * 3 对象内部使用Validate 没有校验成功的异常
     */
    @ExceptionHandler(BindException.class)
    public CommonResult handlerBindException(BindException bindException){
        BindingResult bindingResult = bindException.getBindingResult();
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError!=null){
                return CommonResult.failed(fieldError.getField()+fieldError.getDefaultMessage()) ;
            }
        }
        return CommonResult.failed(bindException.getMessage()) ;
    }

}
