package com.vivo.push.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import com.vivo.push.c.r;
import com.vivo.push.model.InsideNotificationItem;
import java.util.List;

/* JADX INFO: compiled from: ImageDownTask.java */
/* JADX INFO: loaded from: classes2.dex */
public final class k extends AsyncTask<String, Void, List<Bitmap>> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f5637a;
    public InsideNotificationItem b;
    public long c;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f5638e = 0;
    public r.a f;

    public k(Context context, InsideNotificationItem insideNotificationItem, long j, boolean z, r.a aVar) {
        this.f5637a = context;
        this.b = insideNotificationItem;
        this.c = j;
        this.d = z;
        this.f = aVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:51:0x008e A[EXC_TOP_SPLITTER, PHI: r5
  0x008e: PHI (r5v5 java.io.InputStream) = (r5v4 java.io.InputStream), (r5v6 java.io.InputStream) binds: [B:24:0x008c, B:29:0x0098] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.List<android.graphics.Bitmap> doInBackground(java.lang.String... r10) throws java.lang.Throwable {
        /*
            r9 = this;
            android.content.Context r0 = r9.f5637a
            com.vivo.push.cache.ClientConfigManagerImpl r0 = com.vivo.push.cache.ClientConfigManagerImpl.getInstance(r0)
            int r0 = r0.getNotifyStyle()
            r9.f5638e = r0
            boolean r0 = r9.d
            r1 = 0
            java.lang.String r2 = "ImageDownTask"
            if (r0 != 0) goto L19
            java.lang.String r10 = "bitmap is not display by forbid net"
            com.vivo.push.util.o.d(r2, r10)
            return r1
        L19:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r3 = 0
            r4 = 0
        L20:
            r5 = 2
            if (r4 >= r5) goto Lb1
            r5 = r10[r4]
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "imgUrl="
            r6.<init>(r7)
            r6.append(r5)
            java.lang.String r7 = " i="
            r6.append(r7)
            r6.append(r4)
            java.lang.String r6 = r6.toString()
            com.vivo.push.util.o.d(r2, r6)
            boolean r6 = android.text.TextUtils.isEmpty(r5)
            if (r6 != 0) goto La8
            java.net.URL r6 = new java.net.URL     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86 java.net.MalformedURLException -> L92
            r6.<init>(r5)     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86 java.net.MalformedURLException -> L92
            java.net.URLConnection r5 = r6.openConnection()     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86 java.net.MalformedURLException -> L92
            java.net.HttpURLConnection r5 = (java.net.HttpURLConnection) r5     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86 java.net.MalformedURLException -> L92
            r6 = 30000(0x7530, float:4.2039E-41)
            r5.setConnectTimeout(r6)     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86 java.net.MalformedURLException -> L92
            r6 = 1
            r5.setDoInput(r6)     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86 java.net.MalformedURLException -> L92
            r5.setUseCaches(r3)     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86 java.net.MalformedURLException -> L92
            r5.connect()     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86 java.net.MalformedURLException -> L92
            int r6 = r5.getResponseCode()     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86 java.net.MalformedURLException -> L92
            java.lang.String r7 = "code="
            java.lang.String r8 = java.lang.String.valueOf(r6)     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86 java.net.MalformedURLException -> L92
            java.lang.String r7 = r7.concat(r8)     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86 java.net.MalformedURLException -> L92
            com.vivo.push.util.o.c(r2, r7)     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86 java.net.MalformedURLException -> L92
            r7 = 200(0xc8, float:2.8E-43)
            if (r6 != r7) goto L7c
            java.io.InputStream r5 = r5.getInputStream()     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86 java.net.MalformedURLException -> L92
            android.graphics.Bitmap r6 = android.graphics.BitmapFactory.decodeStream(r5)     // Catch: java.io.IOException -> L87 java.net.MalformedURLException -> L93 java.lang.Throwable -> La0
            goto L7e
        L7c:
            r5 = r1
            r6 = r5
        L7e:
            if (r5 == 0) goto L9c
            r5.close()     // Catch: java.lang.Exception -> L9c
            goto L9c
        L84:
            r10 = move-exception
            goto La2
        L86:
            r5 = r1
        L87:
            java.lang.String r6 = "IOException"
            com.vivo.push.util.o.a(r2, r6)     // Catch: java.lang.Throwable -> La0
            if (r5 == 0) goto L9b
        L8e:
            r5.close()     // Catch: java.lang.Exception -> L9b
            goto L9b
        L92:
            r5 = r1
        L93:
            java.lang.String r6 = "MalformedURLException"
            com.vivo.push.util.o.a(r2, r6)     // Catch: java.lang.Throwable -> La0
            if (r5 == 0) goto L9b
            goto L8e
        L9b:
            r6 = r1
        L9c:
            r0.add(r6)
            goto Lad
        La0:
            r10 = move-exception
            r1 = r5
        La2:
            if (r1 == 0) goto La7
            r1.close()     // Catch: java.lang.Exception -> La7
        La7:
            throw r10
        La8:
            if (r4 != 0) goto Lad
            r0.add(r1)
        Lad:
            int r4 = r4 + 1
            goto L20
        Lb1:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vivo.push.util.k.doInBackground(java.lang.String[]):java.util.List");
    }

    @Override // android.os.AsyncTask
    public final /* synthetic */ void onPostExecute(List<Bitmap> list) {
        List<Bitmap> list2 = list;
        super.onPostExecute(list2);
        o.c("ImageDownTask", "onPostExecute");
        if (this.b != null) {
            w.b().a("com.vivo.push.notify_key", this.c);
            NotifyAdapterUtil.pushNotification(this.f5637a, list2, this.b, this.c, this.f5638e, this.f);
        }
    }
}
