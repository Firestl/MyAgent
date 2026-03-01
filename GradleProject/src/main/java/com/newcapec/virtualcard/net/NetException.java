package com.newcapec.virtualcard.net;

import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/* JADX INFO: loaded from: classes2.dex */
public class NetException extends Exception {
    public Exception mException;
    public int mResponseCode;

    public NetException(int i, Exception exc) {
        this.mResponseCode = i;
        this.mException = exc;
    }

    public NetException(int i, String str) {
        this(i, new Exception(str));
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        Exception exc = this.mException;
        if (exc != null) {
            return exc instanceof ConnectException ? "无法连接服务器，请稍后再试" : exc instanceof SocketTimeoutException ? "无法连接服务器(Timeout), 请检查网络连接" : exc instanceof NoRouteToHostException ? "无法连接服务器(NoRouteToHost), 请稍后再试" : exc instanceof UnknownHostException ? "无法连接服务器(UnknownHost), 请检查网络连接" : exc.getMessage();
        }
        return super.getMessage() + "(" + this.mResponseCode + ")";
    }

    public int getResponseCode() {
        return this.mResponseCode;
    }

    @Override // java.lang.Throwable
    public void printStackTrace() {
        Exception exc = this.mException;
        if (exc == null) {
            super.printStackTrace();
        } else {
            exc.printStackTrace();
        }
    }
}
