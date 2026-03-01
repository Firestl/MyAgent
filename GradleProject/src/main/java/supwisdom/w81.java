package supwisdom;

import android.R;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class w81 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final u81 f9597a;

    /* JADX INFO: compiled from: Proguard */
    public static class a implements u81 {
        @Override // supwisdom.u81
        public void a(Window window, int i) {
        }
    }

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 23) {
            f9597a = new a91();
            return;
        }
        if (i >= 21 && !a()) {
            f9597a = new z81();
        } else if (i >= 19) {
            f9597a = new y81();
        } else {
            f9597a = new a();
        }
    }

    public static boolean a() throws Throwable {
        File file = new File(Environment.getRootDirectory(), "build.prop");
        if (!file.exists()) {
            return false;
        }
        Properties properties = new Properties();
        FileInputStream fileInputStream = null;
        try {
            try {
                try {
                    FileInputStream fileInputStream2 = new FileInputStream(file);
                    try {
                        properties.load(fileInputStream2);
                        fileInputStream2.close();
                    } catch (Exception e2) {
                        e = e2;
                        fileInputStream = fileInputStream2;
                        e.printStackTrace();
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        return properties.containsKey(DeviceConfig.KEY_EMUI_VERSION_CODE);
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream = fileInputStream2;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
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
        return properties.containsKey(DeviceConfig.KEY_EMUI_VERSION_CODE);
    }

    public static void a(Activity activity, int i) {
        a(activity, i, a(i) > 225);
    }

    public static int a(int i) {
        int iBlue = Color.blue(i);
        return (((Color.red(i) * 38) + (Color.green(i) * 75)) + (iBlue * 15)) >> 7;
    }

    public static void a(Activity activity, int i, boolean z) {
        a(activity.getWindow(), i, z);
    }

    public static void a(Window window, int i, boolean z) {
        if ((window.getAttributes().flags & 1024) > 0 || x81.f9721a) {
            return;
        }
        f9597a.a(window, i);
        v81.a(window, z);
    }

    @TargetApi(14)
    public static void a(Window window, boolean z) {
        View childAt = ((ViewGroup) window.findViewById(R.id.content)).getChildAt(0);
        if (childAt != null) {
            childAt.setFitsSystemWindows(z);
        }
    }
}
