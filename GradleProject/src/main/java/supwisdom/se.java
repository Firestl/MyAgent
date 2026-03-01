package supwisdom;

import android.media.session.MediaSessionManager;
import android.os.Build;

/* JADX INFO: compiled from: MediaSessionManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class se {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public te f9155a;

    public se(String str, int i, int i2) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.f9155a = new ue(str, i, i2);
        } else {
            this.f9155a = new ve(str, i, i2);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof se) {
            return this.f9155a.equals(((se) obj).f9155a);
        }
        return false;
    }

    public int hashCode() {
        return this.f9155a.hashCode();
    }

    public se(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
        this.f9155a = new ue(remoteUserInfo);
    }
}
