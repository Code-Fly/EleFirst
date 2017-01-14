/**
 *
 */
package com.fujitsu.base.entity;


/**
 * @author Barrie
 */
public class ErrorMsg extends BaseEntity {
    private Integer errcode;
    private String errmsg;
    private Object data;

    public ErrorMsg() {
    }

    public ErrorMsg(Integer errcode, String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    public ErrorMsg(Integer errcode, String errmsg, Object data) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.data = data;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
