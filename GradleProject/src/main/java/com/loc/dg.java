package com.loc;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;

/* JADX INFO: compiled from: ALLog.java */
/* JADX INFO: loaded from: classes2.dex */
public final class dg {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile dh f3726a;

    public static void a(Throwable th) {
        if (cv.f3710a) {
            Throwable cause = th;
            while (true) {
                if (cause == null) {
                    StringWriter stringWriter = new StringWriter();
                    PrintWriter printWriter = new PrintWriter(stringWriter);
                    th.printStackTrace(printWriter);
                    printWriter.flush();
                    stringWriter.toString();
                    break;
                }
                if (cause instanceof UnknownHostException) {
                    break;
                } else {
                    cause = cause.getCause();
                }
            }
            dh dhVar = f3726a;
        }
    }
}
