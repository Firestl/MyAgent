package com.getui.gtc.base.log.a;

import android.content.Context;
import com.getui.gtc.base.log.ILogController;
import com.getui.gtc.base.log.ILogFormatter;
import com.getui.gtc.base.util.CommonUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/* JADX INFO: loaded from: classes.dex */
public class a implements ILogController {
    public static List<String> b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f2130a = "sdk.debug";
    public final Context c;
    public final ILogFormatter d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f2131e;

    public a(Context context, ILogFormatter iLogFormatter) {
        this.c = context;
        this.d = (ILogFormatter) com.getui.gtc.base.log.e.a.a(iLogFormatter);
        a(this.f2130a);
    }

    public static List<String> a(Context context) {
        File file;
        ArrayList arrayList = new ArrayList();
        String str = context.getPackageName() + ".properties";
        File externalFilesDir = CommonUtil.getExternalFilesDir(context);
        for (int i = 0; i < 4; i++) {
            InputStream inputStreamOpen = null;
            if (i == 0) {
                try {
                    inputStreamOpen = context.getAssets().open(str);
                } catch (Throwable unused) {
                    if (inputStreamOpen != null) {
                    }
                }
            } else {
                if (i == 1) {
                    file = new File(externalFilesDir, str);
                } else if (i == 2) {
                    file = new File(context.getFilesDir(), str);
                } else {
                    file = new File(externalFilesDir, context.getPackageName() + "-online.properties");
                }
                if (file.exists() && file.canRead()) {
                    inputStreamOpen = new FileInputStream(file);
                }
            }
            Properties properties = new Properties();
            properties.load(inputStreamOpen);
            Enumeration enumerationKeys = properties.keys();
            while (enumerationKeys.hasMoreElements()) {
                String str2 = (String) enumerationKeys.nextElement();
                String property = properties.getProperty(str2);
                if (property != null && Boolean.parseBoolean(property.trim()) && !arrayList.contains(str2)) {
                    arrayList.add(str2);
                }
            }
            if (inputStreamOpen != null) {
                try {
                    inputStreamOpen.close();
                } catch (IOException unused2) {
                }
            }
        }
        return arrayList;
    }

    public static boolean a(Context context, String str) {
        try {
            if (b == null) {
                synchronized (a.class) {
                    if (b == null) {
                        b = Collections.unmodifiableList(a(context));
                    }
                }
            }
            return b.contains(str);
        } catch (Throwable unused) {
            return false;
        }
    }

    public final void a(String str) {
        this.f2130a = str;
        this.f2131e = a(this.c, str);
    }

    @Override // com.getui.gtc.base.log.ILogController
    public boolean isLoggable(int i, String str) {
        int i2 = i & 240;
        if (i2 == 0 || i2 == 32) {
            return this.f2131e;
        }
        return false;
    }

    @Override // com.getui.gtc.base.log.ILogController
    public void log(int i, String str, String str2, Throwable th) {
        if ((i & 240) != 0) {
            i &= 15;
        }
        this.d.log(i, str, str2, th);
    }
}
