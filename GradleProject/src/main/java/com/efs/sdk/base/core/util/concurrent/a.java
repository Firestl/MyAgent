package com.efs.sdk.base.core.util.concurrent;

import android.os.HandlerThread;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static HandlerThread f1864a;

    static {
        HandlerThread handlerThread = new HandlerThread("efs-base", 10);
        f1864a = handlerThread;
        handlerThread.start();
    }
}
