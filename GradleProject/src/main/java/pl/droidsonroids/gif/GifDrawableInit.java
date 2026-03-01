package pl.droidsonroids.gif;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.net.Uri;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import pl.droidsonroids.gif.GifDrawableInit;
import pl.droidsonroids.gif.InputSource;
import pl.droidsonroids.gif.annotations.Beta;

/* JADX INFO: loaded from: classes3.dex */
public abstract class GifDrawableInit<T extends GifDrawableInit<T>> {
    public ScheduledThreadPoolExecutor mExecutor;
    public InputSource mInputSource;
    public GifDrawable mOldDrawable;
    public boolean mIsRenderingTriggeredOnDraw = true;
    public final GifOptions mOptions = new GifOptions();

    public GifDrawable build() throws IOException {
        InputSource inputSource = this.mInputSource;
        if (inputSource != null) {
            return inputSource.createGifDrawable(this.mOldDrawable, this.mExecutor, this.mIsRenderingTriggeredOnDraw, this.mOptions);
        }
        throw new NullPointerException("Source is not set");
    }

    public T from(InputStream inputStream) {
        this.mInputSource = new InputSource.InputStreamSource(inputStream);
        return (T) self();
    }

    public ScheduledThreadPoolExecutor getExecutor() {
        return this.mExecutor;
    }

    public InputSource getInputSource() {
        return this.mInputSource;
    }

    public GifDrawable getOldDrawable() {
        return this.mOldDrawable;
    }

    public GifOptions getOptions() {
        return this.mOptions;
    }

    public boolean isRenderingTriggeredOnDraw() {
        return this.mIsRenderingTriggeredOnDraw;
    }

    @Beta
    public T options(GifOptions gifOptions) {
        this.mOptions.setFrom(gifOptions);
        return (T) self();
    }

    public T renderingTriggeredOnDraw(boolean z) {
        this.mIsRenderingTriggeredOnDraw = z;
        return (T) self();
    }

    public T sampleSize(int i) {
        this.mOptions.setInSampleSize(i);
        return (T) self();
    }

    public abstract T self();

    public T setRenderingTriggeredOnDraw(boolean z) {
        return (T) renderingTriggeredOnDraw(z);
    }

    public T taskExecutor(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) {
        this.mExecutor = scheduledThreadPoolExecutor;
        return (T) self();
    }

    public T threadPoolSize(int i) {
        this.mExecutor = new ScheduledThreadPoolExecutor(i);
        return (T) self();
    }

    public T with(GifDrawable gifDrawable) {
        this.mOldDrawable = gifDrawable;
        return (T) self();
    }

    public T from(AssetFileDescriptor assetFileDescriptor) {
        this.mInputSource = new InputSource.AssetFileDescriptorSource(assetFileDescriptor);
        return (T) self();
    }

    public T from(FileDescriptor fileDescriptor) {
        this.mInputSource = new InputSource.FileDescriptorSource(fileDescriptor);
        return (T) self();
    }

    public T from(AssetManager assetManager, String str) {
        this.mInputSource = new InputSource.AssetSource(assetManager, str);
        return (T) self();
    }

    public T from(ContentResolver contentResolver, Uri uri) {
        this.mInputSource = new InputSource.UriSource(contentResolver, uri);
        return (T) self();
    }

    public T from(File file) {
        this.mInputSource = new InputSource.FileSource(file);
        return (T) self();
    }

    public T from(String str) {
        this.mInputSource = new InputSource.FileSource(str);
        return (T) self();
    }

    public T from(byte[] bArr) {
        this.mInputSource = new InputSource.ByteArraySource(bArr);
        return (T) self();
    }

    public T from(ByteBuffer byteBuffer) {
        this.mInputSource = new InputSource.DirectByteBufferSource(byteBuffer);
        return (T) self();
    }

    public T from(Resources resources, int i) {
        this.mInputSource = new InputSource.ResourcesSource(resources, i);
        return (T) self();
    }
}
