package com.getui.gtc.a;

import android.text.TextUtils;
import com.getui.gtc.e.c;
import com.getui.gtc.i.d.b;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class c implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f2110a;
    public boolean b = true;
    public long c = 10000;

    @Override // java.lang.Runnable
    public final void run() {
        Map<String, String> mapA = com.getui.gtc.f.b.a(43200000L, (com.getui.gtc.f.d) null);
        if (mapA != null && mapA.size() > 0) {
            try {
                if (mapA.containsKey("sdk.gtc.type301.enable")) {
                    this.b = Boolean.parseBoolean(mapA.get("sdk.gtc.type301.enable"));
                }
            } catch (Exception e2) {
                com.getui.gtc.i.c.a.a(e2);
            }
            try {
                if (mapA.containsKey("sdk.gtc.type301.interval")) {
                    this.c = Long.parseLong(mapA.get("sdk.gtc.type301.interval")) * 1000;
                }
            } catch (Exception e3) {
                com.getui.gtc.i.c.a.a(e3);
            }
        }
        if (!this.b) {
            com.getui.gtc.i.c.a.b("type 301 is not enabled");
            return;
        }
        String str = c.a.f2213a.f2212a.f2214a;
        if (!TextUtils.isEmpty(str)) {
            try {
                String[] strArrSplit = str.split("\n");
                if (System.currentTimeMillis() - new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).parse(strArrSplit[0].split("\\|")[0]).getTime() > 604800000 || strArrSplit.length > 300) {
                    c.a.f2213a.f2212a.d("");
                    com.getui.gtc.i.c.a.a("type 301 clean stored samples");
                }
            } catch (Exception e4) {
                com.getui.gtc.i.c.a.b("type 301 clean samples error: " + e4.toString());
            }
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        StringBuilder sb = new StringBuilder();
        sb.append(a.a(simpleDateFormat.format(new Date())));
        sb.append("|");
        sb.append(a.a(com.getui.gtc.c.b.d));
        sb.append("|");
        sb.append(a.a(com.getui.gtc.c.b.f2140a));
        sb.append("|android|");
        com.getui.gtc.i.d.b unused = b.C0046b.f2252a;
        Calendar calendar = Calendar.getInstance();
        double d = ((double) (calendar.get(15) + calendar.get(16))) / 3600000.0d;
        NumberFormat numberInstance = NumberFormat.getNumberInstance();
        numberInstance.setMaximumFractionDigits(2);
        sb.append(a.a(numberInstance.format(d)));
        String string = sb.toString();
        com.getui.gtc.e.d dVar = c.a.f2213a.f2212a;
        if (!TextUtils.isEmpty(string)) {
            if (!TextUtils.isEmpty(dVar.f2214a)) {
                string = dVar.f2214a + "\n" + string;
            }
            if (dVar.a(7, string)) {
                dVar.f2214a = string;
            }
        }
        this.f2110a = c.a.f2213a.f2212a.f2214a;
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (jCurrentTimeMillis - c.a.f2213a.f2212a.b < this.c) {
                return;
            }
            com.getui.gtc.h.a.a(this.f2110a, 301);
            com.getui.gtc.e.d dVar2 = c.a.f2213a.f2212a;
            if (dVar2.a(6, jCurrentTimeMillis)) {
                dVar2.b = jCurrentTimeMillis;
            }
            c.a.f2213a.f2212a.d("");
        } catch (Exception e5) {
            com.getui.gtc.i.c.a.c("type 301 report error: " + e5.toString());
        }
    }
}
