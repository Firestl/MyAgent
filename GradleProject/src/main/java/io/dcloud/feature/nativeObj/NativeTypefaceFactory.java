package io.dcloud.feature.nativeObj;

import android.graphics.Typeface;
import io.dcloud.common.DHInterface.IApp;
import java.io.File;
import java.lang.ref.SoftReference;
import java.util.HashMap;

/* JADX INFO: loaded from: classes3.dex */
public class NativeTypefaceFactory {
    public static HashMap<String, SoftReference<Typeface>> mCache = new HashMap<>();

    public static void clearCache() {
        HashMap<String, SoftReference<Typeface>> map = mCache;
        if (map != null) {
            map.clear();
        }
    }

    public static Typeface getTypeface(IApp iApp, String str) {
        Typeface typefaceCreateFromFile;
        try {
            if (mCache.containsKey(str)) {
                SoftReference<Typeface> softReference = mCache.get(str);
                if (softReference != null && softReference.get() != null) {
                    return softReference.get();
                }
                mCache.remove(str);
            }
            File file = new File(str);
            if (iApp.obtainRunningAppMode() != 1 || file.exists()) {
                typefaceCreateFromFile = Typeface.createFromFile(str);
            } else {
                if (str.startsWith("/")) {
                    str = str.substring(1, str.length());
                }
                typefaceCreateFromFile = Typeface.createFromAsset(iApp.getActivity().getAssets(), str);
            }
            try {
                mCache.put(str, new SoftReference<>(typefaceCreateFromFile));
                return typefaceCreateFromFile;
            } catch (Exception unused) {
                return typefaceCreateFromFile;
            }
        } catch (Exception unused2) {
            return null;
        }
    }
}
