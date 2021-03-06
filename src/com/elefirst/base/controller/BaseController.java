package com.elefirst.base.controller;

import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.base.exception.ConnectionFailedException;
import com.elefirst.base.utils.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 */
public abstract class BaseController extends Const {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(ConnectionFailedException.class)
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    @ResponseBody
    public ErrorMsg handleConnectionFailedException(ConnectionFailedException ex) {
        logger.error("Connection Failed", ex);
        return new ErrorMsg(Integer.valueOf(HttpStatus.SERVICE_UNAVAILABLE.toString()), "Connection Failed");
    }
}
