package cn.lzg.springboot.background.config.exception;

import cn.lzg.springboot.background.utils.result.ApiCode;
import cn.lzg.springboot.background.utils.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理类
 * @author lzg
 * @Date 2017/2/13.
 */
@ControllerAdvice
class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        return new Result<String>(ApiCode.fail,e.getMessage());
    }
}