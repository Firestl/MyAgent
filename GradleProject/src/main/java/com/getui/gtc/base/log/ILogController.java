package com.getui.gtc.base.log;

/* JADX INFO: loaded from: classes.dex */
public interface ILogController {
    boolean isLoggable(int i, String str);

    void log(int i, String str, String str2, Throwable th);
}
