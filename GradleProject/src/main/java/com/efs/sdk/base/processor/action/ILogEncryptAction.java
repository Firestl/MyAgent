package com.efs.sdk.base.processor.action;

/* JADX INFO: loaded from: classes.dex */
public interface ILogEncryptAction {
    byte[] decrypt(String str, byte[] bArr);

    byte[] encrypt(String str, byte[] bArr);

    int getDeVal();
}
