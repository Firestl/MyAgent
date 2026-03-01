package androidx.core.app;

import android.app.PendingIntent;
import androidx.core.graphics.drawable.IconCompat;
import androidx.versionedparcelable.VersionedParcel;

/* JADX INFO: loaded from: classes.dex */
public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(VersionedParcel versionedParcel) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        remoteActionCompat.f1271a = (IconCompat) versionedParcel.a(remoteActionCompat.f1271a, 1);
        remoteActionCompat.b = versionedParcel.a(remoteActionCompat.b, 2);
        remoteActionCompat.c = versionedParcel.a(remoteActionCompat.c, 3);
        remoteActionCompat.d = (PendingIntent) versionedParcel.a(remoteActionCompat.d, 4);
        remoteActionCompat.f1272e = versionedParcel.a(remoteActionCompat.f1272e, 5);
        remoteActionCompat.f = versionedParcel.a(remoteActionCompat.f, 6);
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, VersionedParcel versionedParcel) {
        versionedParcel.a(false, false);
        versionedParcel.b(remoteActionCompat.f1271a, 1);
        versionedParcel.b(remoteActionCompat.b, 2);
        versionedParcel.b(remoteActionCompat.c, 3);
        versionedParcel.b(remoteActionCompat.d, 4);
        versionedParcel.b(remoteActionCompat.f1272e, 5);
        versionedParcel.b(remoteActionCompat.f, 6);
    }
}
