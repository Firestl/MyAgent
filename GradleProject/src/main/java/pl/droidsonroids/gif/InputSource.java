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

/* JADX INFO: loaded from: classes3.dex */
public abstract class InputSource {

    public static class AssetFileDescriptorSource extends InputSource {
        public final AssetFileDescriptor mAssetFileDescriptor;

        public AssetFileDescriptorSource(AssetFileDescriptor assetFileDescriptor) {
            super();
            this.mAssetFileDescriptor = assetFileDescriptor;
        }

        @Override // pl.droidsonroids.gif.InputSource
        public GifInfoHandle open() throws IOException {
            return new GifInfoHandle(this.mAssetFileDescriptor);
        }
    }

    public static final class AssetSource extends InputSource {
        public final AssetManager mAssetManager;
        public final String mAssetName;

        public AssetSource(AssetManager assetManager, String str) {
            super();
            this.mAssetManager = assetManager;
            this.mAssetName = str;
        }

        @Override // pl.droidsonroids.gif.InputSource
        public GifInfoHandle open() throws IOException {
            return new GifInfoHandle(this.mAssetManager.openFd(this.mAssetName));
        }
    }

    public static final class ByteArraySource extends InputSource {
        public final byte[] bytes;

        public ByteArraySource(byte[] bArr) {
            super();
            this.bytes = bArr;
        }

        @Override // pl.droidsonroids.gif.InputSource
        public GifInfoHandle open() throws GifIOException {
            return new GifInfoHandle(this.bytes);
        }
    }

    public static final class DirectByteBufferSource extends InputSource {
        public final ByteBuffer byteBuffer;

        public DirectByteBufferSource(ByteBuffer byteBuffer) {
            super();
            this.byteBuffer = byteBuffer;
        }

        @Override // pl.droidsonroids.gif.InputSource
        public GifInfoHandle open() throws GifIOException {
            return new GifInfoHandle(this.byteBuffer);
        }
    }

    public static final class FileDescriptorSource extends InputSource {
        public final FileDescriptor mFd;

        public FileDescriptorSource(FileDescriptor fileDescriptor) {
            super();
            this.mFd = fileDescriptor;
        }

        @Override // pl.droidsonroids.gif.InputSource
        public GifInfoHandle open() throws IOException {
            return new GifInfoHandle(this.mFd);
        }
    }

    public static final class InputStreamSource extends InputSource {
        public final InputStream inputStream;

        public InputStreamSource(InputStream inputStream) {
            super();
            this.inputStream = inputStream;
        }

        @Override // pl.droidsonroids.gif.InputSource
        public GifInfoHandle open() throws IOException {
            return new GifInfoHandle(this.inputStream);
        }
    }

    public static class ResourcesSource extends InputSource {
        public final int mResourceId;
        public final Resources mResources;

        public ResourcesSource(Resources resources, int i) {
            super();
            this.mResources = resources;
            this.mResourceId = i;
        }

        @Override // pl.droidsonroids.gif.InputSource
        public GifInfoHandle open() throws IOException {
            return new GifInfoHandle(this.mResources.openRawResourceFd(this.mResourceId));
        }
    }

    public static final class UriSource extends InputSource {
        public final ContentResolver mContentResolver;
        public final Uri mUri;

        public UriSource(ContentResolver contentResolver, Uri uri) {
            super();
            this.mContentResolver = contentResolver;
            this.mUri = uri;
        }

        @Override // pl.droidsonroids.gif.InputSource
        public GifInfoHandle open() throws IOException {
            return GifInfoHandle.openUri(this.mContentResolver, this.mUri);
        }
    }

    public final GifDrawable createGifDrawable(GifDrawable gifDrawable, ScheduledThreadPoolExecutor scheduledThreadPoolExecutor, boolean z, GifOptions gifOptions) throws IOException {
        return new GifDrawable(createHandleWith(gifOptions), gifDrawable, scheduledThreadPoolExecutor, z);
    }

    public final GifInfoHandle createHandleWith(GifOptions gifOptions) throws IOException {
        GifInfoHandle gifInfoHandleOpen = open();
        gifInfoHandleOpen.setOptions(gifOptions.inSampleSize, gifOptions.inIsOpaque);
        return gifInfoHandleOpen;
    }

    public abstract GifInfoHandle open() throws IOException;

    public static final class FileSource extends InputSource {
        public final String mPath;

        public FileSource(File file) {
            super();
            this.mPath = file.getPath();
        }

        @Override // pl.droidsonroids.gif.InputSource
        public GifInfoHandle open() throws GifIOException {
            return new GifInfoHandle(this.mPath);
        }

        public FileSource(String str) {
            super();
            this.mPath = str;
        }
    }

    public InputSource() {
    }
}
