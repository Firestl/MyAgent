package supwisdom;

import android.text.TextUtils;

/* JADX INFO: compiled from: MediaSessionManagerImplBase.java */
/* JADX INFO: loaded from: classes.dex */
public class ve implements te {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f9505a;
    public int b;
    public int c;

    public ve(String str, int i, int i2) {
        this.f9505a = str;
        this.b = i;
        this.c = i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ve)) {
            return false;
        }
        ve veVar = (ve) obj;
        return TextUtils.equals(this.f9505a, veVar.f9505a) && this.b == veVar.b && this.c == veVar.c;
    }

    public int hashCode() {
        return ia.a(this.f9505a, Integer.valueOf(this.b), Integer.valueOf(this.c));
    }
}
