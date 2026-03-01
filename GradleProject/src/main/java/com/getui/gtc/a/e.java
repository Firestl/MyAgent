package com.getui.gtc.a;

import android.annotation.TargetApi;
import android.os.Build;
import android.text.TextUtils;
import com.getui.gtc.base.GtcProvider;
import com.getui.gtc.base.util.CommonUtil;
import com.getui.gtc.e.c;
import com.huawei.hms.common.PackageConstants;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class e implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f2112a;
    public boolean b = false;
    public long c = 86400000;
    public String[] d = {PackageConstants.SERVICES_PACKAGE_APPMARKET, "com.bbk.launcher2", "net.oneplus.launcher", "com.android.deskclock", "com.heytap.market", "com.oppo.market"};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String[] f2113e = {"com.tencent.mm", "com.tencent.mobileqq", "com.eg.android.AlipayGphone", "com.jingdong.app.mall", "com.ss.android.article.news", "com.taobao.taobao", "com.tmall.wireless", "com.sankuai.meituan", "com.xunmeng.pinduoduo", "com.ss.android.ugc.aweme"};

    private String a() {
        try {
            StringBuilder sb = new StringBuilder();
            for (String str : this.d) {
                try {
                    sb.append(str + "#" + com.getui.gtc.i.d.a.a(str).applicationInfo.sourceDir + ",");
                } catch (Throwable unused) {
                    com.getui.gtc.i.c.a.b(str + " not found");
                }
            }
            if (sb.toString().endsWith(",")) {
                sb.deleteCharAt(sb.length() - 1);
            }
            return sb.toString();
        } catch (Throwable th) {
            com.getui.gtc.i.c.a.a(th);
            return "";
        }
    }

    @TargetApi(26)
    private String b() {
        File parentFile;
        int i;
        long millis;
        long millis2;
        File parentFile2;
        if (Build.VERSION.SDK_INT < 26) {
            com.getui.gtc.i.c.a.a("type304 get hot info failed, api<26");
            return "";
        }
        StringBuilder sb = new StringBuilder();
        try {
            File externalCacheDir = GtcProvider.context().getExternalCacheDir();
            parentFile = null;
            if (externalCacheDir != null && (parentFile2 = externalCacheDir.getParentFile()) != null) {
                parentFile = parentFile2.getParentFile();
            }
        } catch (Throwable th) {
            th = th;
        }
        if (parentFile == null) {
            return "";
        }
        try {
            String[] strArr = this.f2113e;
            int length = strArr.length;
            int i2 = 0;
            int i3 = 0;
            while (i3 < length) {
                String str = strArr[i3];
                try {
                    BasicFileAttributes attributes = Files.readAttributes(new File(parentFile, str).toPath(), (Class<BasicFileAttributes>) BasicFileAttributes.class, new LinkOption[i2]);
                    millis = attributes.creationTime().toMillis();
                    millis2 = attributes.lastAccessTime().toMillis();
                    i = i3;
                } catch (Throwable th2) {
                    th = th2;
                    i = i3;
                }
                try {
                    sb.append(str + "#" + com.getui.gtc.i.d.a.a(str).firstInstallTime + "#" + millis + "#" + millis2);
                    sb.append(",");
                } catch (Throwable th3) {
                    th = th3;
                    com.getui.gtc.i.c.a.a(th);
                }
                i3 = i + 1;
                i2 = 0;
            }
            if (sb.toString().endsWith(",")) {
                sb.deleteCharAt(sb.length() - 1);
            }
        } catch (Throwable th4) {
            th = th4;
            com.getui.gtc.i.c.a.a(th);
        }
        return sb.toString();
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            Map<String, String> mapA = com.getui.gtc.f.b.a(43200000L, (com.getui.gtc.f.d) null);
            if (mapA != null && mapA.size() > 0) {
                try {
                    String str = mapA.get("sdk.gtc.type304.enable");
                    if (str != null) {
                        this.b = Boolean.parseBoolean(str);
                    }
                } catch (Exception e2) {
                    com.getui.gtc.i.c.a.a(e2);
                }
                try {
                    String str2 = mapA.get("sdk.gtc.type304.interval");
                    if (str2 != null) {
                        this.c = Long.parseLong(str2) * 1000;
                    }
                } catch (Exception e3) {
                    com.getui.gtc.i.c.a.a(e3);
                }
                try {
                    String str3 = mapA.get("sdk.gtc.type304.sys_al");
                    if (!TextUtils.isEmpty(str3) && !"none".equalsIgnoreCase(str3)) {
                        String[] strArrSplit = str3.split(",");
                        if (strArrSplit.length > 0) {
                            this.d = strArrSplit;
                            com.getui.gtc.i.c.a.a("type304 dyc sysApp:" + Arrays.toString(strArrSplit));
                        }
                    }
                } catch (Exception e4) {
                    com.getui.gtc.i.c.a.a(e4);
                }
                try {
                    String str4 = mapA.get("sdk.gtc.type304.hot_al");
                    if (!TextUtils.isEmpty(str4) && !"none".equalsIgnoreCase(str4)) {
                        String[] strArrSplit2 = str4.split(",");
                        if (strArrSplit2.length > 0) {
                            this.f2113e = strArrSplit2;
                            com.getui.gtc.i.c.a.a("type304 dyc hotApp:" + Arrays.toString(strArrSplit2));
                        }
                    }
                } catch (Exception e5) {
                    com.getui.gtc.i.c.a.a(e5);
                }
            }
            if (!this.b) {
                com.getui.gtc.i.c.a.b("type 304 is not enabled");
                return;
            }
            if (CommonUtil.isAppDebugEnable()) {
                com.getui.gtc.i.c.a.b("type 304 is debug, disallow");
                return;
            }
            if (System.currentTimeMillis() - c.a.f2213a.f2212a.k < this.c) {
                return;
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
            String str5 = a.a(simpleDateFormat.format(new Date())) + "|" + a.a(com.getui.gtc.c.b.d) + "|" + a.a(com.getui.gtc.c.b.f2140a) + "|android|" + GtcProvider.context().getPackageName() + "|GTC-3.2.13.0|" + a() + "|" + b();
            this.f2112a = str5;
            try {
                com.getui.gtc.h.a.a(str5, 304);
                com.getui.gtc.e.d dVar = c.a.f2213a.f2212a;
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (dVar.a(16, jCurrentTimeMillis)) {
                    dVar.k = jCurrentTimeMillis;
                }
            } catch (Exception e6) {
                com.getui.gtc.i.c.a.c("type 304 report error: " + e6.toString());
            }
        } catch (Throwable th) {
            com.getui.gtc.i.c.a.a("type 304", th);
        }
    }
}
