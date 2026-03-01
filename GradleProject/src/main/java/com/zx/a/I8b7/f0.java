package com.zx.a.I8b7;

import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class f0 implements a0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public z f6216a;
    public String b = "";
    public int c = 8;

    public f0(z zVar) {
        this.f6216a = (z) n1.a(zVar);
    }

    @Override // com.zx.a.I8b7.a0
    public void a(int i, String str, String str2, Throwable th) {
        String str3;
        String str4 = "";
        try {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[this.c];
            String className = stackTraceElement.getClassName();
            str3 = String.format("%s.%s", className.substring(className.lastIndexOf(Operators.DOT_STR) + 1), stackTraceElement.getMethodName());
        } catch (Throwable unused) {
            str3 = "";
        }
        if (TextUtils.isEmpty(str)) {
            str = this.b;
        }
        if (!TextUtils.isEmpty(str)) {
            str3 = str + "-" + str3;
        }
        if (th != null && str2 != null) {
            str2 = str2 + " : " + a(th);
        }
        if (th != null && str2 == null) {
            str2 = a(th);
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = "Empty/NULL log message";
        }
        String strTrim = str2.trim();
        if (strTrim.startsWith(Operators.BLOCK_START_STR) && strTrim.endsWith(Operators.BLOCK_END_STR)) {
            try {
                strTrim = new JSONObject(strTrim).toString(2);
            } catch (Throwable unused2) {
            }
        }
        if (strTrim.startsWith(Operators.ARRAY_START_STR) && strTrim.endsWith(Operators.ARRAY_END_STR)) {
            try {
                strTrim = new JSONArray(strTrim).toString(2);
            } catch (Throwable unused3) {
            }
        }
        try {
            StackTraceElement stackTraceElement2 = Thread.currentThread().getStackTrace()[this.c];
            String className2 = stackTraceElement2.getClassName();
            str4 = String.format("(%s:%d)", className2.substring(className2.lastIndexOf(Operators.DOT_STR) + 1) + ".java", Integer.valueOf(stackTraceElement2.getLineNumber()));
        } catch (Throwable unused4) {
        }
        if (th == null) {
            strTrim = strTrim + Operators.SPACE_STR + str4;
        }
        this.f6216a.a(i, str3, strTrim);
    }

    public static String a(Throwable th) {
        for (Throwable cause = th; cause != null; cause = cause.getCause()) {
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintWriter printWriter = new PrintWriter((OutputStream) byteArrayOutputStream, false);
        th.printStackTrace(printWriter);
        printWriter.flush();
        return byteArrayOutputStream.toString();
    }
}
