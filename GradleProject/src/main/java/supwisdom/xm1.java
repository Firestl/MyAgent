package supwisdom;

import android.content.Context;
import android.util.Log;
import com.taobao.weex.utils.WXLogUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: compiled from: JacocoCodeCoverage.java */
/* JADX INFO: loaded from: classes2.dex */
public class xm1 {
    public static void a(Context context) throws Throwable {
        WXLogUtils.d("CoverageDataDumper", "generateCoverageReport()");
        StringBuilder sb = new StringBuilder();
        FileOutputStream fileOutputStream = null;
        sb.append(context.getExternalFilesDir(null));
        sb.append("/coverage.ec");
        File file = new File(sb.toString());
        try {
            try {
                try {
                    Object objInvoke = Class.forName("org.jacoco.agent.rt.RT").getMethod("getAgent", new Class[0]).invoke(null, new Object[0]);
                    FileOutputStream fileOutputStream2 = new FileOutputStream(file, false);
                    try {
                        fileOutputStream2.write((byte[]) objInvoke.getClass().getMethod("getExecutionData", Boolean.TYPE).invoke(objInvoke, false));
                        WXLogUtils.d("CoverageDataDumper", "generateCoverageReport: ok! file in [Android/data/com.alibaba.weex/files]");
                        fileOutputStream2.close();
                    } catch (Exception e2) {
                        e = e2;
                        fileOutputStream = fileOutputStream2;
                        Log.e("CoverageDataDumper", e.toString());
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
                                Log.e("CoverageDataDumper", e3.getMessage().toString());
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
            Log.e("CoverageDataDumper", e5.getMessage().toString());
        }
    }
}
