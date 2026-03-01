package org.mozilla.universalchardet.prober;

/* JADX INFO: loaded from: classes3.dex */
public abstract class CharsetProber {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f6812a = true;

    public enum ProbingState {
        DETECTING,
        FOUND_IT,
        NOT_ME
    }

    public abstract String a();

    public abstract ProbingState a(byte[] bArr, int i, int i2);

    public void a(boolean z) {
        this.f6812a = z;
    }

    public abstract float b();

    public boolean c() {
        return this.f6812a;
    }

    public abstract void d();
}
