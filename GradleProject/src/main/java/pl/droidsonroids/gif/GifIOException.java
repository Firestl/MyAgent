package pl.droidsonroids.gif;

import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public class GifIOException extends IOException {
    public static final long serialVersionUID = 13038402904505L;
    public final String mErrnoMessage;
    public final GifError reason;

    public GifIOException(int i, String str) {
        this.reason = GifError.fromCode(i);
        this.mErrnoMessage = str;
    }

    public static GifIOException fromCode(int i) {
        if (i == GifError.NO_ERROR.errorCode) {
            return null;
        }
        return new GifIOException(i, null);
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        if (this.mErrnoMessage == null) {
            return this.reason.getFormattedDescription();
        }
        return this.reason.getFormattedDescription() + ": " + this.mErrnoMessage;
    }
}
