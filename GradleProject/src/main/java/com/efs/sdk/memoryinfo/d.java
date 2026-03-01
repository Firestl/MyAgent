package com.efs.sdk.memoryinfo;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.SystemClock;
import com.efs.sdk.base.EfsReporter;
import com.efs.sdk.base.integrationtesting.IntegrationTestingUtil;
import com.efs.sdk.base.observer.IConfigCallback;
import com.efs.sdk.base.samplingwhitelist.SamplingWhiteListUtil;
import com.umeng.commonsdk.utils.UMUtils;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public final class d implements UMMemoryMonitorApi {
    public boolean A;
    public boolean u;
    public boolean v = true;
    public b w;
    public WeakReference<Activity> x;
    public boolean y;
    public int z;

    @Override // com.efs.sdk.memoryinfo.UMMemoryMonitorApi
    public final String getCurrentActivity() {
        Activity activity;
        WeakReference<Activity> weakReference = this.x;
        return (weakReference == null || (activity = weakReference.get()) == null) ? "" : activity.getClass().getName();
    }

    @Override // com.efs.sdk.memoryinfo.UMMemoryMonitorApi
    public final boolean isEnable() {
        b bVar;
        return this.v && (bVar = this.w) != null && bVar.b;
    }

    @Override // com.efs.sdk.memoryinfo.UMMemoryMonitorApi
    public final boolean isForeground() {
        return this.y;
    }

    @Override // com.efs.sdk.memoryinfo.UMMemoryMonitorApi
    public final void onActivityResumed(Activity activity) {
        if (this.v) {
            this.x = new WeakReference<>(activity);
        }
    }

    @Override // com.efs.sdk.memoryinfo.UMMemoryMonitorApi
    public final void onActivityStarted(Activity activity) {
        if (this.v && activity != null) {
            if (this.A) {
                this.A = false;
                return;
            }
            int i = this.z + 1;
            this.z = i;
            if (i == 1) {
                this.y = true;
            }
        }
    }

    @Override // com.efs.sdk.memoryinfo.UMMemoryMonitorApi
    public final void onActivityStopped(Activity activity) {
        if (this.v && activity != null) {
            if (activity.isChangingConfigurations()) {
                this.A = true;
                return;
            }
            int i = this.z - 1;
            this.z = i;
            if (i == 0) {
                this.y = false;
            }
        }
    }

    @Override // com.efs.sdk.memoryinfo.UMMemoryMonitorApi
    public final void setEnable(boolean z) {
        this.v = z;
    }

    @Override // com.efs.sdk.memoryinfo.UMMemoryMonitorApi
    public final void start(Context context, EfsReporter efsReporter) {
        if ((this.v || IntegrationTestingUtil.isIntegrationTestingInPeriod()) && !this.u) {
            this.u = true;
            final b bVar = new b(context, efsReporter);
            this.w = bVar;
            bVar.f1884a.getAllSdkConfig(new String[]{"apm_memperf_sampling_rate", "apm_memperf_collect_interval", "apm_memperf_collect_max_period_sec"}, new IConfigCallback() { // from class: com.efs.sdk.memoryinfo.b.1

                /* JADX INFO: renamed from: com.efs.sdk.memoryinfo.b$1$1 */
                public class HandlerC00221 extends Handler {
                    public final /* synthetic */ HandlerThread d;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public HandlerC00221(Looper looper, HandlerThread handlerThread) {
                        super(looper);
                        handlerThread = handlerThread;
                    }

                    @Override // android.os.Handler
                    public final void handleMessage(Message message) {
                        super.handleMessage(message);
                        if (message.what == 1) {
                            try {
                                handlerThread.quit();
                            } catch (Throwable unused) {
                            }
                        }
                    }
                }

                /* JADX INFO: renamed from: com.efs.sdk.memoryinfo.b$1$2 */
                public class AnonymousClass2 implements Runnable {
                    public final /* synthetic */ Handler f;
                    public final /* synthetic */ int g;
                    public final /* synthetic */ int h;

                    public AnonymousClass2(Handler handler, int i, int i2) {
                        handler = handler;
                        i = i;
                        i = i2;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            String string = UUID.randomUUID().toString();
                            String lowerCase = UMUtils.MD5(Process.myPid() + string).toLowerCase();
                            b bVar = b.this;
                            Handler handler = handler;
                            handler.post(new Runnable() { // from class: com.efs.sdk.memoryinfo.b.2
                                public final /* synthetic */ Handler f;
                                public final /* synthetic */ long i;
                                public final /* synthetic */ int j;
                                public final /* synthetic */ e k;
                                public final /* synthetic */ String l;
                                public final /* synthetic */ int m;

                                public AnonymousClass2(long j, int i, Handler handler2, e eVar, String lowerCase2, int i2) {
                                    j = j;
                                    i = i;
                                    handler = handler2;
                                    eVar = eVar;
                                    str = lowerCase2;
                                    i = i2;
                                }

                                @Override // java.lang.Runnable
                                public final void run() {
                                    if (SystemClock.elapsedRealtime() - j > i * 1000) {
                                        handler.sendEmptyMessage(1);
                                        return;
                                    }
                                    try {
                                        b.a(b.this, eVar, str);
                                    } catch (Throwable th) {
                                        f.a("collect ", th);
                                    }
                                    handler.postDelayed(this, i * 1000);
                                }
                            });
                        } catch (Throwable unused) {
                            handler.sendEmptyMessage(1);
                        }
                    }
                }

                public AnonymousClass1() {
                }

                @Override // com.efs.sdk.base.observer.IConfigCallback
                public final void onChange(Map<String, Object> map) {
                    Object obj;
                    Object obj2;
                    Object obj3;
                    try {
                        if (b.this.b || (obj = map.get("apm_memperf_sampling_rate")) == null) {
                            return;
                        }
                        int i = Integer.parseInt(obj.toString());
                        if ((!(i != 0 && (i == 100 || SamplingWhiteListUtil.isHitWL() || new Random().nextInt(100) <= i)) && !IntegrationTestingUtil.isIntegrationTestingInPeriod()) || (obj2 = map.get("apm_memperf_collect_interval")) == null || (obj3 = map.get("apm_memperf_collect_max_period_sec")) == null) {
                            return;
                        }
                        int i2 = Integer.parseInt(obj2.toString());
                        int i3 = Integer.parseInt(obj3.toString());
                        HandlerThread handlerThread = new HandlerThread("mem-info");
                        handlerThread.start();
                        HandlerC00221 handlerC00221 = new Handler(handlerThread.getLooper()) { // from class: com.efs.sdk.memoryinfo.b.1.1
                            public final /* synthetic */ HandlerThread d;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            public HandlerC00221(Looper looper, HandlerThread handlerThread2) {
                                super(looper);
                                handlerThread = handlerThread2;
                            }

                            @Override // android.os.Handler
                            public final void handleMessage(Message message) {
                                super.handleMessage(message);
                                if (message.what == 1) {
                                    try {
                                        handlerThread.quit();
                                    } catch (Throwable unused) {
                                    }
                                }
                            }
                        };
                        handlerC00221.post(new Runnable() { // from class: com.efs.sdk.memoryinfo.b.1.2
                            public final /* synthetic */ Handler f;
                            public final /* synthetic */ int g;
                            public final /* synthetic */ int h;

                            public AnonymousClass2(Handler handlerC002212, int i22, int i32) {
                                handler = handlerC002212;
                                i = i22;
                                i = i32;
                            }

                            @Override // java.lang.Runnable
                            public final void run() {
                                try {
                                    String string = UUID.randomUUID().toString();
                                    String lowerCase2 = UMUtils.MD5(Process.myPid() + string).toLowerCase();
                                    b bVar2 = b.this;
                                    Handler handler2 = handler;
                                    handler2.post(new Runnable() { // from class: com.efs.sdk.memoryinfo.b.2
                                        public final /* synthetic */ Handler f;
                                        public final /* synthetic */ long i;
                                        public final /* synthetic */ int j;
                                        public final /* synthetic */ e k;
                                        public final /* synthetic */ String l;
                                        public final /* synthetic */ int m;

                                        public AnonymousClass2(long j, int i4, Handler handler22, e eVar, String lowerCase22, int i22) {
                                            j = j;
                                            i = i4;
                                            handler = handler22;
                                            eVar = eVar;
                                            str = lowerCase22;
                                            i = i22;
                                        }

                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            if (SystemClock.elapsedRealtime() - j > i * 1000) {
                                                handler.sendEmptyMessage(1);
                                                return;
                                            }
                                            try {
                                                b.a(b.this, eVar, str);
                                            } catch (Throwable th) {
                                                f.a("collect ", th);
                                            }
                                            handler.postDelayed(this, i * 1000);
                                        }
                                    });
                                } catch (Throwable unused) {
                                    handler.sendEmptyMessage(1);
                                }
                            }
                        });
                        b.this.b = true;
                    } catch (Throwable th) {
                        f.a("collect ", th);
                    }
                }
            });
        }
    }
}
