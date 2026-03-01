package pl.droidsonroids.gif;

import android.graphics.Bitmap;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public class GifDecoder {
    public final GifInfoHandle mGifInfoHandle;

    public GifDecoder(InputSource inputSource) throws IOException {
        this(inputSource, null);
    }

    private void checkBuffer(Bitmap bitmap) {
        if (bitmap.isRecycled()) {
            throw new IllegalArgumentException("Bitmap is recycled");
        }
        if (bitmap.getWidth() < this.mGifInfoHandle.getWidth() || bitmap.getHeight() < this.mGifInfoHandle.getHeight()) {
            throw new IllegalArgumentException("Bitmap ia too small, size must be greater than or equal to GIF size");
        }
        if (bitmap.getConfig() == Bitmap.Config.ARGB_8888) {
            return;
        }
        throw new IllegalArgumentException("Only Config.ARGB_8888 is supported. Current bitmap config: " + bitmap.getConfig());
    }

    public long getAllocationByteCount() {
        return this.mGifInfoHandle.getAllocationByteCount();
    }

    public String getComment() {
        return this.mGifInfoHandle.getComment();
    }

    public int getDuration() {
        return this.mGifInfoHandle.getDuration();
    }

    public int getFrameDuration(int i) {
        return this.mGifInfoHandle.getFrameDuration(i);
    }

    public int getHeight() {
        return this.mGifInfoHandle.getHeight();
    }

    public int getLoopCount() {
        return this.mGifInfoHandle.getLoopCount();
    }

    public int getNumberOfFrames() {
        return this.mGifInfoHandle.getNumberOfFrames();
    }

    public long getSourceLength() {
        return this.mGifInfoHandle.getSourceLength();
    }

    public int getWidth() {
        return this.mGifInfoHandle.getWidth();
    }

    public boolean isAnimated() {
        return this.mGifInfoHandle.getNumberOfFrames() > 1 && getDuration() > 0;
    }

    public void recycle() {
        this.mGifInfoHandle.recycle();
    }

    public void seekToFrame(int i, Bitmap bitmap) {
        checkBuffer(bitmap);
        this.mGifInfoHandle.seekToFrame(i, bitmap);
    }

    public void seekToTime(int i, Bitmap bitmap) {
        checkBuffer(bitmap);
        this.mGifInfoHandle.seekToTime(i, bitmap);
    }

    public GifDecoder(InputSource inputSource, GifOptions gifOptions) throws IOException {
        GifInfoHandle gifInfoHandleOpen = inputSource.open();
        this.mGifInfoHandle = gifInfoHandleOpen;
        if (gifOptions != null) {
            gifInfoHandleOpen.setOptions(gifOptions.inSampleSize, gifOptions.inIsOpaque);
        }
    }
}
