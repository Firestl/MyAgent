package com.getui.gtc.i.d;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import com.getui.gtc.a.a.e;
import com.getui.gtc.a.a.f;
import com.getui.gtc.base.GtcProvider;
import com.getui.gtc.base.util.CommonUtil;
import com.getui.gtc.base.util.ScheduleQueue;
import com.getui.gtc.dim.DimManager;
import com.getui.gtc.f.d;
import com.lzy.okgo.model.HttpHeaders;
import com.xiaomi.mipush.sdk.Constants;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AtomicBoolean f2248a;

    public static class a extends f {
        public a(e eVar) {
            this.f2093e = eVar;
        }

        @Override // com.getui.gtc.a.a.f
        public final void a() {
            e eVar = this.f2093e;
            if (eVar != null) {
                try {
                    eVar.a(null);
                } catch (Throwable th) {
                    com.getui.gtc.i.c.a.b(th);
                }
            }
        }

        @Override // com.getui.gtc.a.a.f
        public final void a(int i) {
            e eVar = this.f2093e;
            if (eVar != null) {
                try {
                    eVar.a(null);
                } catch (Throwable th) {
                    com.getui.gtc.i.c.a.b(th);
                }
            }
        }

        @Override // com.getui.gtc.a.a.f
        public final void a(Map<String, List<String>> map, byte[] bArr) {
            try {
                if (this.f2093e != null) {
                    HashMap map2 = new HashMap();
                    map2.put("header", map);
                    map2.put("data", new String(bArr, "utf-8"));
                    this.f2093e.a(map2);
                }
            } catch (Throwable th) {
                com.getui.gtc.i.c.a.b(th);
            }
        }
    }

    /* JADX INFO: renamed from: com.getui.gtc.i.d.b$b, reason: collision with other inner class name */
    public static class C0046b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f2252a = new b(0);
    }

    public b() {
        this.f2248a = new AtomicBoolean(false);
        final Map<String, String> mapA = com.getui.gtc.f.b.a(43200000L, new d() { // from class: com.getui.gtc.i.d.b.1
            @Override // com.getui.gtc.f.d
            public final void a(String str) {
            }

            @Override // com.getui.gtc.f.d
            public final void a(Map<String, String> map, Map<String, String> map2) {
                b.this.a(map2);
                com.getui.gtc.i.c.b.a(map2);
            }
        });
        a(mapA);
        ScheduleQueue.getInstance().addSchedule(new Runnable() { // from class: com.getui.gtc.i.d.b.2
            @Override // java.lang.Runnable
            public final void run() {
                com.getui.gtc.i.c.b.a((Map<String, String>) mapA);
            }
        });
    }

    public /* synthetic */ b(byte b) {
        this();
    }

    private void a() {
        if (this.f2248a.getAndSet(true) || a(GtcProvider.context())) {
            return;
        }
        a aVar = new a(new e() { // from class: com.getui.gtc.i.d.b.3
            @Override // com.getui.gtc.a.a.e
            public final void a(Object obj) throws Throwable {
                Map map;
                List<String> list;
                if (obj instanceof HashMap) {
                    HashMap map2 = (HashMap) obj;
                    if (map2.size() <= 0 || (map = (Map) map2.get("header")) == null || map.size() <= 0 || (list = (List) map.get(HttpHeaders.HEAD_KEY_DATE)) == null) {
                        return;
                    }
                    for (String str : list) {
                        if (str.contains(Constants.COLON_SEPARATOR) && str.contains("GMT")) {
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss z", Locale.ENGLISH);
                            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                            Date date = simpleDateFormat.parse(str);
                            long time = date.getTime();
                            long jCurrentTimeMillis = System.currentTimeMillis();
                            if (jCurrentTimeMillis != time) {
                                long j = time - jCurrentTimeMillis;
                                com.getui.gtc.i.c.a.a("serverDate:" + date + ", localTimeByServerTimeDiff:" + j);
                                DimManager.getInstance().set("dim-2-2-4-1", "dim-2-2-4-1", String.valueOf(j));
                            }
                        }
                    }
                }
            }
        });
        aVar.f2092a = "https://sdk-open-phone.getui.com/";
        ScheduleQueue.getInstance().addSchedule(new com.getui.gtc.a.a.b(aVar));
    }

    public static boolean a(Context context) {
        return CommonUtil.isAppForeground() && Settings.System.getInt(context.getContentResolver(), "auto_time", 1) == 1;
    }

    public final void a(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        try {
            DimManager.getInstance().set("dim-2-2-1-1", "dim-2-2-1-1", new JSONObject(map).toString());
            String str = map.get("sdk.gtc.dim.halfclosed.enable");
            if (TextUtils.isEmpty(str) || "none".equals(str)) {
                return;
            }
            a();
        } catch (Throwable th) {
            com.getui.gtc.i.c.a.a(th);
        }
    }
}
