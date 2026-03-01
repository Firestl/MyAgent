package supwisdom;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.widget.RemoteViews;
import androidx.core.graphics.drawable.IconCompat;
import java.util.ArrayList;

/* JADX INFO: compiled from: NotificationCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class q7 {

    /* JADX INFO: compiled from: NotificationCompat.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Bundle f8882a;
        public IconCompat b;
        public final v7[] c;
        public final v7[] d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f8883e;
        public boolean f;
        public final int g;
        public final boolean h;

        @Deprecated
        public int i;
        public CharSequence j;
        public PendingIntent k;

        public a(int i, CharSequence charSequence, PendingIntent pendingIntent) {
            this(i != 0 ? IconCompat.a(null, "", i) : null, charSequence, pendingIntent);
        }

        public PendingIntent a() {
            return this.k;
        }

        public boolean b() {
            return this.f8883e;
        }

        public v7[] c() {
            return this.d;
        }

        public Bundle d() {
            return this.f8882a;
        }

        public IconCompat e() {
            int i;
            if (this.b == null && (i = this.i) != 0) {
                this.b = IconCompat.a(null, "", i);
            }
            return this.b;
        }

        public v7[] f() {
            return this.c;
        }

        public int g() {
            return this.g;
        }

        public boolean h() {
            return this.f;
        }

        public CharSequence i() {
            return this.j;
        }

        public boolean j() {
            return this.h;
        }

        public a(IconCompat iconCompat, CharSequence charSequence, PendingIntent pendingIntent) {
            this(iconCompat, charSequence, pendingIntent, new Bundle(), null, null, true, 0, true, false);
        }

        public a(IconCompat iconCompat, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle, v7[] v7VarArr, v7[] v7VarArr2, boolean z, int i, boolean z2, boolean z3) {
            this.f = true;
            this.b = iconCompat;
            if (iconCompat != null && iconCompat.c() == 2) {
                this.i = iconCompat.a();
            }
            this.j = d.d(charSequence);
            this.k = pendingIntent;
            this.f8882a = bundle == null ? new Bundle() : bundle;
            this.c = v7VarArr;
            this.d = v7VarArr2;
            this.f8883e = z;
            this.g = i;
            this.f = z2;
            this.h = z3;
        }
    }

    /* JADX INFO: compiled from: NotificationCompat.java */
    public static class b extends e {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public CharSequence f8884e;

        @Override // supwisdom.q7.e
        public String a() {
            return "androidx.core.app.NotificationCompat$BigTextStyle";
        }

        public b a(CharSequence charSequence) {
            this.f8884e = d.d(charSequence);
            return this;
        }

        @Override // supwisdom.q7.e
        public void a(p7 p7Var) {
            if (Build.VERSION.SDK_INT >= 16) {
                Notification.BigTextStyle bigTextStyleBigText = new Notification.BigTextStyle(p7Var.a()).setBigContentTitle(this.b).bigText(this.f8884e);
                if (this.d) {
                    bigTextStyleBigText.setSummaryText(this.c);
                }
            }
        }

        @Override // supwisdom.q7.e
        public void a(Bundle bundle) {
            super.a(bundle);
            if (Build.VERSION.SDK_INT < 21) {
                bundle.putCharSequence("android.bigText", this.f8884e);
            }
        }
    }

    /* JADX INFO: compiled from: NotificationCompat.java */
    public static final class c {

        /* JADX INFO: compiled from: NotificationCompat.java */
        public static class a {
            public static Notification.BubbleMetadata a(c cVar) {
                if (cVar == null) {
                    return null;
                }
                cVar.a();
                throw null;
            }
        }

        /* JADX INFO: compiled from: NotificationCompat.java */
        public static class b {
            public static Notification.BubbleMetadata a(c cVar) {
                if (cVar == null) {
                    return null;
                }
                cVar.b();
                throw null;
            }
        }

        public static Notification.BubbleMetadata a(c cVar) {
            if (cVar == null) {
                return null;
            }
            int i = Build.VERSION.SDK_INT;
            if (i >= 30) {
                return b.a(cVar);
            }
            if (i == 29) {
                return a.a(cVar);
            }
            return null;
        }

        @SuppressLint({"InvalidNullConversion"})
        public PendingIntent a() {
            throw null;
        }

        public String b() {
            throw null;
        }
    }

    /* JADX INFO: compiled from: NotificationCompat.java */
    public static class d {
        public boolean A;
        public boolean B;
        public String C;
        public Bundle D;
        public int E;
        public int F;
        public Notification G;
        public RemoteViews H;
        public RemoteViews I;
        public RemoteViews J;
        public String K;
        public int L;
        public String M;
        public z7 N;
        public long O;
        public int P;
        public boolean Q;
        public c R;
        public Notification S;
        public boolean T;
        public Icon U;

        @Deprecated
        public ArrayList<String> V;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Context f8885a;
        public ArrayList<a> b;
        public ArrayList<u7> c;
        public ArrayList<a> d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public CharSequence f8886e;
        public CharSequence f;
        public PendingIntent g;
        public PendingIntent h;
        public RemoteViews i;
        public Bitmap j;
        public CharSequence k;
        public int l;
        public int m;
        public boolean n;
        public boolean o;
        public e p;
        public CharSequence q;
        public CharSequence r;
        public CharSequence[] s;
        public int t;
        public int u;
        public boolean v;
        public String w;
        public boolean x;
        public String y;
        public boolean z;

        public d(Context context, String str) {
            this.b = new ArrayList<>();
            this.c = new ArrayList<>();
            this.d = new ArrayList<>();
            this.n = true;
            this.z = false;
            this.E = 0;
            this.F = 0;
            this.L = 0;
            this.P = 0;
            Notification notification = new Notification();
            this.S = notification;
            this.f8885a = context;
            this.K = str;
            notification.when = System.currentTimeMillis();
            this.S.audioStreamType = -1;
            this.m = 0;
            this.V = new ArrayList<>();
            this.Q = true;
        }

        public static CharSequence d(CharSequence charSequence) {
            return (charSequence != null && charSequence.length() > 5120) ? charSequence.subSequence(0, 5120) : charSequence;
        }

        public d a(long j) {
            this.S.when = j;
            return this;
        }

        public d b(int i) {
            this.S.icon = i;
            return this;
        }

        public d c(CharSequence charSequence) {
            this.S.tickerText = d(charSequence);
            return this;
        }

        public d a(CharSequence charSequence) {
            this.f = d(charSequence);
            return this;
        }

        public d b(CharSequence charSequence) {
            this.f8886e = d(charSequence);
            return this;
        }

        public d a(int i, int i2, boolean z) {
            this.t = i;
            this.u = i2;
            this.v = z;
            return this;
        }

        public d b(boolean z) {
            this.z = z;
            return this;
        }

        public Bundle b() {
            if (this.D == null) {
                this.D = new Bundle();
            }
            return this.D;
        }

        public d a(PendingIntent pendingIntent) {
            this.g = pendingIntent;
            return this;
        }

        public d a(boolean z) {
            a(16, z);
            return this;
        }

        public final void a(int i, boolean z) {
            if (z) {
                Notification notification = this.S;
                notification.flags = i | notification.flags;
            } else {
                Notification notification2 = this.S;
                notification2.flags = (~i) & notification2.flags;
            }
        }

        public d a(int i) {
            this.m = i;
            return this;
        }

        public d a(int i, CharSequence charSequence, PendingIntent pendingIntent) {
            this.b.add(new a(i, charSequence, pendingIntent));
            return this;
        }

        public d a(e eVar) {
            if (this.p != eVar) {
                this.p = eVar;
                if (eVar != null) {
                    eVar.a(this);
                }
            }
            return this;
        }

        public d a(String str) {
            this.K = str;
            return this;
        }

        public Notification a() {
            return new r7(this).b();
        }

        @Deprecated
        public d(Context context) {
            this(context, null);
        }
    }

    public static Bundle a(Notification notification) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 19) {
            return notification.extras;
        }
        if (i >= 16) {
            return s7.a(notification);
        }
        return null;
    }

    /* JADX INFO: compiled from: NotificationCompat.java */
    public static abstract class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public d f8887a;
        public CharSequence b;
        public CharSequence c;
        public boolean d = false;

        public abstract String a();

        public abstract void a(p7 p7Var);

        public void a(d dVar) {
            if (this.f8887a != dVar) {
                this.f8887a = dVar;
                if (dVar != null) {
                    dVar.a(this);
                }
            }
        }

        public RemoteViews b(p7 p7Var) {
            return null;
        }

        public RemoteViews c(p7 p7Var) {
            return null;
        }

        public RemoteViews d(p7 p7Var) {
            return null;
        }

        public void a(Bundle bundle) {
            if (this.d) {
                bundle.putCharSequence("android.summaryText", this.c);
            }
            CharSequence charSequence = this.b;
            if (charSequence != null) {
                bundle.putCharSequence("android.title.big", charSequence);
            }
            String strA = a();
            if (strA != null) {
                bundle.putString("androidx.core.app.extra.COMPAT_TEMPLATE", strA);
            }
        }
    }
}
