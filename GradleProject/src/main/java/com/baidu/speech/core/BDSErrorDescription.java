package com.baidu.speech.core;

/* JADX INFO: loaded from: classes.dex */
public class BDSErrorDescription {
    public int errorCode;
    public String errorDescription;
    public int errorDomain;

    public int getDetailCode() {
        return (this.errorDomain << 16) | (this.errorCode & 65535);
    }
}
