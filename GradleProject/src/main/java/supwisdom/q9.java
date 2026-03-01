package supwisdom;

import android.os.LocaleList;
import java.util.Locale;

/* JADX INFO: compiled from: LocaleListPlatformWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public final class q9 implements p9 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LocaleList f8896a;

    public q9(LocaleList localeList) {
        this.f8896a = localeList;
    }

    @Override // supwisdom.p9
    public Object a() {
        return this.f8896a;
    }

    public boolean equals(Object obj) {
        return this.f8896a.equals(((p9) obj).a());
    }

    @Override // supwisdom.p9
    public Locale get(int i) {
        return this.f8896a.get(i);
    }

    public int hashCode() {
        return this.f8896a.hashCode();
    }

    public String toString() {
        return this.f8896a.toString();
    }
}
