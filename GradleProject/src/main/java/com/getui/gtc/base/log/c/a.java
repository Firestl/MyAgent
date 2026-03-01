package com.getui.gtc.base.log.c;

import android.os.Process;
import android.text.TextUtils;
import com.getui.gtc.base.log.ILogDestination;
import com.getui.gtc.base.log.ILogFormatter;
import com.getui.gtc.base.util.CommonUtil;
import com.taobao.weex.el.parse.Operators;
import com.tencent.qphone.base.util.QLog;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class a implements ILogFormatter {
    public final ILogDestination c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f2136e;
    public final SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f2135a = "";
    public int b = 8;

    public a(ILogDestination iLogDestination) {
        this.f2136e = "";
        this.c = (ILogDestination) com.getui.gtc.base.log.e.a.a(iLogDestination);
        this.f2136e = CommonUtil.getProcessName();
    }

    private String a() {
        try {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[this.b];
            String className = stackTraceElement.getClassName();
            return String.format("%s.%s", className.substring(className.lastIndexOf(Operators.DOT_STR) + 1), stackTraceElement.getMethodName());
        } catch (Throwable unused) {
            return "";
        }
    }

    private String a(String str, Throwable th) {
        if (th != null && str != null) {
            str = str + " : " + a(th);
        }
        if (th != null && str == null) {
            str = a(th);
        }
        if (TextUtils.isEmpty(str)) {
            str = "Empty/NULL log message";
        }
        String strTrim = str.trim();
        if (strTrim.startsWith(Operators.BLOCK_START_STR) && strTrim.endsWith(Operators.BLOCK_END_STR)) {
            try {
                strTrim = new JSONObject(strTrim).toString(2);
            } catch (Throwable unused) {
            }
        }
        if (strTrim.startsWith(Operators.ARRAY_START_STR) && strTrim.endsWith(Operators.ARRAY_END_STR)) {
            try {
                strTrim = new JSONArray(strTrim).toString(2);
            } catch (Throwable unused2) {
            }
        }
        String strB = b();
        if (th != null) {
            return strTrim;
        }
        return strTrim + Operators.SPACE_STR + strB;
    }

    public static String a(Throwable th) {
        if (th == null) {
            return "";
        }
        for (Throwable cause = th; cause != null; cause = cause.getCause()) {
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintWriter printWriter = new PrintWriter((OutputStream) byteArrayOutputStream, false);
        th.printStackTrace(printWriter);
        printWriter.flush();
        return byteArrayOutputStream.toString();
    }

    private String b() {
        try {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[this.b];
            String className = stackTraceElement.getClassName();
            return String.format("(%s:%d)", className.substring(className.lastIndexOf(Operators.DOT_STR) + 1) + ".java", Integer.valueOf(stackTraceElement.getLineNumber()));
        } catch (Throwable unused) {
            return "";
        }
    }

    @Override // com.getui.gtc.base.log.ILogFormatter
    public final void log(int i, String str, String str2, Throwable th) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.d.format(new Date()));
        sb.append(Operators.SPACE_STR);
        sb.append(Process.myPid());
        sb.append("/");
        sb.append(this.f2136e);
        sb.append(Operators.SPACE_STR);
        sb.append(i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? Operators.CONDITION_IF_STRING : QLog.TAG_REPORTLEVEL_USER : QLog.TAG_REPORTLEVEL_COLORUSER : "I" : QLog.TAG_REPORTLEVEL_DEVELOPER : "V");
        sb.append("/");
        String strA = a();
        String str3 = TextUtils.isEmpty(str) ? this.f2135a : str;
        if (!TextUtils.isEmpty(str3)) {
            strA = str3 + "-" + strA;
        }
        sb.append(strA);
        sb.append(": ");
        sb.append(a(str2, th));
        String string = sb.toString();
        if (!string.endsWith("\n")) {
            string = string + "\n";
        }
        this.c.log(i, str, string);
    }
}
