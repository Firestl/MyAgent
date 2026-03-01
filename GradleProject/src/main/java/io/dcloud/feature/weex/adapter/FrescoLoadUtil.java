package io.dcloud.feature.weex.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.image.CloseableBitmap;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.request.Postprocessor;
import io.dcloud.common.util.ThreadPool;
import io.dcloud.feature.uniapp.adapter.UniImageLoadAdapter;
import io.dcloud.feature.uniapp.utils.bitmap.BitmapLoadCallback;
import java.lang.ref.SoftReference;

/* JADX INFO: loaded from: classes3.dex */
public class FrescoLoadUtil implements UniImageLoadAdapter {
    public static FrescoLoadUtil inst;
    public Handler mHandler = new Handler(Looper.getMainLooper());

    private void fetch(final Context context, final Uri uri, final int i, final int i2, final BitmapLoadCallback<Bitmap> bitmapLoadCallback) {
        ThreadPool.self().addThreadTask(new Runnable() { // from class: io.dcloud.feature.weex.adapter.FrescoLoadUtil.1
            @Override // java.lang.Runnable
            public void run() {
                ImageRequestBuilder progressiveRenderingEnabled = ImageRequestBuilder.newBuilderWithSource(uri).setProgressiveRenderingEnabled(false);
                progressiveRenderingEnabled.setPostprocessor(new Postprocessor() { // from class: io.dcloud.feature.weex.adapter.FrescoLoadUtil.1.1
                    @Override // com.facebook.imagepipeline.request.Postprocessor
                    public String getName() {
                        return null;
                    }

                    @Override // com.facebook.imagepipeline.request.Postprocessor
                    public CacheKey getPostprocessorCacheKey() {
                        return null;
                    }

                    @Override // com.facebook.imagepipeline.request.Postprocessor
                    public CloseableReference<Bitmap> process(Bitmap bitmap, PlatformBitmapFactory platformBitmapFactory) {
                        int i3 = i2;
                        if (i3 <= 0 || i3 <= 0) {
                            return platformBitmapFactory.createBitmap(bitmap);
                        }
                        int width = bitmap.getWidth();
                        int height = bitmap.getHeight();
                        Matrix matrix = new Matrix();
                        AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                        matrix.postScale(i / width, i2 / height);
                        return platformBitmapFactory.createBitmap(bitmap, 0, 0, width, height, matrix, true);
                    }
                });
                Fresco.getImagePipeline().fetchDecodedImage(progressiveRenderingEnabled.build(), context).subscribe(new BaseDataSubscriber<CloseableReference<CloseableImage>>() { // from class: io.dcloud.feature.weex.adapter.FrescoLoadUtil.1.2
                    @Override // com.facebook.datasource.BaseDataSubscriber
                    public void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                        AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                        BitmapLoadCallback bitmapLoadCallback2 = bitmapLoadCallback;
                        if (bitmapLoadCallback2 != null) {
                            bitmapLoadCallback2.onSuccess(uri.toString(), null);
                        }
                    }

                    @Override // com.facebook.datasource.BaseDataSubscriber
                    public void onNewResultImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                        CloseableReference<CloseableImage> result;
                        if (dataSource.isFinished() && (result = dataSource.getResult()) != null) {
                            try {
                                Bitmap underlyingBitmap = result.get() instanceof CloseableBitmap ? ((CloseableBitmap) result.get()).getUnderlyingBitmap() : null;
                                if (underlyingBitmap != null && !underlyingBitmap.isRecycled()) {
                                    SoftReference softReference = new SoftReference(Bitmap.createBitmap(underlyingBitmap));
                                    if (bitmapLoadCallback != null) {
                                        bitmapLoadCallback.onSuccess(uri.toString(), softReference.get());
                                    }
                                }
                            } finally {
                                result.close();
                                dataSource.close();
                            }
                        }
                    }
                }, UiThreadImmediateExecutorService.getInstance());
            }
        }, true);
    }

    public static UniImageLoadAdapter getInstance() {
        if (inst == null) {
            inst = new FrescoLoadUtil();
        }
        return inst;
    }

    @Override // io.dcloud.feature.uniapp.adapter.UniImageLoadAdapter
    public final void loadImageBitmap(Context context, String str, BitmapLoadCallback<Bitmap> bitmapLoadCallback) {
        loadImageBitmap(context, str, 0, 0, bitmapLoadCallback);
    }

    @Override // io.dcloud.feature.uniapp.adapter.UniImageLoadAdapter
    public final void loadImageBitmap(Context context, String str, int i, int i2, BitmapLoadCallback<Bitmap> bitmapLoadCallback) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (str.startsWith("//")) {
            str2 = "http:" + str;
        } else {
            str2 = str;
        }
        try {
            fetch(context, Uri.parse(str2), i, i2, bitmapLoadCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            bitmapLoadCallback.onFailure(str, e2);
        }
    }
}
