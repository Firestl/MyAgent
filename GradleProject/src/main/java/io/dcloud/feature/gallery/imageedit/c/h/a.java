package io.dcloud.feature.gallery.imageedit.c.h;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public class a extends b {
    public Context b;

    public a(Context context, Uri uri) {
        super(uri);
        this.b = context;
    }

    @Override // io.dcloud.feature.gallery.imageedit.c.h.b
    public Bitmap a(BitmapFactory.Options options) {
        Uri uriA = a();
        if (uriA == null) {
            return null;
        }
        String path = uriA.getPath();
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        try {
            return BitmapFactory.decodeStream(this.b.getAssets().open(path.substring(1)), null, options);
        } catch (IOException unused) {
            return null;
        }
    }
}
