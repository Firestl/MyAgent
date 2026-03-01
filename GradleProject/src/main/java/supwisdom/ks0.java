package supwisdom;

import android.os.Parcelable;
import com.google.firebase.appindexing.internal.Thing;
import java.util.Comparator;

/* JADX INFO: compiled from: com.google.firebase:firebase-appindexing@@20.0.0 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ks0 implements Comparator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Comparator f8201a = new ks0();

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        String str = (String) obj;
        String str2 = (String) obj2;
        Parcelable.Creator<Thing> creator = Thing.CREATOR;
        if (str == null) {
            return str2 != null ? -1 : 0;
        }
        if (str2 == null) {
            return 1;
        }
        return str.compareTo(str2);
    }
}
