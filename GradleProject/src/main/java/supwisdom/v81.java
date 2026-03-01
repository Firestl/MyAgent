package supwisdom;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class v81 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c f9489a;

    /* JADX INFO: compiled from: Proguard */
    public static class a extends e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final c f9490a;

        /* JADX WARN: Illegal instructions before constructor call */
        public a() {
            a aVar = null;
            super(aVar);
            this.f9490a = new d(aVar);
        }

        @Override // supwisdom.v81.e, supwisdom.v81.c
        public void a(Window window, boolean z) {
            super.a(window, z);
            this.f9490a.a(window, z);
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public static class b implements c {
        @Override // supwisdom.v81.c
        public void a(Window window, boolean z) {
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public interface c {
        void a(Window window, boolean z);
    }

    /* JADX INFO: compiled from: Proguard */
    public static class d implements c {
        public d() {
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0032  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static boolean a() throws java.lang.Throwable {
            /*
                r0 = 0
                r1 = 0
                java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L43
                java.io.File r3 = new java.io.File     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L43
                java.io.File r4 = android.os.Environment.getRootDirectory()     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L43
                java.lang.String r5 = "build.prop"
                r3.<init>(r4, r5)     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L43
                r2.<init>(r3)     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L43
                java.util.Properties r1 = new java.util.Properties     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L3a
                r1.<init>()     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L3a
                r1.load(r2)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L3a
                java.lang.String r3 = "ro.miui.ui.version.code"
                java.lang.String r3 = r1.getProperty(r3)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L3a
                if (r3 != 0) goto L32
                java.lang.String r3 = "ro.miui.ui.version.name"
                java.lang.String r3 = r1.getProperty(r3)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L3a
                if (r3 != 0) goto L32
                java.lang.String r3 = "ro.miui.internal.storage"
                java.lang.String r1 = r1.getProperty(r3)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L3a
                if (r1 == 0) goto L33
            L32:
                r0 = 1
            L33:
                r2.close()     // Catch: java.io.IOException -> L36
            L36:
                return r0
            L37:
                r0 = move-exception
                r1 = r2
                goto L3d
            L3a:
                r1 = r2
                goto L44
            L3c:
                r0 = move-exception
            L3d:
                if (r1 == 0) goto L42
                r1.close()     // Catch: java.io.IOException -> L42
            L42:
                throw r0
            L43:
            L44:
                if (r1 == 0) goto L49
                r1.close()     // Catch: java.io.IOException -> L49
            L49:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: supwisdom.v81.d.a():boolean");
        }

        public /* synthetic */ d(a aVar) {
            this();
        }

        @Override // supwisdom.v81.c
        public void a(Window window, boolean z) {
            Class<?> cls = window.getClass();
            try {
                Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                int i = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
                Class<?> cls3 = Integer.TYPE;
                Method method = cls.getMethod("setExtraFlags", cls3, cls3);
                Object[] objArr = new Object[2];
                objArr[0] = Integer.valueOf(z ? i : 0);
                objArr[1] = Integer.valueOf(i);
                method.invoke(window, objArr);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public static class e implements c {
        public e() {
        }

        @Override // supwisdom.v81.c
        @TargetApi(11)
        public void a(Window window, boolean z) {
            View decorView = window.getDecorView();
            int systemUiVisibility = decorView.getSystemUiVisibility();
            decorView.setSystemUiVisibility(z ? systemUiVisibility | 8192 : systemUiVisibility & (-8193));
        }

        public /* synthetic */ e(a aVar) {
            this();
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public static class f implements c {
        public f() {
        }

        public static boolean a() {
            return Build.DISPLAY.startsWith("Flyme");
        }

        public /* synthetic */ f(a aVar) {
            this();
        }

        @Override // supwisdom.v81.c
        public void a(Window window, boolean z) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            try {
                Field declaredField = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field declaredField2 = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                declaredField.setAccessible(true);
                declaredField2.setAccessible(true);
                int i = declaredField.getInt(null);
                int i2 = declaredField2.getInt(attributes);
                declaredField2.setInt(attributes, z ? i2 | i : (~i) & i2);
                window.setAttributes(attributes);
                declaredField.setAccessible(false);
                declaredField2.setAccessible(false);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    static {
        a aVar = null;
        if (d.a()) {
            if (Build.VERSION.SDK_INT >= 23) {
                f9489a = new a();
                return;
            } else {
                f9489a = new d(aVar);
                return;
            }
        }
        if (Build.VERSION.SDK_INT >= 23) {
            f9489a = new e(aVar);
        } else if (f.a()) {
            f9489a = new f(aVar);
        } else {
            f9489a = new b();
        }
    }

    public static void a(Window window, boolean z) {
        f9489a.a(window, z);
    }
}
