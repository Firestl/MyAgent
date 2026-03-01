package supwisdom;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import androidx.core.graphics.drawable.IconCompat;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import supwisdom.d8;

/* JADX INFO: compiled from: ShortcutManagerCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class e8 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile d8<?> f7449a;
    public static volatile List<b8> b;

    /* JADX INFO: compiled from: ShortcutManagerCompat.java */
    public static class a {
        public static String a(List<ShortcutInfo> list) {
            int rank = -1;
            String id = null;
            for (ShortcutInfo shortcutInfo : list) {
                if (shortcutInfo.getRank() > rank) {
                    id = shortcutInfo.getId();
                    rank = shortcutInfo.getRank();
                }
            }
            return id;
        }
    }

    public static int a(Context context) {
        na.a(context);
        if (Build.VERSION.SDK_INT >= 25) {
            return ((ShortcutManager) context.getSystemService(ShortcutManager.class)).getMaxShortcutCountPerActivity();
        }
        return 5;
    }

    public static boolean b(Context context, c8 c8Var) {
        na.a(context);
        na.a(c8Var);
        int iA = a(context);
        if (iA == 0) {
            return false;
        }
        if (Build.VERSION.SDK_INT <= 29) {
            a(context, c8Var);
        }
        int i = Build.VERSION.SDK_INT;
        if (i >= 30) {
            ((ShortcutManager) context.getSystemService(ShortcutManager.class)).pushDynamicShortcut(c8Var.d());
        } else if (i >= 25) {
            ShortcutManager shortcutManager = (ShortcutManager) context.getSystemService(ShortcutManager.class);
            if (shortcutManager.isRateLimitingActive()) {
                return false;
            }
            List<ShortcutInfo> dynamicShortcuts = shortcutManager.getDynamicShortcuts();
            if (dynamicShortcuts.size() >= iA) {
                shortcutManager.removeDynamicShortcuts(Arrays.asList(a.a(dynamicShortcuts)));
            }
            shortcutManager.addDynamicShortcuts(Arrays.asList(c8Var.d()));
        }
        d8<?> d8VarC = c(context);
        try {
            List<c8> listA = d8VarC.a();
            if (listA.size() >= iA) {
                d8VarC.b(Arrays.asList(a(listA)));
            }
            d8VarC.a(Arrays.asList(c8Var));
            Iterator<b8> it = b(context).iterator();
            while (it.hasNext()) {
                it.next().a(Collections.singletonList(c8Var));
            }
            a(context, c8Var.b());
            return true;
        } catch (Exception unused) {
            Iterator<b8> it2 = b(context).iterator();
            while (it2.hasNext()) {
                it2.next().a(Collections.singletonList(c8Var));
            }
            a(context, c8Var.b());
            return false;
        } catch (Throwable th) {
            Iterator<b8> it3 = b(context).iterator();
            while (it3.hasNext()) {
                it3.next().a(Collections.singletonList(c8Var));
            }
            a(context, c8Var.b());
            throw th;
        }
    }

    public static d8<?> c(Context context) {
        if (f7449a == null) {
            if (Build.VERSION.SDK_INT >= 23) {
                try {
                    f7449a = (d8) Class.forName("androidx.sharetarget.ShortcutInfoCompatSaverImpl", false, e8.class.getClassLoader()).getMethod("getInstance", Context.class).invoke(null, context);
                } catch (Exception unused) {
                }
            }
            if (f7449a == null) {
                f7449a = new d8.a();
            }
        }
        return f7449a;
    }

    public static void d(Context context) {
        if (Build.VERSION.SDK_INT >= 25) {
            ((ShortcutManager) context.getSystemService(ShortcutManager.class)).removeAllDynamicShortcuts();
        }
        c(context).b();
        Iterator<b8> it = b(context).iterator();
        while (it.hasNext()) {
            it.next().a();
        }
    }

    public static void a(Context context, String str) {
        na.a(context);
        na.a(str);
        if (Build.VERSION.SDK_INT >= 25) {
            ((ShortcutManager) context.getSystemService(ShortcutManager.class)).reportShortcutUsed(str);
        }
        Iterator<b8> it = b(context).iterator();
        while (it.hasNext()) {
            it.next().b(Collections.singletonList(str));
        }
    }

    public static boolean a(Context context, c8 c8Var) {
        Bitmap bitmapDecodeStream;
        IconCompat iconCompatB;
        IconCompat iconCompat = c8Var.h;
        if (iconCompat == null) {
            return false;
        }
        int i = iconCompat.f1275a;
        if (i != 6 && i != 4) {
            return true;
        }
        InputStream inputStreamA = c8Var.h.a(context);
        if (inputStreamA == null || (bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamA)) == null) {
            return false;
        }
        if (i == 6) {
            iconCompatB = IconCompat.a(bitmapDecodeStream);
        } else {
            iconCompatB = IconCompat.b(bitmapDecodeStream);
        }
        c8Var.h = iconCompatB;
        return true;
    }

    public static String a(List<c8> list) {
        int iC = -1;
        String strB = null;
        for (c8 c8Var : list) {
            if (c8Var.c() > iC) {
                strB = c8Var.b();
                iC = c8Var.c();
            }
        }
        return strB;
    }

    public static List<b8> b(Context context) {
        Bundle bundle;
        String string;
        if (b == null) {
            ArrayList arrayList = new ArrayList();
            if (Build.VERSION.SDK_INT >= 21) {
                PackageManager packageManager = context.getPackageManager();
                Intent intent = new Intent("androidx.core.content.pm.SHORTCUT_LISTENER");
                intent.setPackage(context.getPackageName());
                Iterator<ResolveInfo> it = packageManager.queryIntentActivities(intent, 128).iterator();
                while (it.hasNext()) {
                    ActivityInfo activityInfo = it.next().activityInfo;
                    if (activityInfo != null && (bundle = activityInfo.metaData) != null && (string = bundle.getString("androidx.core.content.pm.shortcut_listener_impl")) != null) {
                        try {
                            arrayList.add((b8) Class.forName(string, false, e8.class.getClassLoader()).getMethod("getInstance", Context.class).invoke(null, context));
                        } catch (Exception unused) {
                        }
                    }
                }
            }
            if (b == null) {
                b = arrayList;
            }
        }
        return b;
    }
}
