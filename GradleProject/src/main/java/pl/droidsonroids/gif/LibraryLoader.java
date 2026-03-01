package pl.droidsonroids.gif;

import android.annotation.SuppressLint;
import android.content.Context;
import pl.droidsonroids.relinker.ReLinker;

/* JADX INFO: loaded from: classes3.dex */
public class LibraryLoader {
    public static final String BASE_LIBRARY_NAME = "pl_droidsonroids_gif";

    @SuppressLint({"StaticFieldLeak"})
    public static Context sAppContext;

    public static Context getContext() {
        if (sAppContext == null) {
            try {
                sAppContext = (Context) Class.forName("android.app.ActivityThread").getDeclaredMethod("currentApplication", new Class[0]).invoke(null, new Object[0]);
            } catch (Exception e2) {
                throw new IllegalStateException("LibraryLoader not initialized. Call LibraryLoader.initialize() before using library classes.", e2);
            }
        }
        return sAppContext;
    }

    public static void initialize(Context context) {
        sAppContext = context.getApplicationContext();
    }

    public static void loadLibrary() {
        try {
            System.loadLibrary(BASE_LIBRARY_NAME);
        } catch (UnsatisfiedLinkError unused) {
            ReLinker.loadLibrary(getContext(), BASE_LIBRARY_NAME);
        }
    }
}
