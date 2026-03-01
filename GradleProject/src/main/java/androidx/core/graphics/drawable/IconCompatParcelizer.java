package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.os.Parcelable;
import androidx.versionedparcelable.VersionedParcel;

/* JADX INFO: loaded from: classes.dex */
public class IconCompatParcelizer {
    public static IconCompat read(VersionedParcel versionedParcel) {
        IconCompat iconCompat = new IconCompat();
        iconCompat.f1275a = versionedParcel.a(iconCompat.f1275a, 1);
        iconCompat.c = versionedParcel.a(iconCompat.c, 2);
        iconCompat.d = versionedParcel.a(iconCompat.d, 3);
        iconCompat.f1276e = versionedParcel.a(iconCompat.f1276e, 4);
        iconCompat.f = versionedParcel.a(iconCompat.f, 5);
        iconCompat.g = (ColorStateList) versionedParcel.a(iconCompat.g, 6);
        iconCompat.i = versionedParcel.a(iconCompat.i, 7);
        iconCompat.j = versionedParcel.a(iconCompat.j, 8);
        iconCompat.e();
        return iconCompat;
    }

    public static void write(IconCompat iconCompat, VersionedParcel versionedParcel) {
        versionedParcel.a(true, true);
        iconCompat.a(versionedParcel.c());
        int i = iconCompat.f1275a;
        if (-1 != i) {
            versionedParcel.b(i, 1);
        }
        byte[] bArr = iconCompat.c;
        if (bArr != null) {
            versionedParcel.b(bArr, 2);
        }
        Parcelable parcelable = iconCompat.d;
        if (parcelable != null) {
            versionedParcel.b(parcelable, 3);
        }
        int i2 = iconCompat.f1276e;
        if (i2 != 0) {
            versionedParcel.b(i2, 4);
        }
        int i3 = iconCompat.f;
        if (i3 != 0) {
            versionedParcel.b(i3, 5);
        }
        ColorStateList colorStateList = iconCompat.g;
        if (colorStateList != null) {
            versionedParcel.b(colorStateList, 6);
        }
        String str = iconCompat.i;
        if (str != null) {
            versionedParcel.b(str, 7);
        }
        String str2 = iconCompat.j;
        if (str2 != null) {
            versionedParcel.b(str2, 8);
        }
    }
}
