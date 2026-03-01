package com.qiniu.android.dns;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public class DnsException extends IOException {
    public DnsException(String str, String str2) {
        super(str + ": " + str2);
    }
}
