package com.fujitsu.base.controller;

import com.fujitsu.base.entity.ErrorMsg;
import net.sf.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Barrie on 15/12/21.
 */
@Controller
@RequestMapping(value = "/exception")
public class ExceptionController extends BaseController {

    @RequestMapping(value = "/401")
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public String missingLogin() {
        logger.error("Missing login");
        ErrorMsg errMsg = new ErrorMsg(Integer.valueOf(HttpStatus.UNAUTHORIZED.toString()), "Missing login");
        return JSONObject.fromObject(errMsg).toString();
    }

    @RequestMapping(value = "/403")
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ResponseBody
    public String forbiddenDirectoryListing() {
        logger.error("Forbidden directory listing");
        ErrorMsg errMsg = new ErrorMsg(Integer.valueOf(HttpStatus.FORBIDDEN.toString()), "Forbidden directory listing");
        return JSONObject.fromObject(errMsg).toString();
    }

    @RequestMapping(value = "/404")
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMsg missingResource() {
        logger.error("Missing resource");
        return new ErrorMsg(Integer.valueOf(HttpStatus.NOT_FOUND.toString()), "Missing resource");
    }

    @RequestMapping(value = "/500")
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMsg uncaughtException(HttpServletRequest request) {
        // retrieve some useful information from the request
        // String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
        // Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        // String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");

        Exception ex = (Exception) request.getAttribute("javax.servlet.error.exception");

        logger.error("Uncaught exception", ex);
        return new ErrorMsg(Integer.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.toString()), ex.getMessage());
    }

    @RequestMapping(value = "/503")
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    @ResponseBody
    public ErrorMsg unsupportedServletMethod() {
        logger.error("Unsupported servlet method");
        return new ErrorMsg(Integer.valueOf(HttpStatus.SERVICE_UNAVAILABLE.toString()), "Unsupported servlet method");
    }


}
