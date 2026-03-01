package androidx.media;

import androidx.versionedparcelable.VersionedParcel;

/* JADX INFO: loaded from: classes.dex */
public final class AudioAttributesImplBaseParcelizer {
    public static AudioAttributesImplBase read(VersionedParcel versionedParcel) {
        AudioAttributesImplBase audioAttributesImplBase = new AudioAttributesImplBase();
        audioAttributesImplBase.f1320a = versionedParcel.a(audioAttributesImplBase.f1320a, 1);
        audioAttributesImplBase.b = versionedParcel.a(audioAttributesImplBase.b, 2);
        audioAttributesImplBase.c = versionedParcel.a(audioAttributesImplBase.c, 3);
        audioAttributesImplBase.d = versionedParcel.a(audioAttributesImplBase.d, 4);
        return audioAttributesImplBase;
    }

    public static void write(AudioAttributesImplBase audioAttributesImplBase, VersionedParcel versionedParcel) {
        versionedParcel.a(false, false);
        versionedParcel.b(audioAttributesImplBase.f1320a, 1);
        versionedParcel.b(audioAttributesImplBase.b, 2);
        versionedParcel.b(audioAttributesImplBase.c, 3);
        versionedParcel.b(audioAttributesImplBase.d, 4);
    }
}
