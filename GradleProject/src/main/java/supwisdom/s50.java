package supwisdom;

import android.text.Layout;

/* JADX INFO: compiled from: Cea708Cue.java */
/* JADX INFO: loaded from: classes.dex */
public final class s50 extends y50 implements Comparable<s50> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f9124e;

    public s50(CharSequence charSequence, Layout.Alignment alignment, float f, int i, int i2, float f2, int i3, float f3, boolean z, int i4, int i5) {
        super(charSequence, alignment, f, i, i2, f2, i3, f3, z, i4);
        this.f9124e = i5;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(s50 s50Var) {
        int i = s50Var.f9124e;
        int i2 = this.f9124e;
        if (i < i2) {
            return -1;
        }
        return i > i2 ? 1 : 0;
    }
}
