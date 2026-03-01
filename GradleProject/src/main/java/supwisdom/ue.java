package supwisdom;

import android.media.session.MediaSessionManager;

/* JADX INFO: compiled from: MediaSessionManagerImplApi28.java */
/* JADX INFO: loaded from: classes.dex */
public final class ue implements te {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final MediaSessionManager.RemoteUserInfo f9402a;

    public ue(String str, int i, int i2) {
        this.f9402a = new MediaSessionManager.RemoteUserInfo(str, i, i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ue) {
            return this.f9402a.equals(((ue) obj).f9402a);
        }
        return false;
    }

    public int hashCode() {
        return ia.a(this.f9402a);
    }

    public ue(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
        this.f9402a = remoteUserInfo;
    }
}
