package supwisdom;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.DataSetObservable;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: compiled from: ActivityChooserModel.java */
/* JADX INFO: loaded from: classes.dex */
public class l2 extends DataSetObservable {
    public static final String n = l2.class.getSimpleName();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f8230a;
    public final List<a> b;
    public final List<c> c;
    public final Context d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f8231e;
    public Intent f;
    public b g;
    public int h;
    public boolean i;
    public boolean j;
    public boolean k;
    public boolean l;
    public d m;

    /* JADX INFO: compiled from: ActivityChooserModel.java */
    public static final class a implements Comparable<a> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ResolveInfo f8232a;
        public float b;

        public a(ResolveInfo resolveInfo) {
            this.f8232a = resolveInfo;
        }

        @Override // java.lang.Comparable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compareTo(a aVar) {
            return Float.floatToIntBits(aVar.b) - Float.floatToIntBits(this.b);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && a.class == obj.getClass() && Float.floatToIntBits(this.b) == Float.floatToIntBits(((a) obj).b);
        }

        public int hashCode() {
            return Float.floatToIntBits(this.b) + 31;
        }

        public String toString() {
            return Operators.ARRAY_START_STR + "resolveInfo:" + this.f8232a.toString() + "; weight:" + new BigDecimal(this.b) + Operators.ARRAY_END_STR;
        }
    }

    /* JADX INFO: compiled from: ActivityChooserModel.java */
    public interface b {
        void a(Intent intent, List<a> list, List<c> list2);
    }

    /* JADX INFO: compiled from: ActivityChooserModel.java */
    public static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ComponentName f8233a;
        public final long b;
        public final float c;

        public c(String str, long j, float f) {
            this(ComponentName.unflattenFromString(str), j, f);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || c.class != obj.getClass()) {
                return false;
            }
            c cVar = (c) obj;
            ComponentName componentName = this.f8233a;
            if (componentName == null) {
                if (cVar.f8233a != null) {
                    return false;
                }
            } else if (!componentName.equals(cVar.f8233a)) {
                return false;
            }
            return this.b == cVar.b && Float.floatToIntBits(this.c) == Float.floatToIntBits(cVar.c);
        }

        public int hashCode() {
            ComponentName componentName = this.f8233a;
            int iHashCode = componentName == null ? 0 : componentName.hashCode();
            long j = this.b;
            return ((((iHashCode + 31) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + Float.floatToIntBits(this.c);
        }

        public String toString() {
            return Operators.ARRAY_START_STR + "; activity:" + this.f8233a + "; time:" + this.b + "; weight:" + new BigDecimal(this.c) + Operators.ARRAY_END_STR;
        }

        public c(ComponentName componentName, long j, float f) {
            this.f8233a = componentName;
            this.b = j;
            this.c = f;
        }
    }

    /* JADX INFO: compiled from: ActivityChooserModel.java */
    public interface d {
        boolean a(l2 l2Var, Intent intent);
    }

    /* JADX INFO: compiled from: ActivityChooserModel.java */
    public final class e extends AsyncTask<Object, Void, Void> {
        public e() {
        }

        /* JADX WARN: Removed duplicated region for block: B:43:0x006f A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // android.os.AsyncTask
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Void doInBackground(java.lang.Object... r15) {
            /*
                Method dump skipped, instruction units count: 246
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: supwisdom.l2.e.doInBackground(java.lang.Object[]):java.lang.Void");
        }
    }

    static {
        new HashMap();
    }

    public int a(ResolveInfo resolveInfo) {
        synchronized (this.f8230a) {
            a();
            List<a> list = this.b;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (list.get(i).f8232a == resolveInfo) {
                    return i;
                }
            }
            return -1;
        }
    }

    public int b() {
        int size;
        synchronized (this.f8230a) {
            a();
            size = this.b.size();
        }
        return size;
    }

    public ResolveInfo c() {
        synchronized (this.f8230a) {
            a();
            if (this.b.isEmpty()) {
                return null;
            }
            return this.b.get(0).f8232a;
        }
    }

    public int d() {
        int size;
        synchronized (this.f8230a) {
            a();
            size = this.c.size();
        }
        return size;
    }

    public final boolean e() {
        if (!this.l || this.f == null) {
            return false;
        }
        this.l = false;
        this.b.clear();
        List<ResolveInfo> listQueryIntentActivities = this.d.getPackageManager().queryIntentActivities(this.f, 0);
        int size = listQueryIntentActivities.size();
        for (int i = 0; i < size; i++) {
            this.b.add(new a(listQueryIntentActivities.get(i)));
        }
        return true;
    }

    public final void f() {
        if (!this.j) {
            throw new IllegalStateException("No preceding call to #readHistoricalData");
        }
        if (this.k) {
            this.k = false;
            if (TextUtils.isEmpty(this.f8231e)) {
                return;
            }
            new e().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new ArrayList(this.c), this.f8231e);
        }
    }

    public final void g() {
        int size = this.c.size() - this.h;
        if (size <= 0) {
            return;
        }
        this.k = true;
        for (int i = 0; i < size; i++) {
            this.c.remove(0);
        }
    }

    public final boolean h() {
        if (!this.i || !this.k || TextUtils.isEmpty(this.f8231e)) {
            return false;
        }
        this.i = false;
        this.j = true;
        i();
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0038, code lost:
    
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:?, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void i() {
        /*
            r10 = this;
            java.lang.String r0 = "Error reading historical recrod file: "
            android.content.Context r1 = r10.d     // Catch: java.io.FileNotFoundException -> Lc2
            java.lang.String r2 = r10.f8231e     // Catch: java.io.FileNotFoundException -> Lc2
            java.io.FileInputStream r1 = r1.openFileInput(r2)     // Catch: java.io.FileNotFoundException -> Lc2
            org.xmlpull.v1.XmlPullParser r2 = android.util.Xml.newPullParser()     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86 org.xmlpull.v1.XmlPullParserException -> La0
            java.lang.String r3 = "UTF-8"
            r2.setInput(r1, r3)     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86 org.xmlpull.v1.XmlPullParserException -> La0
            r3 = 0
        L14:
            r4 = 1
            if (r3 == r4) goto L1f
            r5 = 2
            if (r3 == r5) goto L1f
            int r3 = r2.next()     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86 org.xmlpull.v1.XmlPullParserException -> La0
            goto L14
        L1f:
            java.lang.String r3 = "historical-records"
            java.lang.String r5 = r2.getName()     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86 org.xmlpull.v1.XmlPullParserException -> La0
            boolean r3 = r3.equals(r5)     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86 org.xmlpull.v1.XmlPullParserException -> La0
            if (r3 == 0) goto L7c
            java.util.List<supwisdom.l2$c> r3 = r10.c     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86 org.xmlpull.v1.XmlPullParserException -> La0
            r3.clear()     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86 org.xmlpull.v1.XmlPullParserException -> La0
        L30:
            int r5 = r2.next()     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86 org.xmlpull.v1.XmlPullParserException -> La0
            if (r5 != r4) goto L3d
            if (r1 == 0) goto Lbb
        L38:
            r1.close()     // Catch: java.io.IOException -> Lbb
            goto Lbb
        L3d:
            r6 = 3
            if (r5 == r6) goto L30
            r6 = 4
            if (r5 != r6) goto L44
            goto L30
        L44:
            java.lang.String r5 = r2.getName()     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86 org.xmlpull.v1.XmlPullParserException -> La0
            java.lang.String r6 = "historical-record"
            boolean r5 = r6.equals(r5)     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86 org.xmlpull.v1.XmlPullParserException -> La0
            if (r5 == 0) goto L74
            java.lang.String r5 = "activity"
            r6 = 0
            java.lang.String r5 = r2.getAttributeValue(r6, r5)     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86 org.xmlpull.v1.XmlPullParserException -> La0
            java.lang.String r7 = "time"
            java.lang.String r7 = r2.getAttributeValue(r6, r7)     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86 org.xmlpull.v1.XmlPullParserException -> La0
            long r7 = java.lang.Long.parseLong(r7)     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86 org.xmlpull.v1.XmlPullParserException -> La0
            java.lang.String r9 = "weight"
            java.lang.String r6 = r2.getAttributeValue(r6, r9)     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86 org.xmlpull.v1.XmlPullParserException -> La0
            float r6 = java.lang.Float.parseFloat(r6)     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86 org.xmlpull.v1.XmlPullParserException -> La0
            supwisdom.l2$c r9 = new supwisdom.l2$c     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86 org.xmlpull.v1.XmlPullParserException -> La0
            r9.<init>(r5, r7, r6)     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86 org.xmlpull.v1.XmlPullParserException -> La0
            r3.add(r9)     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86 org.xmlpull.v1.XmlPullParserException -> La0
            goto L30
        L74:
            org.xmlpull.v1.XmlPullParserException r2 = new org.xmlpull.v1.XmlPullParserException     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86 org.xmlpull.v1.XmlPullParserException -> La0
            java.lang.String r3 = "Share records file not well-formed."
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86 org.xmlpull.v1.XmlPullParserException -> La0
            throw r2     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86 org.xmlpull.v1.XmlPullParserException -> La0
        L7c:
            org.xmlpull.v1.XmlPullParserException r2 = new org.xmlpull.v1.XmlPullParserException     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86 org.xmlpull.v1.XmlPullParserException -> La0
            java.lang.String r3 = "Share records file does not start with historical-records tag."
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86 org.xmlpull.v1.XmlPullParserException -> La0
            throw r2     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86 org.xmlpull.v1.XmlPullParserException -> La0
        L84:
            r0 = move-exception
            goto Lbc
        L86:
            r2 = move-exception
            java.lang.String r3 = supwisdom.l2.n     // Catch: java.lang.Throwable -> L84
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L84
            r4.<init>()     // Catch: java.lang.Throwable -> L84
            r4.append(r0)     // Catch: java.lang.Throwable -> L84
            java.lang.String r0 = r10.f8231e     // Catch: java.lang.Throwable -> L84
            r4.append(r0)     // Catch: java.lang.Throwable -> L84
            java.lang.String r0 = r4.toString()     // Catch: java.lang.Throwable -> L84
            android.util.Log.e(r3, r0, r2)     // Catch: java.lang.Throwable -> L84
            if (r1 == 0) goto Lbb
            goto L38
        La0:
            r2 = move-exception
            java.lang.String r3 = supwisdom.l2.n     // Catch: java.lang.Throwable -> L84
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L84
            r4.<init>()     // Catch: java.lang.Throwable -> L84
            r4.append(r0)     // Catch: java.lang.Throwable -> L84
            java.lang.String r0 = r10.f8231e     // Catch: java.lang.Throwable -> L84
            r4.append(r0)     // Catch: java.lang.Throwable -> L84
            java.lang.String r0 = r4.toString()     // Catch: java.lang.Throwable -> L84
            android.util.Log.e(r3, r0, r2)     // Catch: java.lang.Throwable -> L84
            if (r1 == 0) goto Lbb
            goto L38
        Lbb:
            return
        Lbc:
            if (r1 == 0) goto Lc1
            r1.close()     // Catch: java.io.IOException -> Lc1
        Lc1:
            throw r0
        Lc2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.l2.i():void");
    }

    public final boolean j() {
        if (this.g == null || this.f == null || this.b.isEmpty() || this.c.isEmpty()) {
            return false;
        }
        this.g.a(this.f, this.b, Collections.unmodifiableList(this.c));
        return true;
    }

    public ResolveInfo b(int i) {
        ResolveInfo resolveInfo;
        synchronized (this.f8230a) {
            a();
            resolveInfo = this.b.get(i).f8232a;
        }
        return resolveInfo;
    }

    public void c(int i) {
        synchronized (this.f8230a) {
            a();
            a aVar = this.b.get(i);
            a aVar2 = this.b.get(0);
            a(new c(new ComponentName(aVar.f8232a.activityInfo.packageName, aVar.f8232a.activityInfo.name), System.currentTimeMillis(), aVar2 != null ? (aVar2.b - aVar.b) + 5.0f : 1.0f));
        }
    }

    public Intent a(int i) {
        synchronized (this.f8230a) {
            if (this.f == null) {
                return null;
            }
            a();
            a aVar = this.b.get(i);
            ComponentName componentName = new ComponentName(aVar.f8232a.activityInfo.packageName, aVar.f8232a.activityInfo.name);
            Intent intent = new Intent(this.f);
            intent.setComponent(componentName);
            if (this.m != null) {
                if (this.m.a(this, new Intent(intent))) {
                    return null;
                }
            }
            a(new c(componentName, System.currentTimeMillis(), 1.0f));
            return intent;
        }
    }

    public final void a() {
        boolean zE = e() | h();
        g();
        if (zE) {
            j();
            notifyChanged();
        }
    }

    public final boolean a(c cVar) {
        boolean zAdd = this.c.add(cVar);
        if (zAdd) {
            this.k = true;
            g();
            f();
            j();
            notifyChanged();
        }
        return zAdd;
    }
}
