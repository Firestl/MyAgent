package io.dcloud.feature.pdr;

import android.util.Log;
import io.dcloud.common.adapter.util.Logger;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public class b extends Logger {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f6585a;
    public static File b;
    public static Boolean c = Boolean.TRUE;

    public static void WriteLogToSDcard(String str, String str2, String str3) throws Throwable {
        String strGenerateLog = Logger.generateLog(str, str2, str3);
        if (b == null || strGenerateLog == null) {
            return;
        }
        FileOutputStream fileOutputStream = null;
        try {
            try {
                try {
                    FileOutputStream fileOutputStream2 = new FileOutputStream(b, true);
                    try {
                        fileOutputStream2.write(strGenerateLog.getBytes());
                        fileOutputStream2.flush();
                        fileOutputStream2.close();
                    } catch (Exception e2) {
                        e = e2;
                        fileOutputStream = fileOutputStream2;
                        e.printStackTrace();
                        if (fileOutputStream == null) {
                        } else {
                            fileOutputStream.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e4) {
                e = e4;
            }
        } catch (IOException e5) {
            e5.printStackTrace();
        }
    }

    public static void a(String str) {
        if (c.booleanValue()) {
            f6585a = str;
            storeLogToSDcard();
            c = Boolean.FALSE;
        }
    }

    public static void d(String str, String str2) throws Throwable {
        Log.d(str, str2);
        WriteLogToSDcard(Logger.D, str, str2);
    }

    public static void e(String str, String str2) throws Throwable {
        Log.e(str, str2);
        WriteLogToSDcard(Logger.E, str, str2);
    }

    public static void i(String str, String str2) throws Throwable {
        Log.i(str, str2);
        WriteLogToSDcard(Logger.I, str, str2);
    }

    public static void storeLogToSDcard() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(f6585a);
        stringBuffer.append(File.separatorChar);
        stringBuffer.append(Logger.generateTimeStamp(Boolean.FALSE));
        stringBuffer.append(".log");
        File file = new File(f6585a);
        b = new File(stringBuffer.toString());
        if (file.exists()) {
            Logger.deleteOldLog(file);
        } else {
            file.mkdirs();
        }
        if (b.exists()) {
            return;
        }
        try {
            b.createNewFile();
        } catch (IOException e2) {
            b = null;
            e2.printStackTrace();
        }
    }

    public static void a(String str, String str2) throws Throwable {
        Log.i(str, str2);
        WriteLogToSDcard(Logger.W, str, str2);
    }
}
