package supwisdom;

import android.net.Uri;
import com.taobao.weex.el.parse.Operators;
import java.util.Arrays;

/* JADX INFO: compiled from: DataSpec.java */
/* JADX INFO: loaded from: classes.dex */
public final class u70 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Uri f9382a;
    public final byte[] b;
    public final long c;
    public final long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f9383e;
    public final String f;
    public final int g;

    public u70(Uri uri, int i) {
        this(uri, 0L, -1L, null, i);
    }

    public boolean a(int i) {
        return (this.g & i) == i;
    }

    public String toString() {
        return "DataSpec[" + this.f9382a + ", " + Arrays.toString(this.b) + ", " + this.c + ", " + this.d + ", " + this.f9383e + ", " + this.f + ", " + this.g + Operators.ARRAY_END_STR;
    }

    public u70(Uri uri, long j, long j2, String str) {
        this(uri, j, j, j2, str, 0);
    }

    public u70(Uri uri, long j, long j2, String str, int i) {
        this(uri, j, j, j2, str, i);
    }

    public u70(Uri uri, long j, long j2, long j3, String str, int i) {
        this(uri, null, j, j2, j3, str, i);
    }

    public u70(Uri uri, byte[] bArr, long j, long j2, long j3, String str, int i) {
        boolean z = true;
        e80.a(j >= 0);
        e80.a(j2 >= 0);
        if (j3 <= 0 && j3 != -1) {
            z = false;
        }
        e80.a(z);
        this.f9382a = uri;
        this.b = bArr;
        this.c = j;
        this.d = j2;
        this.f9383e = j3;
        this.f = str;
        this.g = i;
    }
}
