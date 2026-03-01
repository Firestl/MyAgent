package io.dcloud.feature.weex.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import com.taobao.weex.adapter.DrawableStrategy;
import com.taobao.weex.adapter.IDrawableLoader;
import io.dcloud.feature.uniapp.utils.bitmap.BitmapLoadCallback;

/* JADX INFO: loaded from: classes3.dex */
public class FrescoDrawableLoader implements IDrawableLoader {
    public Context mContext;

    public FrescoDrawableLoader(Context context) {
        this.mContext = context;
    }

    @Override // com.taobao.weex.adapter.IDrawableLoader
    public void setDrawable(String str, final IDrawableLoader.DrawableTarget drawableTarget, DrawableStrategy drawableStrategy) {
        FrescoLoadUtil.getInstance().loadImageBitmap(this.mContext, str, drawableStrategy.width, drawableStrategy.height, new BitmapLoadCallback<Bitmap>() { // from class: io.dcloud.feature.weex.adapter.FrescoDrawableLoader.1
            @Override // io.dcloud.feature.uniapp.utils.bitmap.BitmapLoadCallback
            public void onFailure(String str2, Throwable th) {
            }

            @Override // io.dcloud.feature.uniapp.utils.bitmap.BitmapLoadCallback
            public void onSuccess(String str2, Bitmap bitmap) {
                BitmapDrawable bitmapDrawable = new BitmapDrawable(FrescoDrawableLoader.this.mContext.getResources(), bitmap);
                bitmapDrawable.setGravity(119);
                drawableTarget.setDrawable(bitmapDrawable, true);
            }
        });
    }
}
