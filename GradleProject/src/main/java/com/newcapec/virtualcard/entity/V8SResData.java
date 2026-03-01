package com.newcapec.virtualcard.entity;

import com.alibaba.fastjson.JSON;

/* JADX INFO: loaded from: classes2.dex */
public class V8SResData extends JsonData {
    public String message;
    public String resultData;
    public boolean success;

    public String getMessage() {
        return this.message;
    }

    public String getResultData() {
        return this.resultData;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setResultData(String str) {
        this.resultData = str;
    }

    public void setSuccess(boolean z) {
        this.success = z;
    }

    @Override // com.newcapec.virtualcard.entity.JsonData
    public String toString() {
        return JSON.toJSONString(this);
    }
}
