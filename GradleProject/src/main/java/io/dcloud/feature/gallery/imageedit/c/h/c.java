package io.dcloud.feature.gallery.imageedit.c.h;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public class c extends b {
    public c(Uri uri) {
        super(uri);
    }

    @Override // io.dcloud.feature.gallery.imageedit.c.h.b
    public Bitmap a(BitmapFactory.Options options) {
        Uri uriA = a();
        if (uriA == null) {
            return null;
        }
        String path = uriA.getPath();
        if (!TextUtils.isEmpty(path) && new File(path).exists()) {
            return BitmapFactory.decodeFile(path, options);
        }
        return null;
    }
}
