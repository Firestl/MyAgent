package com.lzy.okgo.exception;

import com.taobao.weex.el.parse.Operators;
import supwisdom.aw0;
import supwisdom.dw0;

/* JADX INFO: loaded from: classes2.dex */
public class HttpException extends RuntimeException {
    public static final long serialVersionUID = 8773734741709178425L;
    public int code;
    public String message;
    public transient aw0<?> response;

    public HttpException(String str) {
        super(str);
    }

    public static HttpException COMMON(String str) {
        return new HttpException(str);
    }

    public static HttpException NET_ERROR() {
        return new HttpException("network error! http response code is 404 or 5xx!");
    }

    public static String getMessage(aw0<?> aw0Var) {
        dw0.a(aw0Var, "response == null");
        return "HTTP " + aw0Var.a() + Operators.SPACE_STR + aw0Var.d();
    }

    public int code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public aw0<?> response() {
        return this.response;
    }

    public HttpException(aw0<?> aw0Var) {
        super(getMessage(aw0Var));
        this.code = aw0Var.a();
        this.message = aw0Var.d();
        this.response = aw0Var;
    }
}
