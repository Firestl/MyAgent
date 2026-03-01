package com.efs.sdk.pa.a;

import android.app.Application;
import android.content.Context;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import com.efs.sdk.base.integrationtesting.IntegrationTestingUtil;
import com.efs.sdk.pa.PA;
import com.efs.sdk.pa.PAANRListener;
import com.efs.sdk.pa.PAMsgListener;
import com.efs.sdk.pa.a.b;
import com.efs.sdk.pa.a.g;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

/* JADX INFO: loaded from: classes.dex */
public final class c implements PA {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f1923a;
    public e c;
    public f d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public a f1924e;
    public boolean h;
    public boolean i;
    public Looper b = Looper.myLooper();
    public b f = new b();
    public g g = new g();

    public c(boolean z) {
        this.i = z;
    }

    @Override // com.efs.sdk.pa.PA
    public final void enableDumpToFile(String str) {
        f fVar = this.d;
        if (fVar == null || str == null || str.trim().length() == 0) {
            return;
        }
        fVar.c = str;
        if (fVar.d != null) {
            return;
        }
        FileOutputStream fileOutputStream = null;
        try {
            FileOutputStream fileOutputStream2 = new FileOutputStream(str);
            try {
                fVar.d = new BufferedOutputStream(fileOutputStream2);
            } catch (Exception unused) {
                fileOutputStream = fileOutputStream2;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception unused2) {
                    }
                }
            }
        } catch (Exception unused3) {
        }
    }

    @Override // com.efs.sdk.pa.PA
    public final void enableLog(boolean z) {
        this.f1923a = z;
        this.f.b = z;
        this.g.b = z;
        f fVar = this.d;
        if (fVar != null) {
            fVar.b = z;
        }
    }

    @Override // com.efs.sdk.pa.PA
    public final int endCalFPS(String str) {
        if (!this.h) {
            return -1;
        }
        b bVar = this.f;
        if (str != null && str.trim().length() != 0) {
            b.a aVar = bVar.f1920a.get(str);
            if (aVar == null) {
                return 0;
            }
            View view = aVar.d;
            if (view != null && aVar.c != null) {
                view.getViewTreeObserver().removeOnPreDrawListener(aVar.c);
            }
            bVar.f1920a.remove(str);
            int iCurrentTimeMillis = (int) (aVar.b / ((System.currentTimeMillis() - aVar.f1921a) / 1000.0f));
            i = iCurrentTimeMillis > 0 ? iCurrentTimeMillis : 0;
            if (bVar.b) {
                Log.e("PerformanceAnalyze", "key=" + str + ",fps=" + i);
            }
        }
        return i;
    }

    @Override // com.efs.sdk.pa.PA
    public final long endCalTime(String str) {
        if (!this.h) {
            return -1L;
        }
        g gVar = this.g;
        long jCurrentTimeMillis = 0;
        if (str != null && str.trim().length() != 0) {
            g.a aVar = gVar.f1928a.get(str);
            if (aVar == null) {
                return 0L;
            }
            gVar.f1928a.remove(str);
            jCurrentTimeMillis = System.currentTimeMillis() - aVar.f1929a;
            if (gVar.b) {
                Log.e("PerformanceAnalyze", "key=" + str + ",consumeTime=" + jCurrentTimeMillis);
            }
        }
        return jCurrentTimeMillis;
    }

    @Override // com.efs.sdk.pa.PA
    public final void registerPAANRListener(Context context, PAANRListener pAANRListener) {
        registerPAANRListener(context, pAANRListener, 2000L);
    }

    @Override // com.efs.sdk.pa.PA
    public final void registerPAMsgListener(PAMsgListener pAMsgListener) {
        if (this.c == null) {
            this.c = new e();
        }
        this.b.setMessageLogging(this.c);
        if (this.d == null) {
            this.d = new f();
        }
        f fVar = this.d;
        fVar.b = this.f1923a;
        fVar.f1927a = pAMsgListener;
        this.c.f1925a.add(fVar);
    }

    @Override // com.efs.sdk.pa.PA
    public final void start() {
        if (this.i || IntegrationTestingUtil.isIntegrationTestingInPeriod()) {
            this.h = true;
            e eVar = this.c;
            if (eVar != null) {
                this.b.setMessageLogging(eVar);
            }
            a aVar = this.f1924e;
            if (aVar == null || !aVar.f) {
                return;
            }
            aVar.f = false;
            aVar.g.post(aVar.m);
            aVar.j = SystemClock.uptimeMillis();
        }
    }

    @Override // com.efs.sdk.pa.PA
    public final void startCalFPS(String str, View view) {
        if (this.h) {
            b bVar = this.f;
            if (str == null || str.trim().length() == 0 || view == null || bVar.f1920a.get(str) != null) {
                return;
            }
            b.a aVar = new b.a((byte) 0);
            if (view != null) {
                aVar.d = view;
                b.a.AnonymousClass1 anonymousClass1 = new ViewTreeObserver.OnPreDrawListener() { // from class: com.efs.sdk.pa.a.b.a.1
                    public AnonymousClass1() {
                    }

                    @Override // android.view.ViewTreeObserver.OnPreDrawListener
                    public final boolean onPreDraw() {
                        a.this.b++;
                        return true;
                    }
                };
                aVar.c = anonymousClass1;
                aVar.d.getViewTreeObserver().addOnPreDrawListener(anonymousClass1);
                aVar.f1921a = System.currentTimeMillis();
            }
            bVar.f1920a.put(str, aVar);
        }
    }

    @Override // com.efs.sdk.pa.PA
    public final void startCalTime(String str) {
        if (this.h) {
            g gVar = this.g;
            if (str == null || str.trim().length() == 0 || gVar.f1928a.get(str) != null) {
                return;
            }
            g.a aVar = new g.a((byte) 0);
            aVar.f1929a = System.currentTimeMillis();
            gVar.f1928a.put(str, aVar);
        }
    }

    @Override // com.efs.sdk.pa.PA
    public final void stop() {
        this.h = false;
        this.b.setMessageLogging(null);
        a aVar = this.f1924e;
        if (aVar != null) {
            aVar.f = true;
            aVar.g.removeCallbacksAndMessages(null);
            aVar.f1916a = true;
        }
    }

    @Override // com.efs.sdk.pa.PA
    public final void unRegisterPAMsgListener() {
        f fVar = this.d;
        if (fVar != null) {
            fVar.f1927a = null;
        }
        e eVar = this.c;
        if (eVar != null) {
            eVar.f1925a.remove(this.d);
        }
    }

    @Override // com.efs.sdk.pa.PA
    public final void unregisterPAANRListener() {
    }

    @Override // com.efs.sdk.pa.PA
    public final void registerPAANRListener(Context context, PAANRListener pAANRListener, long j) {
        registerPAANRListener(context, pAANRListener, j, Looper.getMainLooper().getThread());
    }

    @Override // com.efs.sdk.pa.PA
    public final void registerPAANRListener(Context context, PAANRListener pAANRListener, long j, Thread thread) {
        if (this.f1924e == null) {
            if (thread != null) {
                this.f1924e = new a((Application) context.getApplicationContext(), j);
            } else {
                this.f1924e = new a((Application) context.getApplicationContext(), j, false);
            }
        }
        this.f1924e.h = pAANRListener;
    }
}
