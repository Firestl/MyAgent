package com.newcapec.virtualcard.entity;

/* JADX INFO: loaded from: classes2.dex */
public class ECardResData<T> extends JsonData {
    public String message;
    public T resultData;
    public boolean success;

    public String getMessage() {
        return this.message;
    }

    public T getResultData() {
        return this.resultData;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setResultData(T t) {
        this.resultData = t;
    }

    public void setSuccess(boolean z) {
        this.success = z;
    }
}
