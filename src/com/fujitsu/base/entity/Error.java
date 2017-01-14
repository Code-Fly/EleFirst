package com.fujitsu.base.entity;

/**
 * Created by Administrator on 2016/7/29.
 */
public abstract class Error {
    public static Integer SUCCESS = 0;
    public static Integer UNKNOW_EXCEPTION = -1;
    public static Integer SESSION_EXPIRED_EXCEPTION = 1;
    public static Integer CLASS_NOT_FOUND_EXCEPTION = 4;
    public static Integer SQL_EXCEPTION = 5;
    public static Integer CONNECTION_FAILED_EXCEPTION = 6;
    public static Integer FILE_UPLOAD_EXCEPTION = 7;
    public static Integer IO_EXCEPTION = 8;
    public static Integer UNSUPPORTED_ENCODING_EXCEPTION = 9;
    public static Integer MALFORMED_URL_EXCEPTION = 10;
    public static Integer JSON_EXCEPTION = 11;
    public static Integer PARSE_FILE_EXCEPTION = 13;
    public static Integer UNSUPPORTED_MIGRATION = 14;
    public static Integer TRANSCODER_EXCEPTION = 15;
    public static Integer PARSE_EXCEPTION = 16;
    public static Integer SCHEDULER_EXCEPTION = 17;
    public static Integer DATA_NOT_EXIST_EXCEPTION = 18;
    public static Integer API_RESPONSE_EXCEPTION = 19;
    public static Integer DUPLICATE_KEY_EXCEPTION = 20;
    public static Integer INVALID_DATA_EXCEPTION = 21;
    public static Integer ILLEGAL_ACCESS_EXCEPTION = 22;
    public static Integer INSTANTIANTION_EXCEPTION  = 23;
    public static Integer NO_SUCH_FIELD_EXCEPTION   = 24;
    public static Integer TIME_OUT_EXCEPTION   = 25;
}
