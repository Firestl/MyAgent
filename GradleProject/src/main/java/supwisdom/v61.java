package supwisdom;

import android.util.Log;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class v61 {
    static {
        Log.i("LoadLibraryHelper", "will loadLibrary sangforsdk");
        if (!yb1.a()) {
            System.loadLibrary("sangforsdk");
        }
        Log.i("LoadLibraryHelper", "did loadLibrary sangforsdk");
    }

    public static void a() {
        Log.i("LoadLibraryHelper", "load called.");
    }
}
