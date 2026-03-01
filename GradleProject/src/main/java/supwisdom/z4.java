package supwisdom;

import android.content.Context;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.motion.widget.MotionLayout;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.mipush.sdk.Constants;

/* JADX INFO: compiled from: Debug.java */
/* JADX INFO: loaded from: classes.dex */
public class z4 {
    public static void a(String str, String str2, int i) {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        int iMin = Math.min(i, stackTrace.length - 1);
        String str3 = Operators.SPACE_STR;
        for (int i2 = 1; i2 <= iMin; i2++) {
            StackTraceElement stackTraceElement = stackTrace[i2];
            String str4 = ".(" + stackTrace[i2].getFileName() + Constants.COLON_SEPARATOR + stackTrace[i2].getLineNumber() + ") " + stackTrace[i2].getMethodName();
            str3 = str3 + Operators.SPACE_STR;
            Log.v(str, str2 + str3 + str4 + str3);
        }
    }

    public static String a(View view) {
        try {
            return view.getContext().getResources().getResourceEntryName(view.getId());
        } catch (Exception unused) {
            return GrsBaseInfo.CountryCodeSource.UNKNOWN;
        }
    }

    public static String a(Context context, int i) {
        if (i == -1) {
            return GrsBaseInfo.CountryCodeSource.UNKNOWN;
        }
        try {
            return context.getResources().getResourceEntryName(i);
        } catch (Exception unused) {
            return Operators.CONDITION_IF_STRING + i;
        }
    }

    public static String a(MotionLayout motionLayout, int i) {
        return i == -1 ? "UNDEFINED" : motionLayout.getContext().getResources().getResourceEntryName(i);
    }

    public static String a() {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        return ".(" + stackTraceElement.getFileName() + Constants.COLON_SEPARATOR + stackTraceElement.getLineNumber() + ")";
    }
}
