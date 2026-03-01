package supwisdom;

import com.google.android.gms.internal.icing.zzbq;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: compiled from: com.google.firebase:firebase-appindexing@@20.0.0 */
/* JADX INFO: loaded from: classes.dex */
public final class pi0 {
    public static <T> T a(@NullableDecl T t) {
        if (t != null) {
            return t;
        }
        StringBuilder sb = new StringBuilder(29);
        sb.append((CharSequence) "expected a non-null reference", 0, 29);
        throw new zzbq(sb.toString());
    }
}
