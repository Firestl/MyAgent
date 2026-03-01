package supwisdom;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.StatFs;
import android.provider.Settings;
import android.util.Log;
import com.squareup.picasso.Downloader;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.ui.component.list.template.TemplateDom;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ThreadFactory;

/* JADX INFO: compiled from: Utils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class rh1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final StringBuilder f9064a = new StringBuilder();

    /* JADX INFO: compiled from: Utils.java */
    public static class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            sendMessageDelayed(obtainMessage(), 1000L);
        }
    }

    /* JADX INFO: compiled from: Utils.java */
    @TargetApi(11)
    public static class b {
        public static int a(ActivityManager activityManager) {
            return activityManager.getLargeMemoryClass();
        }
    }

    /* JADX INFO: compiled from: Utils.java */
    @TargetApi(12)
    public static class c {
        public static int a(Bitmap bitmap) {
            return bitmap.getByteCount();
        }
    }

    /* JADX INFO: compiled from: Utils.java */
    public static class d {
        public static Downloader a(Context context) {
            return new gh1(context);
        }
    }

    /* JADX INFO: compiled from: Utils.java */
    public static class e extends Thread {
        public e(Runnable runnable) {
            super(runnable);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Process.setThreadPriority(10);
            super.run();
        }
    }

    /* JADX INFO: compiled from: Utils.java */
    public static class f implements ThreadFactory {
        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new e(runnable);
        }
    }

    public static int a(Bitmap bitmap) {
        int iA = Build.VERSION.SDK_INT >= 12 ? c.a(bitmap) : bitmap.getRowBytes() * bitmap.getHeight();
        if (iA >= 0) {
            return iA;
        }
        throw new IllegalStateException("Negative size: " + bitmap);
    }

    public static boolean b() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    public static Downloader c(Context context) {
        try {
            Class.forName("supwisdom.re1");
            return d.a(context);
        } catch (ClassNotFoundException unused) {
            return new qh1(context);
        }
    }

    public static boolean d(Context context) {
        try {
            return Settings.System.getInt(context.getContentResolver(), "airplane_mode_on", 0) != 0;
        } catch (NullPointerException unused) {
            return false;
        }
    }

    public static File b(Context context) {
        File file = new File(context.getApplicationContext().getCacheDir(), "picasso-cache");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static byte[] c(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[4096];
        while (true) {
            int i = inputStream.read(bArr);
            if (-1 != i) {
                byteArrayOutputStream.write(bArr, 0, i);
            } else {
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    public static <T> T a(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public static boolean b(Context context, String str) {
        return context.checkCallingOrSelfPermission(str) == 0;
    }

    public static void a() {
        if (!b()) {
            throw new IllegalStateException("Method call should happen from the main thread.");
        }
    }

    public static boolean b(InputStream inputStream) throws IOException {
        byte[] bArr = new byte[12];
        return inputStream.read(bArr, 0, 12) == 12 && "RIFF".equals(new String(bArr, 0, 4, "US-ASCII")) && "WEBP".equals(new String(bArr, 8, 4, "US-ASCII"));
    }

    public static String a(tg1 tg1Var) {
        return a(tg1Var, "");
    }

    public static String a(tg1 tg1Var, String str) {
        StringBuilder sb = new StringBuilder(str);
        rg1 rg1VarC = tg1Var.c();
        if (rg1VarC != null) {
            sb.append(rg1VarC.b.d());
        }
        List<rg1> listD = tg1Var.d();
        if (listD != null) {
            int size = listD.size();
            for (int i = 0; i < size; i++) {
                if (i > 0 || rg1VarC != null) {
                    sb.append(", ");
                }
                sb.append(listD.get(i).b.d());
            }
        }
        return sb.toString();
    }

    public static void a(String str, String str2, String str3) {
        a(str, str2, str3, "");
    }

    public static void a(String str, String str2, String str3, String str4) {
        Log.d("Picasso", String.format("%1$-11s %2$-12s %3$s %4$s", str, str2, str3, str4));
    }

    public static String a(jh1 jh1Var) {
        String strA = a(jh1Var, f9064a);
        f9064a.setLength(0);
        return strA;
    }

    public static String a(jh1 jh1Var, StringBuilder sb) {
        String str = jh1Var.f;
        if (str != null) {
            sb.ensureCapacity(str.length() + 50);
            sb.append(jh1Var.f);
        } else {
            Uri uri = jh1Var.d;
            if (uri != null) {
                String string = uri.toString();
                sb.ensureCapacity(string.length() + 50);
                sb.append(string);
            } else {
                sb.ensureCapacity(50);
                sb.append(jh1Var.f8063e);
            }
        }
        sb.append('\n');
        if (jh1Var.m != 0.0f) {
            sb.append("rotation:");
            sb.append(jh1Var.m);
            if (jh1Var.p) {
                sb.append(TemplateDom.SEPARATOR);
                sb.append(jh1Var.n);
                sb.append('x');
                sb.append(jh1Var.o);
            }
            sb.append('\n');
        }
        if (jh1Var.c()) {
            sb.append("resize:");
            sb.append(jh1Var.h);
            sb.append('x');
            sb.append(jh1Var.i);
            sb.append('\n');
        }
        if (jh1Var.j) {
            sb.append("centerCrop");
            sb.append('\n');
        } else if (jh1Var.k) {
            sb.append("centerInside");
            sb.append('\n');
        }
        List<ph1> list = jh1Var.g;
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                sb.append(jh1Var.g.get(i).a());
                sb.append('\n');
            }
        }
        return sb.toString();
    }

    public static void a(InputStream inputStream) {
        if (inputStream == null) {
            return;
        }
        try {
            inputStream.close();
        } catch (IOException unused) {
        }
    }

    public static boolean a(String str) {
        if (str == null) {
            return false;
        }
        String[] strArrSplit = str.split(Operators.SPACE_STR, 2);
        if ("CACHE".equals(strArrSplit[0])) {
            return true;
        }
        if (strArrSplit.length == 1) {
            return false;
        }
        try {
            if ("CONDITIONAL_CACHE".equals(strArrSplit[0])) {
                return Integer.parseInt(strArrSplit[1]) == 304;
            }
            return false;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    public static long a(File file) {
        long blockCount;
        try {
            StatFs statFs = new StatFs(file.getAbsolutePath());
            blockCount = (((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize())) / 50;
        } catch (IllegalArgumentException unused) {
            blockCount = 5242880;
        }
        return Math.max(Math.min(blockCount, 52428800L), 5242880L);
    }

    public static int a(Context context) {
        ActivityManager activityManager = (ActivityManager) a(context, "activity");
        boolean z = (context.getApplicationInfo().flags & 1048576) != 0;
        int memoryClass = activityManager.getMemoryClass();
        if (z && Build.VERSION.SDK_INT >= 11) {
            memoryClass = b.a(activityManager);
        }
        return (memoryClass * 1048576) / 7;
    }

    public static <T> T a(Context context, String str) {
        return (T) context.getSystemService(str);
    }

    public static int a(Resources resources, jh1 jh1Var) throws FileNotFoundException {
        Uri uri;
        if (jh1Var.f8063e == 0 && (uri = jh1Var.d) != null) {
            String authority = uri.getAuthority();
            if (authority != null) {
                List<String> pathSegments = jh1Var.d.getPathSegments();
                if (pathSegments != null && !pathSegments.isEmpty()) {
                    if (pathSegments.size() == 1) {
                        try {
                            return Integer.parseInt(pathSegments.get(0));
                        } catch (NumberFormatException unused) {
                            throw new FileNotFoundException("Last path segment is not a resource ID: " + jh1Var.d);
                        }
                    }
                    if (pathSegments.size() == 2) {
                        return resources.getIdentifier(pathSegments.get(1), pathSegments.get(0), authority);
                    }
                    throw new FileNotFoundException("More than two path segments: " + jh1Var.d);
                }
                throw new FileNotFoundException("No path segments: " + jh1Var.d);
            }
            throw new FileNotFoundException("No package provided: " + jh1Var.d);
        }
        return jh1Var.f8063e;
    }

    public static Resources a(Context context, jh1 jh1Var) throws FileNotFoundException {
        Uri uri;
        if (jh1Var.f8063e == 0 && (uri = jh1Var.d) != null) {
            String authority = uri.getAuthority();
            if (authority != null) {
                try {
                    return context.getPackageManager().getResourcesForApplication(authority);
                } catch (PackageManager.NameNotFoundException unused) {
                    throw new FileNotFoundException("Unable to obtain resources for package: " + jh1Var.d);
                }
            }
            throw new FileNotFoundException("No package provided: " + jh1Var.d);
        }
        return context.getResources();
    }

    public static void a(Looper looper) {
        a aVar = new a(looper);
        aVar.sendMessageDelayed(aVar.obtainMessage(), 1000L);
    }
}
