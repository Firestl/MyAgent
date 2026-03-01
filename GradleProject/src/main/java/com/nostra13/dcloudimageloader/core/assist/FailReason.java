package com.nostra13.dcloudimageloader.core.assist;

/* JADX INFO: loaded from: classes2.dex */
public class FailReason {

    public enum FailType {
        IO_ERROR,
        DECODING_ERROR,
        NETWORK_DENIED,
        OUT_OF_MEMORY,
        UNKNOWN
    }

    public FailReason(FailType failType, Throwable th) {
    }
}
