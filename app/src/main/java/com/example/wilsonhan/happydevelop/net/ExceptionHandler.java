package com.example.wilsonhan.happydevelop.net;

import android.net.ParseException;

import com.google.gson.JsonParseException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import org.json.JSONException;

import java.net.ConnectException;


public class ExceptionHandler {

    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;


    public static ResponseThrowable handleException(Throwable e) {
        ResponseThrowable responseThrowable;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            responseThrowable = new ResponseThrowable(e, ERROR.HTTP_ERROR);
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    responseThrowable.code = httpException.code();
                    responseThrowable.message = "网络错误";
                    break;
            }
            return responseThrowable;
        } else if (e instanceof ServerException) {
            ServerException resultException = (ServerException) e;
            responseThrowable = new ResponseThrowable(resultException, resultException.code);
            responseThrowable.message = resultException.getMessage();
            return responseThrowable;
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
            responseThrowable = new ResponseThrowable(e, ERROR.PARSE_ERROR);
            responseThrowable.message = "解析错误";
            return responseThrowable;
        } else if (e instanceof ConnectException) {
            responseThrowable = new ResponseThrowable(e, ERROR.NETWORD_ERROR);
            responseThrowable.message = "连接失败";
            return responseThrowable;
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            responseThrowable = new ResponseThrowable(e, ERROR.SSL_ERROR);
            responseThrowable.message = "证书验证失败";
            return responseThrowable;
        } else {
            responseThrowable = new ResponseThrowable(e, ERROR.UNKNOWN);
            responseThrowable.message = "未知错误";
            return responseThrowable;
        }
    }

    public static class ResponseThrowable extends Exception {
        public int code;
        public String message;

        public ResponseThrowable(Throwable throwable, int code) {
            super(throwable);
            this.code = code;
        }
    }

    /**
     * 约定异常
     */
    class ERROR {
        /**
         * 未知错误
         */
        public static final int UNKNOWN = 1000;
        /**
         * 解析错误
         */
        public static final int PARSE_ERROR = 1001;
        /**
         * 网络错误
         */
        public static final int NETWORD_ERROR = 1002;
        /**
         * 协议出错
         */
        public static final int HTTP_ERROR = 1003;

        /**
         * 证书出错
         */
        public static final int SSL_ERROR = 1005;

        /**
         * 连接超时
         */
        public static final int TIMEOUT_ERROR = 1006;
    }

    public class ServerException extends RuntimeException {
        public int code;
        public String message;
    }


}
