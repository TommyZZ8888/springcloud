package com.vren.common.common.exception;

import lombok.Getter;

/**
 * @ClassName:ErrorException
 * @Description:
 * @Author: vren
 * @Date: 2021/12/13 10:00
 */
@Getter
public class ErrorException extends BaseException {

    private static final long serialVersionUID = 2307168092500141151L;
    private final String message;

    public ErrorException(String msg) {
        super(msg);
        this.message = msg;
    }
}
