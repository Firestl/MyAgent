package io.dcloud.feature.weex.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.common.WXImageStrategy;
import com.taobao.weex.dom.WXImageQuality;

/* JADX INFO: loaded from: classes3.dex */
public class GlideImageAdapter {
    public static void onImageFinish(WXImageStrategy wXImageStrategy, String str, ImageView imageView, boolean z) {
        if (wXImageStrategy == null || wXImageStrategy.getImageListener() == null) {
            return;
        }
        wXImageStrategy.getImageListener().onImageFinish(str, imageView, z, null);
    }

    public static void setImage(final String str, final ImageView imageView, WXImageQuality wXImageQuality, final WXImageStrategy wXImageStrategy) {
        Runnable runnable = new Runnable() { // from class: io.dcloud.feature.weex.adapter.GlideImageAdapter.1
            @Override // java.lang.Runnable
            public void run() {
                ImageView imageView2 = imageView;
                if (imageView2 == null || imageView2.getLayoutParams() == null) {
                    return;
                }
                if (TextUtils.isEmpty(str)) {
                    imageView.setImageBitmap(null);
                    return;
                }
                String str2 = str;
                if (str2.startsWith("//")) {
                    str2 = "http:" + str;
                }
                if (imageView.getLayoutParams().width <= 0 || imageView.getLayoutParams().height <= 0) {
                    return;
                }
                RequestOptions requestOptions = new RequestOptions();
                DisplayMetrics displayMetrics = imageView.getResources().getDisplayMetrics();
                requestOptions.override(displayMetrics.widthPixels, displayMetrics.heightPixels);
                Context context = imageView.getContext();
                if (str2.contains(".gif")) {
                    Glide.with(context).asGif().load(str2).apply((BaseRequestOptions<?>) requestOptions).listener(new RequestListener<GifDrawable>() { // from class: io.dcloud.feature.weex.adapter.GlideImageAdapter.1.1
                        @Override // com.bumptech.glide.request.RequestListener
                        public boolean onLoadFailed(GlideException glideException, Object obj, Target<GifDrawable> target, boolean z) {
                            AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                            GlideImageAdapter.onImageFinish(wXImageStrategy, str, imageView, false);
                            return false;
                        }

                        @Override // com.bumptech.glide.request.RequestListener
                        public boolean onResourceReady(GifDrawable gifDrawable, Object obj, Target<GifDrawable> target, DataSource dataSource, boolean z) {
                            AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                            GlideImageAdapter.onImageFinish(wXImageStrategy, str, imageView, true);
                            return false;
                        }
                    }).into(imageView);
                } else {
                    Glide.with(context).load(str2).apply((BaseRequestOptions<?>) requestOptions).listener(new RequestListener<Drawable>() { // from class: io.dcloud.feature.weex.adapter.GlideImageAdapter.1.2
                        @Override // com.bumptech.glide.request.RequestListener
                        public boolean onLoadFailed(GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                            AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                            GlideImageAdapter.onImageFinish(wXImageStrategy, str, imageView, false);
                            return false;
                        }

                        @Override // com.bumptech.glide.request.RequestListener
                        public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                            AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                            GlideImageAdapter.onImageFinish(wXImageStrategy, str, imageView, true);
                            return false;
                        }
                    }).into(imageView);
                }
            }
        };
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            runnable.run();
        } else {
            WXSDKManager.getInstance().postOnUiThread(runnable, 0L);
        }
    }
}
