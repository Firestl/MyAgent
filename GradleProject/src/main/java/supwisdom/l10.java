package supwisdom;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.taobao.weex.el.parse.Operators;
import java.util.Arrays;

/* JADX INFO: compiled from: AudioCapabilities.java */
/* JADX INFO: loaded from: classes.dex */
@TargetApi(21)
public final class l10 {
    public static final l10 c = new l10(new int[]{2}, 2);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int[] f8229a;
    public final int b;

    public l10(int[] iArr, int i) {
        if (iArr != null) {
            int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length);
            this.f8229a = iArrCopyOf;
            Arrays.sort(iArrCopyOf);
        } else {
            this.f8229a = new int[0];
        }
        this.b = i;
    }

    public static l10 a(Context context) {
        return a(context.registerReceiver(null, new IntentFilter("android.media.action.HDMI_AUDIO_PLUG")));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof l10)) {
            return false;
        }
        l10 l10Var = (l10) obj;
        return Arrays.equals(this.f8229a, l10Var.f8229a) && this.b == l10Var.b;
    }

    public int hashCode() {
        return this.b + (Arrays.hashCode(this.f8229a) * 31);
    }

    public String toString() {
        return "AudioCapabilities[maxChannelCount=" + this.b + ", supportedEncodings=" + Arrays.toString(this.f8229a) + Operators.ARRAY_END_STR;
    }

    @SuppressLint({"InlinedApi"})
    public static l10 a(Intent intent) {
        if (intent != null && intent.getIntExtra("android.media.extra.AUDIO_PLUG_STATE", 0) != 0) {
            return new l10(intent.getIntArrayExtra("android.media.extra.ENCODINGS"), intent.getIntExtra("android.media.extra.MAX_CHANNEL_COUNT", 0));
        }
        return c;
    }

    public boolean a(int i) {
        return Arrays.binarySearch(this.f8229a, i) >= 0;
    }
}
