package io.dcloud.feature.weex.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.view.DraweeView;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.adapter.IWXImgLoaderAdapter;
import com.taobao.weex.common.WXImageStrategy;
import com.taobao.weex.dom.WXImageQuality;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.ThreadPool;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/* JADX INFO: loaded from: classes3.dex */
public class FrescoImageAdapter implements IWXImgLoaderAdapter {
    @Override // com.taobao.weex.adapter.IWXImgLoaderAdapter
    public void setImage(final String str, final ImageView imageView, final WXImageQuality wXImageQuality, final WXImageStrategy wXImageStrategy) {
        if (PdrUtil.isEmpty(str)) {
            if (imageView != null) {
                imageView.setImageBitmap(null);
            }
        } else if (wXImageStrategy == null || wXImageStrategy.placeHolder == null) {
            WXSDKManager.getInstance().postOnUiThread(new Runnable() { // from class: io.dcloud.feature.weex.adapter.FrescoImageAdapter.2
                @Override // java.lang.Runnable
                public void run() {
                    FrescoImageAdapter.setImage(str, imageView, wXImageQuality, wXImageStrategy, null);
                }
            }, 0L);
        } else {
            ThreadPool.self().addThreadTask(new Runnable() { // from class: io.dcloud.feature.weex.adapter.FrescoImageAdapter.1
                @Override // java.lang.Runnable
                public void run() {
                    Bitmap bitmapDecodeStream;
                    Logger.d("FrescoImage", "Thread_setImage--" + str);
                    try {
                        if (wXImageStrategy.placeHolder.startsWith("file")) {
                            bitmapDecodeStream = BitmapFactory.decodeFile(wXImageStrategy.placeHolder.replaceFirst("file://", ""));
                        } else {
                            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(wXImageStrategy.placeHolder).openConnection();
                            httpURLConnection.connect();
                            bitmapDecodeStream = BitmapFactory.decodeStream(httpURLConnection.getInputStream());
                        }
                    } catch (IOException e2) {
                        e2.printStackTrace();
                        bitmapDecodeStream = null;
                    }
                    final BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmapDecodeStream);
                    WXSDKManager.getInstance().postOnUiThread(new Runnable() { // from class: io.dcloud.feature.weex.adapter.FrescoImageAdapter.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                            FrescoImageAdapter.setImage(str, imageView, wXImageQuality, wXImageStrategy, bitmapDrawable);
                        }
                    }, 0L);
                }
            }, true);
        }
    }

    public static void setImage(final String str, final ImageView imageView, WXImageQuality wXImageQuality, final WXImageStrategy wXImageStrategy, Drawable drawable) {
        String str2;
        if (imageView == null || imageView.getLayoutParams() == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            imageView.setImageBitmap(null);
            return;
        }
        if (str.startsWith("//")) {
            str2 = "http:" + str;
        } else {
            str2 = str;
        }
        ImageRequest imageRequestBuild = ImageRequestBuilder.newBuilderWithSource(Uri.parse(str2)).setImageDecodeOptions(ImageDecodeOptions.newBuilder().build()).setAutoRotateEnabled(true).setLocalThumbnailPreviewsEnabled(true).setLowestPermittedRequestLevel(ImageRequest.RequestLevel.FULL_FETCH).setProgressiveRenderingEnabled(false).build();
        if (imageView instanceof DraweeView) {
            AbstractDraweeController abstractDraweeControllerBuild = Fresco.newDraweeControllerBuilder().setAutoPlayAnimations(true).setControllerListener(new BaseControllerListener<ImageInfo>() { // from class: io.dcloud.feature.weex.adapter.FrescoImageAdapter.3
                @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
                public void onFailure(String str3, Throwable th) {
                    FLog.e((Class<?>) AnonymousClass3.class, th, "Error loading %s", str3);
                    WXImageStrategy wXImageStrategy2 = wXImageStrategy;
                    if (wXImageStrategy2 == null || wXImageStrategy2.getImageListener() == null) {
                        return;
                    }
                    wXImageStrategy.getImageListener().onImageFinish(str, imageView, false, null);
                }

                @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
                public void onFinalImageSet(String str3, ImageInfo imageInfo, Animatable animatable) {
                    WXImageStrategy wXImageStrategy2;
                    if (imageInfo == null || (wXImageStrategy2 = wXImageStrategy) == null || wXImageStrategy2.getImageListener() == null) {
                        return;
                    }
                    HashMap map = new HashMap();
                    map.put("width", Integer.valueOf(imageInfo.getWidth()));
                    map.put("height", Integer.valueOf(imageInfo.getHeight()));
                    if (imageInfo.getWidth() > 0) {
                        wXImageStrategy.getImageListener().onImageFinish(str, imageView, true, map);
                    } else {
                        wXImageStrategy.getImageListener().onImageFinish(str, imageView, false, map);
                    }
                }

                @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
                public void onIntermediateImageSet(String str3, ImageInfo imageInfo) {
                    FLog.d("", "Intermediate image received");
                }
            }).setImageRequest(imageRequestBuild).build();
            if (drawable != null) {
                ((FrescoImageView) imageView).getHierarchy().setPlaceholderImage(drawable);
            }
            ((DraweeView) imageView).setController(abstractDraweeControllerBuild);
            return;
        }
        Fresco.getImagePipeline().fetchDecodedImage(imageRequestBuild, new Object()).subscribe(new BaseDataSubscriber<CloseableReference<CloseableImage>>() { // from class: io.dcloud.feature.weex.adapter.FrescoImageAdapter.4
            @Override // com.facebook.datasource.BaseDataSubscriber
            public void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                WXImageStrategy wXImageStrategy2 = wXImageStrategy;
                if (wXImageStrategy2 == null || wXImageStrategy2.getImageListener() == null) {
                    return;
                }
                wXImageStrategy.getImageListener().onImageFinish(str, imageView, false, null);
            }

            @Override // com.facebook.datasource.BaseDataSubscriber
            public void onNewResultImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                CloseableReference<CloseableImage> result = dataSource.getResult();
                if (result != null) {
                    try {
                        Preconditions.checkState(CloseableReference.isValid(result));
                        CloseableImage closeableImage = result.get();
                        if (closeableImage instanceof CloseableStaticBitmap) {
                            imageView.setImageBitmap(((CloseableStaticBitmap) closeableImage).getUnderlyingBitmap());
                        }
                        if (wXImageStrategy != null && wXImageStrategy.getImageListener() != null) {
                            wXImageStrategy.getImageListener().onImageFinish(str, imageView, true, null);
                        }
                    } finally {
                        result.close();
                    }
                }
            }
        }, UiThreadImmediateExecutorService.getInstance());
    }
}
