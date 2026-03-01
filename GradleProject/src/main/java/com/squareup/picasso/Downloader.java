package com.squareup.picasso;

import android.graphics.Bitmap;
import android.net.Uri;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes2.dex */
public interface Downloader {

    public static class ResponseException extends IOException {
        public final boolean localCacheOnly;
        public final int responseCode;

        public ResponseException(String str, int i, int i2) {
            super(str);
            this.localCacheOnly = NetworkPolicy.isOfflineOnly(i);
            this.responseCode = i2;
        }
    }

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final InputStream f3961a;
        public final Bitmap b;
        public final boolean c;
        public final long d;

        public a(InputStream inputStream, boolean z, long j) {
            if (inputStream == null) {
                throw new IllegalArgumentException("Stream may not be null.");
            }
            this.f3961a = inputStream;
            this.b = null;
            this.c = z;
            this.d = j;
        }

        @Deprecated
        public Bitmap a() {
            return this.b;
        }

        public long b() {
            return this.d;
        }

        public InputStream c() {
            return this.f3961a;
        }
    }

    a a(Uri uri, int i) throws IOException;
}
