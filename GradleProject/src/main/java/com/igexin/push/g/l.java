package com.igexin.push.g;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/* JADX INFO: loaded from: classes2.dex */
public final class l {
    public static Bitmap a(String str) {
        if (str != null) {
            try {
                Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(str);
                if (bitmapDecodeFile != null) {
                    return bitmapDecodeFile;
                }
            } catch (Exception e2) {
                com.igexin.c.a.c.a.a(e2);
            }
        }
        return null;
    }
}
