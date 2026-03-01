package io.dcloud.feature.uniapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import io.dcloud.feature.uniapp.utils.bitmap.BitmapLoadCallback;

/* JADX INFO: loaded from: classes3.dex */
public interface UniImageLoadAdapter {
    void loadImageBitmap(Context context, String str, int i, int i2, BitmapLoadCallback<Bitmap> bitmapLoadCallback);

    void loadImageBitmap(Context context, String str, BitmapLoadCallback<Bitmap> bitmapLoadCallback);
}
