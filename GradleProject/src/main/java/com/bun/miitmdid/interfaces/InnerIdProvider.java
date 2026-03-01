package com.bun.miitmdid.interfaces;

/* JADX INFO: loaded from: classes.dex */
public interface InnerIdProvider extends IdSupplier {
    boolean isSync();

    void shutDown();

    void startAction(IIdentifierListener iIdentifierListener);
}
