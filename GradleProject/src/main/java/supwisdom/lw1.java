package supwisdom;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import com.huawei.hms.framework.network.grs.local.model.CountryCodeBean;
import com.igexin.assist.util.AssistUtils;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: IdentifierIdClient.java */
/* JADX INFO: loaded from: classes3.dex */
public class lw1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Object f8322a = new Object();
    public static Context b = null;
    public static boolean c = false;
    public static mw1 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static mw1 f8323e;
    public static mw1 f;
    public static HandlerThread g;
    public static Handler h;
    public static String i;
    public static String j;
    public static String k;
    public static volatile lw1 l;
    public static volatile kw1 m;
    public static int n;
    public static int o;
    public static int p;
    public static int q;
    public static int r;
    public static int s;
    public static int t;
    public static int u;
    public static int v;
    public static int w;
    public static int x;
    public static int y;

    /* JADX INFO: compiled from: IdentifierIdClient.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (lw1.n + lw1.o + lw1.t + lw1.w + lw1.p + lw1.q + lw1.v + lw1.w + lw1.r + lw1.s + lw1.x + lw1.y > 0) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(com.umeng.commonsdk.statistics.idtracking.i.d, lw1.this.a(lw1.n, lw1.o, lw1.t, lw1.u));
                contentValues.put("vaid", lw1.this.a(lw1.p, lw1.q, lw1.v, lw1.w));
                contentValues.put("aaid", lw1.this.a(lw1.r, lw1.s, lw1.x, lw1.y));
                lw1.m.a(7, AssistUtils.BRAND_VIVO, new ContentValues[]{contentValues});
                int unused = lw1.s = 0;
                int unused2 = lw1.r = 0;
                int unused3 = lw1.q = 0;
                int unused4 = lw1.p = 0;
                int unused5 = lw1.o = 0;
                int unused6 = lw1.n = 0;
                int unused7 = lw1.y = 0;
                int unused8 = lw1.x = 0;
                int unused9 = lw1.w = 0;
                int unused10 = lw1.v = 0;
                int unused11 = lw1.u = 0;
                int unused12 = lw1.t = 0;
            }
        }
    }

    /* JADX INFO: compiled from: IdentifierIdClient.java */
    public class b extends Handler {
        public b(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) throws Throwable {
            if (message.what != 11) {
                Log.e("VMS_SDK_Client", "message type valid");
                return;
            }
            int i = message.getData().getInt("type");
            try {
                String strA = lw1.m.a(i, message.getData().getString("appid"));
                if (i == 0) {
                    String unused = lw1.i = strA;
                    lw1.d(8, lw1.i);
                } else if (i == 1) {
                    if (strA != null) {
                        String unused2 = lw1.j = strA;
                    } else {
                        Log.e("VMS_SDK_Client", "get vaid failed");
                    }
                    lw1.d(9, lw1.j);
                } else if (i == 2) {
                    if (strA != null) {
                        String unused3 = lw1.k = strA;
                    } else {
                        Log.e("VMS_SDK_Client", "get aaid failed");
                    }
                    lw1.d(10, lw1.k);
                } else if (i != 3) {
                    if (i == 4) {
                        lw1.e(strA);
                    } else if (i == 5) {
                        if (strA != null) {
                            lw1.f(strA);
                        } else {
                            Log.e("VMS_SDK_Client", "get guid failed");
                        }
                    }
                } else if (strA != null) {
                    lw1.d(strA);
                } else {
                    Log.e("VMS_SDK_Client", "get udid failed");
                }
            } catch (Exception e2) {
                Log.e("VMS_SDK_Client", "readException:" + e2.toString());
            }
            synchronized (lw1.f8322a) {
                lw1.f8322a.notify();
            }
        }
    }

    public lw1() {
        e();
        m = new kw1(b);
        c(b);
    }

    public static /* synthetic */ String d(String str) {
        return str;
    }

    public static /* synthetic */ String e(String str) {
        return str;
    }

    public static /* synthetic */ String f(String str) {
        return str;
    }

    public static void e() {
        HandlerThread handlerThread = new HandlerThread("SqlWorkThread");
        g = handlerThread;
        handlerThread.start();
        h = new b(g.getLooper());
    }

    public static void f() {
        c = "1".equals(a("persist.sys.identifierid.supported", "0")) || "1".equals(a("persist.sys.identifierid", "0"));
    }

    public static boolean g() {
        if (!c) {
            f();
        }
        return c;
    }

    public static lw1 b(Context context) {
        if (g()) {
            return a(context);
        }
        return null;
    }

    public static void d(int i2, String str) {
        if (i2 == 0) {
            if (str == null) {
                o++;
                return;
            }
            n++;
        }
        if (i2 == 1) {
            if (str == null) {
                q++;
                return;
            } else {
                p++;
                return;
            }
        }
        if (i2 == 2) {
            if (str == null) {
                s++;
                return;
            } else {
                r++;
                return;
            }
        }
        switch (i2) {
            case 8:
                if (str != null) {
                    t++;
                } else {
                    u++;
                }
                break;
            case 9:
                if (str != null) {
                    v++;
                } else {
                    w++;
                }
                break;
            case 10:
                if (str != null) {
                    x++;
                } else {
                    y++;
                }
                break;
        }
    }

    public final void a() {
        Executors.newScheduledThreadPool(1).scheduleWithFixedDelay(new a(), 600L, 600L, TimeUnit.SECONDS);
    }

    public static int c(Context context) {
        try {
            return context.getPackageManager().getPackageInfo("com.vivo.vms", 0).versionCode;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public String b() {
        String str = i;
        if (str != null) {
            d(0, str);
            return i;
        }
        b(0, null);
        if (d == null) {
            a(b, 0, null);
        }
        d(0, i);
        return i;
    }

    public final String a(int i2, int i3, int i4, int i5) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(i2);
        stringBuffer.append(",");
        stringBuffer.append(i3);
        stringBuffer.append(";");
        stringBuffer.append(i4);
        stringBuffer.append(",");
        stringBuffer.append(i5);
        return stringBuffer.toString();
    }

    public static lw1 a(Context context) {
        if (b == null) {
            if (context == null) {
                return null;
            }
            Context applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                context = applicationContext;
            }
            b = context;
        }
        if (l == null) {
            synchronized (lw1.class) {
                if (l == null) {
                    l = new lw1();
                    l.a();
                }
            }
        }
        return l;
    }

    public final void b(int i2, String str) {
        synchronized (f8322a) {
            a(i2, str);
            long jUptimeMillis = SystemClock.uptimeMillis();
            try {
                f8322a.wait(2000L);
            } catch (InterruptedException unused) {
                Log.e("VMS_SDK_Client", "queryId: lock error");
            }
            if (SystemClock.uptimeMillis() - jUptimeMillis >= 2000) {
                Log.d("VMS_SDK_Client", "query timeout");
            }
        }
    }

    public void a(int i2, String str) {
        Message messageObtainMessage = h.obtainMessage();
        messageObtainMessage.what = 11;
        Bundle bundle = new Bundle();
        bundle.putInt("type", i2);
        if (i2 == 1 || i2 == 2 || i2 == 6) {
            bundle.putString("appid", str);
        }
        messageObtainMessage.setData(bundle);
        h.sendMessage(messageObtainMessage);
    }

    public static String a(String str, String str2) {
        try {
            try {
                Class<?> cls = Class.forName(CountryCodeBean.ANDRIOD_SYSTEMPROP);
                return (String) cls.getMethod("get", String.class, String.class).invoke(cls, str, "0");
            } catch (Exception e2) {
                Log.e("VMS_SDK_Client", "getProperty: invoke is error" + e2.getMessage());
                return str2;
            }
        } catch (Throwable unused) {
            return str2;
        }
    }

    public static synchronized void a(Context context, int i2, String str) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 == 2) {
                    if (f == null) {
                        f = new mw1(l, 2, str);
                        context.getContentResolver().registerContentObserver(Uri.parse("content://com.vivo.vms.IdProvider/IdentifierId/" + context.getPackageName()), false, f);
                    }
                }
            } else if (f8323e == null) {
                f8323e = new mw1(l, 1, str);
                context.getContentResolver().registerContentObserver(Uri.parse("content://com.vivo.vms.IdProvider/IdentifierId/VAID_" + str), false, f8323e);
            }
        } else if (d == null) {
            d = new mw1(l, 0, null);
            context.getContentResolver().registerContentObserver(Uri.parse("content://com.vivo.vms.IdProvider/IdentifierId/OAID"), true, d);
        }
    }
}
