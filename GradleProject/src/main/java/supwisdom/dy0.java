package supwisdom;

import com.qiniu.android.dns.NetworkInfo;
import com.qiniu.android.dns.Record;
import com.qiniu.android.dns.util.LruCache;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: DnsManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class dy0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final fy0[] f7395a;
    public final LruCache<String, Record[]> b;
    public final jy0 c;
    public final gy0 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile NetworkInfo f7396e;
    public volatile int f;
    public c g;

    /* JADX INFO: compiled from: DnsManager.java */
    public static class b implements gy0 {
        public b() {
            new AtomicInteger();
        }

        @Override // supwisdom.gy0
        public String[] a(String[] strArr) {
            return strArr;
        }
    }

    /* JADX INFO: compiled from: DnsManager.java */
    public interface c {
        void a(Exception exc, String str);
    }

    public dy0(NetworkInfo networkInfo, fy0[] fy0VarArr) {
        this(networkInfo, fy0VarArr, null);
    }

    public static String[] a(Record[] recordArr) {
        if (recordArr == null || recordArr.length == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(recordArr.length);
        for (Record record : recordArr) {
            arrayList.add(record.f3868a);
        }
        if (arrayList.size() == 0) {
            return null;
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static void b(Record[] recordArr) {
        if (recordArr == null || recordArr.length <= 1) {
            return;
        }
        Record record = recordArr[0];
        System.arraycopy(recordArr, 1, recordArr, 0, recordArr.length - 1);
        recordArr[recordArr.length - 1] = record;
    }

    public static Record[] c(Record[] recordArr) {
        ArrayList arrayList = new ArrayList(recordArr.length);
        for (Record record : recordArr) {
            if (record != null && record.b == 1) {
                arrayList.add(record);
            }
        }
        return (Record[]) arrayList.toArray(new Record[arrayList.size()]);
    }

    public dy0(NetworkInfo networkInfo, fy0[] fy0VarArr, gy0 gy0Var) {
        this.c = new jy0();
        this.f7396e = null;
        this.f = 0;
        this.f7396e = networkInfo == null ? NetworkInfo.c : networkInfo;
        this.f7395a = (fy0[]) fy0VarArr.clone();
        this.b = new LruCache<>();
        this.d = gy0Var == null ? new b() : gy0Var;
    }

    public static boolean b(String str) {
        if (str == null || str.length() < 7 || str.length() > 15 || str.contains("-")) {
            return false;
        }
        try {
            int iIndexOf = str.indexOf(46);
            if (iIndexOf != -1 && Integer.parseInt(str.substring(0, iIndexOf)) > 255) {
                return false;
            }
            int i = iIndexOf + 1;
            int iIndexOf2 = str.indexOf(46, i);
            if (iIndexOf2 != -1 && Integer.parseInt(str.substring(i, iIndexOf2)) > 255) {
                return false;
            }
            int i2 = iIndexOf2 + 1;
            int iIndexOf3 = str.indexOf(46, i2);
            if (iIndexOf3 != -1 && Integer.parseInt(str.substring(i2, iIndexOf3)) > 255 && Integer.parseInt(str.substring(iIndexOf3 + 1, str.length() - 1)) > 255) {
                if (str.charAt(str.length() - 1) != '.') {
                    return false;
                }
            }
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x00ec A[EDGE_INSN: B:123:0x00ec->B:76:0x00ec BREAK  A[LOOP:1: B:40:0x007c->B:75:0x00e9], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0081  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.qiniu.android.dns.Record[] c(supwisdom.ey0 r12) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 346
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.dy0.c(supwisdom.ey0):com.qiniu.android.dns.Record[]");
    }

    public String[] a(String str) throws IOException {
        return a(new ey0(str));
    }

    public String[] a(ey0 ey0Var) throws IOException {
        if (ey0Var != null) {
            String str = ey0Var.f7544a;
            if (str != null && str.trim().length() != 0) {
                if (b(ey0Var.f7544a)) {
                    return new String[]{ey0Var.f7544a};
                }
                String[] strArrB = b(ey0Var);
                if (strArrB != null && strArrB.length > 1) {
                    this.d.a(strArrB);
                }
                return strArrB;
            }
            throw new IOException("empty domain " + ey0Var.f7544a);
        }
        throw new IOException("null domain");
    }

    public final String[] b(ey0 ey0Var) throws IOException {
        Record[] recordArrC = c(ey0Var);
        if (recordArrC == null || recordArrC.length == 0) {
            return null;
        }
        return a(recordArrC);
    }

    public void a(NetworkInfo networkInfo) {
        a();
        if (networkInfo == null) {
            networkInfo = NetworkInfo.c;
        }
        this.f7396e = networkInfo;
        synchronized (this.f7395a) {
            this.f = 0;
        }
    }

    public final void a() {
        synchronized (this.b) {
            this.b.clear();
        }
    }
}
