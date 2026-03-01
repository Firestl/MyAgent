package supwisdom;

import com.dcloud.zxing2.NotFoundException;

/* JADX INFO: loaded from: classes.dex */
public final class sv {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final rv f9215a;
    public fw b;

    public sv(rv rvVar) {
        if (rvVar == null) {
            throw new IllegalArgumentException("Binarizer must be non-null.");
        }
        this.f9215a = rvVar;
    }

    public fw a() throws NotFoundException {
        if (this.b == null) {
            this.b = this.f9215a.a();
        }
        return this.b;
    }

    public int b() {
        return this.f9215a.b();
    }

    public int c() {
        return this.f9215a.d();
    }

    public boolean d() {
        return this.f9215a.c().isRotateSupported();
    }

    public sv e() {
        return new sv(this.f9215a.a(this.f9215a.c().rotateCounterClockwise()));
    }

    public String toString() {
        try {
            return a().toString();
        } catch (NotFoundException unused) {
            return "";
        }
    }

    public ew a(int i, ew ewVar) throws NotFoundException {
        return this.f9215a.a(i, ewVar);
    }
}
