package androidx.media;

import android.media.AudioAttributes;
import androidx.versionedparcelable.VersionedParcel;

/* JADX INFO: loaded from: classes.dex */
public final class AudioAttributesImplApi21Parcelizer {
    public static AudioAttributesImplApi21 read(VersionedParcel versionedParcel) {
        AudioAttributesImplApi21 audioAttributesImplApi21 = new AudioAttributesImplApi21();
        audioAttributesImplApi21.f1319a = (AudioAttributes) versionedParcel.a(audioAttributesImplApi21.f1319a, 1);
        audioAttributesImplApi21.b = versionedParcel.a(audioAttributesImplApi21.b, 2);
        return audioAttributesImplApi21;
    }

    public static void write(AudioAttributesImplApi21 audioAttributesImplApi21, VersionedParcel versionedParcel) {
        versionedParcel.a(false, false);
        versionedParcel.b(audioAttributesImplApi21.f1319a, 1);
        versionedParcel.b(audioAttributesImplApi21.b, 2);
    }
}
