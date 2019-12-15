package com.xcbeyond.ctm.exception;

/**
 * 异常基类
 * @Auther: xcbeyond
 * @Date: 2019/12/2 16:36
 */
public class BaseException extends RuntimeException {

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
