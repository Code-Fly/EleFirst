/**
 * Copyright: Copyright (c) 2016 Sinovatio
 * Company:中新赛克科技有限责任公司
 *
 * @ClassName: GeneralMessage.java
 * @Description: TODO
 * 
 * @author jinlu
 * @date 2016年3月7日 上午10:48:37
 * @version V1.0
 */
package com.elefirst.base.entity;

public class GeneralMessage {
    public static class Result {
        public static final String FAILED  = "FAILED";
        public static final String SUCCESS = "SUCCESS";
        public static final String LOOP    = "2";
        public static final String OTHER   = "3";
    }
    
    private String flag;
    
    private String msg;
    
    private String keyid;
    
    public GeneralMessage() {
        
    }
    public GeneralMessage(String flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }
    
    public GeneralMessage(String flag, String msg,String keyid) {
        this.flag = flag;
        this.msg = msg;
        this.keyid = keyid;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
	public String getKeyid() {
		return keyid;
	}
	public void setKeyid(String keyid) {
		this.keyid = keyid;
	}
    
}
