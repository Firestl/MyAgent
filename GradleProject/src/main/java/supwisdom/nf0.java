package supwisdom;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.RecentlyNonNull;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public abstract class nf0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f8509a = 4225;
    public static final Object b = new Object();
    public static nf0 c;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.5.0 */
    public static final class a {
        public static final Uri f = new Uri.Builder().scheme("content").authority("com.google.android.gms.chimera").build();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f8510a;
        public final String b;
        public final ComponentName c;
        public final int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final boolean f8511e;

        public a(String str, String str2, int i, boolean z) {
            pf0.a(str);
            this.f8510a = str;
            pf0.a(str2);
            this.b = str2;
            this.c = null;
            this.d = i;
            this.f8511e = z;
        }

        public final String a() {
            return this.b;
        }

        public final ComponentName b() {
            return this.c;
        }

        public final int c() {
            return this.d;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return of0.a(this.f8510a, aVar.f8510a) && of0.a(this.b, aVar.b) && of0.a(this.c, aVar.c) && this.d == aVar.d && this.f8511e == aVar.f8511e;
        }

        public final int hashCode() {
            return of0.a(this.f8510a, this.b, this.c, Integer.valueOf(this.d), Boolean.valueOf(this.f8511e));
        }

        public final String toString() {
            String str = this.f8510a;
            if (str != null) {
                return str;
            }
            pf0.a(this.c);
            return this.c.flattenToString();
        }

        public final Intent a(Context context) {
            if (this.f8510a == null) {
                return new Intent().setComponent(this.c);
            }
            Intent intentB = this.f8511e ? b(context) : null;
            return intentB == null ? new Intent(this.f8510a).setPackage(this.b) : intentB;
        }

        public final Intent b(Context context) {
            Bundle bundleCall;
            Bundle bundle = new Bundle();
            bundle.putString("serviceActionBundleKey", this.f8510a);
            try {
                bundleCall = context.getContentResolver().call(f, "serviceIntentCall", (String) null, bundle);
            } catch (IllegalArgumentException e2) {
                String strValueOf = String.valueOf(e2);
                StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 34);
                sb.append("Dynamic intent resolution failed: ");
                sb.append(strValueOf);
                Log.w("ConnectionStatusConfig", sb.toString());
                bundleCall = null;
            }
            Intent intent = bundleCall != null ? (Intent) bundleCall.getParcelable("serviceResponseIntentKey") : null;
            if (intent == null) {
                String strValueOf2 = String.valueOf(this.f8510a);
                Log.w("ConnectionStatusConfig", strValueOf2.length() != 0 ? "Dynamic lookup for intent failed for action: ".concat(strValueOf2) : new String("Dynamic lookup for intent failed for action: "));
            }
            return intent;
        }
    }

    public static int a() {
        return f8509a;
    }

    public abstract boolean a(a aVar, ServiceConnection serviceConnection, String str);

    public abstract void b(a aVar, ServiceConnection serviceConnection, String str);

    @RecentlyNonNull
    public static nf0 a(@RecentlyNonNull Context context) {
        synchronized (b) {
            if (c == null) {
                c = new qg0(context.getApplicationContext());
            }
        }
        return c;
    }

    public final void a(@RecentlyNonNull String str, @RecentlyNonNull String str2, int i, @RecentlyNonNull ServiceConnection serviceConnection, @RecentlyNonNull String str3, boolean z) {
        b(new a(str, str2, i, z), serviceConnection, str3);
    }
}
