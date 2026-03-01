package supwisdom;

import com.dcloud.zxing2.NotFoundException;

/* JADX INFO: loaded from: classes.dex */
public abstract class rv {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final uv f9094a;

    public rv(uv uvVar) {
        this.f9094a = uvVar;
    }

    public abstract ew a(int i, ew ewVar) throws NotFoundException;

    public abstract fw a() throws NotFoundException;

    public abstract rv a(uv uvVar);

    public final int b() {
        return this.f9094a.getHeight();
    }

    public final uv c() {
        return this.f9094a;
    }

    public final int d() {
        return this.f9094a.getWidth();
    }
}
