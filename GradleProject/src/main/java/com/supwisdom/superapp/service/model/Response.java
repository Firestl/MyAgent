package com.supwisdom.superapp.service.model;

/* JADX INFO: loaded from: classes2.dex */
public class Response<T> {
    public static int CODE_FAIL = -1;
    public static int CODE_SUCCESS;
    public boolean acknowleged;
    public int code = CODE_FAIL;
    public T data;
    public String idToken;
    public String message;
}
