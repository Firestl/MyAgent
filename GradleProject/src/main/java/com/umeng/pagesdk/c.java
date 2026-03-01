package com.umeng.pagesdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Choreographer;
import com.efs.sdk.base.EfsReporter;
import com.efs.sdk.base.protocol.record.EfsJSONLog;
import dc.squareup.okhttp3.internal.http2.Http2Codec;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f5501a;
    public long b;
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f5502e;
    public boolean g;
    public long h;
    public long i;
    public String j;
    public Context k;
    public Map<String, Double> f = new HashMap();
    public Choreographer.FrameCallback l = new Choreographer.FrameCallback() { // from class: com.umeng.pagesdk.c.1
        @Override // android.view.Choreographer.FrameCallback
        public final void doFrame(long j) {
            boolean z = PageManger.isDebug;
            c cVar = c.this;
            if (cVar.g) {
                if (cVar.h == 0) {
                    cVar.h = System.currentTimeMillis();
                }
                long jCurrentTimeMillis = System.currentTimeMillis();
                c cVar2 = c.this;
                if (jCurrentTimeMillis - cVar2.h > cVar2.i) {
                    cVar2.b();
                    return;
                }
                if (cVar2.b == 0) {
                    cVar2.b = j;
                }
                c cVar3 = c.this;
                float f = (j - cVar3.b) / 1000000.0f;
                if (f > cVar3.f5501a) {
                    double d = (((long) cVar3.c) * 1000) / ((double) f);
                    cVar3.c = 0;
                    cVar3.b = 0L;
                    if (PageManger.isDebug) {
                        Log.i("PageManger-PageFPSImpl", "doFrame: " + d + ", map size is " + c.this.f.size() + ", page is " + c.this.j);
                    }
                    Map<String, Double> map = c.this.f;
                    StringBuilder sb = new StringBuilder();
                    sb.append(System.currentTimeMillis());
                    map.put(sb.toString(), Double.valueOf(d));
                    c cVar4 = c.this;
                    int i = cVar4.d + 1;
                    cVar4.d = i;
                    if (i >= cVar4.f5502e) {
                        cVar4.c();
                        c cVar5 = c.this;
                        cVar5.d = 0;
                        Map<String, Double> map2 = cVar5.f;
                        if (map2 != null) {
                            map2.clear();
                        }
                    }
                } else {
                    cVar3.c++;
                }
                Choreographer.getInstance().postFrameCallback(this);
            }
        }
    };

    public c(Context context) {
        SharedPreferences sharedPreferences;
        this.f5501a = 1000.0f;
        this.f5502e = 6;
        this.i = 300000L;
        this.k = context;
        if (context == null || (sharedPreferences = context.getSharedPreferences("efs_page", 0)) == null) {
            return;
        }
        this.f5501a = sharedPreferences.getFloat(PageConfigManger.APM_FPSPERF_COLLECT_INTERVAL, 1000.0f);
        this.f5502e = sharedPreferences.getInt(PageConfigManger.APM_FPSPERF_COLLECT_INTERVAL_TOGETHER, 6);
        this.i = sharedPreferences.getLong(PageConfigManger.APM_FPSPERF_COLLECT_MAX_PERIOD_SEC, 300000L);
        if (PageManger.isDebug) {
            Log.i("PageManger-PageFPSImpl", "init fps. diff is " + this.f5501a + ", count diff is " + this.f5502e + ", dlealt time is " + this.i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        a aVarA;
        Iterator<Map.Entry<String, Double>> it = this.f.entrySet().iterator();
        if (it != null) {
            JSONArray jSONArray = null;
            boolean z = false;
            while (it.hasNext()) {
                Map.Entry<String, Double> next = it.next();
                if (next != null) {
                    if (jSONArray == null) {
                        jSONArray = new JSONArray();
                    }
                    JSONObject jSONObject = new JSONObject();
                    try {
                        if (PageManger.getRefreshRate() > 0.0f && next.getValue().doubleValue() < ((double) PageManger.getRefreshRate()) * 1.1d) {
                            jSONObject.put(next.getKey(), next.getValue());
                            if (next.getValue().doubleValue() < 40.0d) {
                                z = true;
                            }
                        }
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                    jSONArray.put(jSONObject);
                }
            }
            if (jSONArray != null) {
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put("pN", this.j);
                    jSONObject2.put("pF", jSONArray);
                    if (z && (aVarA = b.a(this.k).a()) != null) {
                        jSONObject2.put(Http2Codec.TE, aVarA.c);
                        jSONObject2.put("le", aVarA.f5496a);
                    }
                    EfsJSONLog efsJSONLog = new EfsJSONLog("fpsperf");
                    efsJSONLog.put("fps", jSONObject2);
                    EfsReporter reporter = PageManger.getReporter();
                    if (reporter != null) {
                        reporter.send(efsJSONLog);
                    }
                } catch (JSONException e3) {
                    e3.printStackTrace();
                }
            }
        }
    }

    public final void a() {
        if (this.g) {
            if (PageManger.isDebug) {
                Log.i("PageManger-PageFPSImpl", "state is start!");
                return;
            }
            return;
        }
        this.g = true;
        if (PageManger.isDebug) {
            Log.i("PageManger-PageFPSImpl", "start, page is " + this.j);
        }
        Choreographer.getInstance().removeFrameCallback(this.l);
        Choreographer.getInstance().postFrameCallback(this.l);
    }

    public final void b() {
        if (PageManger.isDebug) {
            Log.i("PageManger-PageFPSImpl", "stop, page is " + this.j);
        }
        c();
        this.g = false;
        this.h = 0L;
        this.b = 0L;
        this.c = 0;
        Map<String, Double> map = this.f;
        if (map != null) {
            map.clear();
        }
        this.d = 0;
    }
}
