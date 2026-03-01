package supwisdom;

import android.text.SpannableStringBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: WebvttSubtitle.java */
/* JADX INFO: loaded from: classes.dex */
public final class y60 implements m60 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<u60> f9849a;
    public final int b;
    public final long[] c;
    public final long[] d;

    public y60(List<u60> list) {
        this.f9849a = list;
        int size = list.size();
        this.b = size;
        this.c = new long[size * 2];
        for (int i = 0; i < this.b; i++) {
            u60 u60Var = list.get(i);
            int i2 = i * 2;
            long[] jArr = this.c;
            jArr[i2] = u60Var.f9374e;
            jArr[i2 + 1] = u60Var.f;
        }
        long[] jArr2 = this.c;
        long[] jArrCopyOf = Arrays.copyOf(jArr2, jArr2.length);
        this.d = jArrCopyOf;
        Arrays.sort(jArrCopyOf);
    }

    @Override // supwisdom.m60
    public int a(long j) {
        int iB = x80.b(this.d, j, false, false);
        if (iB < this.d.length) {
            return iB;
        }
        return -1;
    }

    @Override // supwisdom.m60
    public int b() {
        return this.d.length;
    }

    @Override // supwisdom.m60
    public List<y50> b(long j) {
        SpannableStringBuilder spannableStringBuilder = null;
        u60 u60Var = null;
        ArrayList arrayList = null;
        for (int i = 0; i < this.b; i++) {
            long[] jArr = this.c;
            int i2 = i * 2;
            if (jArr[i2] <= j && j < jArr[i2 + 1]) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                u60 u60Var2 = this.f9849a.get(i);
                if (!u60Var2.a()) {
                    arrayList.add(u60Var2);
                } else if (u60Var == null) {
                    u60Var = u60Var2;
                } else if (spannableStringBuilder == null) {
                    spannableStringBuilder = new SpannableStringBuilder();
                    spannableStringBuilder.append(u60Var.f9847a).append((CharSequence) "\n").append(u60Var2.f9847a);
                } else {
                    spannableStringBuilder.append((CharSequence) "\n").append(u60Var2.f9847a);
                }
            }
        }
        if (spannableStringBuilder != null) {
            arrayList.add(new u60(spannableStringBuilder));
        } else if (u60Var != null) {
            arrayList.add(u60Var);
        }
        return arrayList != null ? arrayList : Collections.emptyList();
    }

    @Override // supwisdom.m60
    public long a(int i) {
        e80.a(i >= 0);
        e80.a(i < this.d.length);
        return this.d[i];
    }
}
