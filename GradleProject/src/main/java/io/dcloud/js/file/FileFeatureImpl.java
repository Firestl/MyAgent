package io.dcloud.js.file;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import com.bumptech.glide.load.model.AssetUriLoader;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.taobao.weex.common.Constants;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.base.R;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IFeature;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.io.DHFile;
import io.dcloud.common.adapter.util.AsyncTaskHandler;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.util.Base64;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.FileUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.Md5Utils;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class FileFeatureImpl implements IFeature {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f6734a;
    public static String b;
    public static String c;
    public static String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static String f6735e;

    public class a implements AsyncTaskHandler.IAsyncTaskListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f6736a;
        public final /* synthetic */ int b;
        public final /* synthetic */ int c;
        public final /* synthetic */ String d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ IWebview f6737e;
        public final /* synthetic */ String f;

        public a(String str, int i, int i2, String str2, IWebview iWebview, String str3) {
            this.f6736a = str;
            this.b = i;
            this.c = i2;
            this.d = str2;
            this.f6737e = iWebview;
            this.f = str3;
        }

        @Override // io.dcloud.common.adapter.util.AsyncTaskHandler.IAsyncTaskListener
        public void onCancel() {
        }

        @Override // io.dcloud.common.adapter.util.AsyncTaskHandler.IAsyncTaskListener
        public void onExecuteBegin() {
        }

        @Override // io.dcloud.common.adapter.util.AsyncTaskHandler.IAsyncTaskListener
        public void onExecuteEnd(Object obj) {
            if (obj != null) {
                Deprecated_JSUtil.execCallback(this.f6737e, this.f, String.valueOf(obj), JSUtil.OK, true, false);
            } else {
                FileFeatureImpl.this.a(10, this.f6737e, this.f);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:49:0x009a A[Catch: IOException -> 0x0096, TRY_LEAVE, TryCatch #4 {IOException -> 0x0096, blocks: (B:45:0x0092, B:49:0x009a), top: B:72:0x0092 }] */
        /* JADX WARN: Removed duplicated region for block: B:62:0x00b3 A[Catch: IOException -> 0x00af, TRY_LEAVE, TryCatch #8 {IOException -> 0x00af, blocks: (B:58:0x00ab, B:62:0x00b3), top: B:74:0x00ab }] */
        /* JADX WARN: Removed duplicated region for block: B:72:0x0092 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:74:0x00ab A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // io.dcloud.common.adapter.util.AsyncTaskHandler.IAsyncTaskListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Object onExecuting() throws java.lang.Throwable {
            /*
                r10 = this;
                r0 = 0
                java.lang.String r1 = r10.f6736a     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
                java.lang.Object r1 = io.dcloud.common.adapter.io.DHFile.createFileHandler(r1)     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
                java.io.InputStream r1 = io.dcloud.common.adapter.io.DHFile.getInputStream(r1)     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
                int r2 = r1.available()     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
                io.dcloud.common.adapter.io.UnicodeInputStream r3 = new io.dcloud.common.adapter.io.UnicodeInputStream     // Catch: java.lang.Exception -> L1e java.lang.Throwable -> L80
                java.nio.charset.Charset r4 = java.nio.charset.Charset.defaultCharset()     // Catch: java.lang.Exception -> L1e java.lang.Throwable -> L80
                java.lang.String r4 = r4.name()     // Catch: java.lang.Exception -> L1e java.lang.Throwable -> L80
                r3.<init>(r1, r4)     // Catch: java.lang.Exception -> L1e java.lang.Throwable -> L80
                r1 = r3
                goto L22
            L1e:
                r3 = move-exception
                r3.printStackTrace()     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
            L22:
                java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
                r3.<init>()     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
                int r4 = r10.b     // Catch: java.lang.Throwable -> L77 java.lang.Exception -> L79
                r5 = 0
                if (r4 <= 0) goto L42
                if (r4 < r2) goto L30
                int r4 = r2 + (-1)
            L30:
                int r2 = r10.c     // Catch: java.lang.Throwable -> L77 java.lang.Exception -> L79
                int r4 = r4 - r2
                int r4 = r4 + 1
                byte[] r6 = new byte[r4]     // Catch: java.lang.Throwable -> L77 java.lang.Exception -> L79
                long r7 = (long) r2     // Catch: java.lang.Throwable -> L77 java.lang.Exception -> L79
                r1.skip(r7)     // Catch: java.lang.Throwable -> L77 java.lang.Exception -> L79
                r1.read(r6, r5, r4)     // Catch: java.lang.Throwable -> L77 java.lang.Exception -> L79
                r3.write(r6, r5, r4)     // Catch: java.lang.Throwable -> L77 java.lang.Exception -> L79
                goto L4e
            L42:
                r2 = 204800(0x32000, float:2.86986E-40)
                byte[] r2 = new byte[r2]     // Catch: java.lang.Throwable -> L77 java.lang.Exception -> L79
                int r4 = r1.read(r2)     // Catch: java.lang.Throwable -> L77 java.lang.Exception -> L79
                r6 = -1
                if (r4 != r6) goto L73
            L4e:
                java.lang.String r2 = r10.d     // Catch: java.lang.Throwable -> L77 java.lang.Exception -> L79
                boolean r2 = io.dcloud.common.util.PdrUtil.isEmpty(r2)     // Catch: java.lang.Throwable -> L77 java.lang.Exception -> L79
                if (r2 == 0) goto L5b
                java.lang.String r0 = r3.toString()     // Catch: java.lang.Throwable -> L77 java.lang.Exception -> L79
                goto L61
            L5b:
                java.lang.String r2 = r10.d     // Catch: java.lang.Throwable -> L77 java.lang.Exception -> L79
                java.lang.String r0 = r3.toString(r2)     // Catch: java.lang.Throwable -> L77 java.lang.Exception -> L79
            L61:
                java.lang.String r0 = io.dcloud.common.util.JSONUtil.toJSONableString(r0)     // Catch: java.lang.Throwable -> L77 java.lang.Exception -> L79
                if (r1 == 0) goto L6a
                r1.close()     // Catch: java.io.IOException -> L6e
            L6a:
                r3.close()     // Catch: java.io.IOException -> L6e
                goto La2
            L6e:
                r1 = move-exception
                r1.printStackTrace()
                goto La2
            L73:
                r3.write(r2, r5, r4)     // Catch: java.lang.Throwable -> L77 java.lang.Exception -> L79
                goto L42
            L77:
                r0 = move-exception
                goto La7
            L79:
                r2 = move-exception
                r9 = r1
                r1 = r0
                r0 = r3
                r3 = r2
                r2 = r9
                goto L8d
            L80:
                r2 = move-exception
                goto La9
            L82:
                r2 = move-exception
                goto L8a
            L84:
                r2 = move-exception
                r1 = r0
                goto La9
            L87:
                r1 = move-exception
                r2 = r1
                r1 = r0
            L8a:
                r3 = r2
                r2 = r1
                r1 = r0
            L8d:
                r3.printStackTrace()     // Catch: java.lang.Throwable -> La3
                if (r2 == 0) goto L98
                r2.close()     // Catch: java.io.IOException -> L96
                goto L98
            L96:
                r0 = move-exception
                goto L9e
            L98:
                if (r0 == 0) goto La1
                r0.close()     // Catch: java.io.IOException -> L96
                goto La1
            L9e:
                r0.printStackTrace()
            La1:
                r0 = r1
            La2:
                return r0
            La3:
                r1 = move-exception
                r3 = r0
                r0 = r1
                r1 = r2
            La7:
                r2 = r0
                r0 = r3
            La9:
                if (r1 == 0) goto Lb1
                r1.close()     // Catch: java.io.IOException -> Laf
                goto Lb1
            Laf:
                r0 = move-exception
                goto Lb7
            Lb1:
                if (r0 == 0) goto Lba
                r0.close()     // Catch: java.io.IOException -> Laf
                goto Lba
            Lb7:
                r0.printStackTrace()
            Lba:
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: io.dcloud.js.file.FileFeatureImpl.a.onExecuting():java.lang.Object");
        }
    }

    public class b extends CustomTarget<File> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f6738a;
        public final /* synthetic */ IWebview b;
        public final /* synthetic */ String c;

        public b(String str, IWebview iWebview, String str2) {
            this.f6738a = str;
            this.b = iWebview;
            this.c = str2;
        }

        @Override // com.bumptech.glide.request.target.Target
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onResourceReady(File file, Transition<? super File> transition) {
            if (PdrUtil.isEmpty(this.f6738a)) {
                FileFeatureImpl.this.a(file.getAbsolutePath(), this.b, this.c);
            } else if (DHFile.copyFile(file.getPath(), this.f6738a) != 1) {
                Deprecated_JSUtil.execCallback(this.b, this.c, DOMException.toJSON(13, "Failed to load resource"), JSUtil.ERROR, true, false);
            } else {
                FileFeatureImpl.this.a(this.f6738a, this.b, this.c);
            }
        }

        @Override // com.bumptech.glide.request.target.Target
        public void onLoadCleared(Drawable drawable) {
        }

        @Override // com.bumptech.glide.request.target.CustomTarget, com.bumptech.glide.request.target.Target
        public void onLoadFailed(Drawable drawable) {
            Deprecated_JSUtil.execCallback(this.b, this.c, DOMException.toJSON(13, "Failed to load resource"), JSUtil.ERROR, true, false);
        }
    }

    public class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ File f6739a;
        public final /* synthetic */ String b;
        public final /* synthetic */ IWebview c;
        public final /* synthetic */ String d;

        public c(FileFeatureImpl fileFeatureImpl, File file, String str, IWebview iWebview, String str2) {
            this.f6739a = file;
            this.b = str;
            this.c = iWebview;
            this.d = str2;
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            String strMd5 = Md5Utils.md5(this.f6739a, this.b);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(AbsoluteConst.JSON_KEY_SIZE, this.f6739a.length());
                if (strMd5 != null) {
                    jSONObject.put(Constants.CodeCache.BANNER_DIGEST, strMd5.toUpperCase(Locale.US));
                }
            } catch (JSONException unused) {
            }
            Deprecated_JSUtil.execCallback(this.c, this.d, jSONObject.toString(), JSUtil.OK, true, false);
        }
    }

    public class d implements ISysEventListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f6740a;
        public final /* synthetic */ IApp b;
        public final /* synthetic */ IWebview c;
        public final /* synthetic */ String d;

        public d(FileFeatureImpl fileFeatureImpl, int i, IApp iApp, IWebview iWebview, String str) {
            this.f6740a = i;
            this.b = iApp;
            this.c = iWebview;
            this.d = str;
        }

        @Override // io.dcloud.common.DHInterface.ISysEventListener
        public boolean onExecute(ISysEventListener.SysEventType sysEventType, Object obj) {
            Object[] objArr = (Object[]) obj;
            int iIntValue = ((Integer) objArr[0]).intValue();
            Intent intent = (Intent) objArr[2];
            ISysEventListener.SysEventType sysEventType2 = ISysEventListener.SysEventType.onActivityResult;
            if (sysEventType == sysEventType2 && iIntValue == this.f6740a) {
                this.b.unregisterSysEventListener(this, sysEventType2);
                if (intent == null || (intent.getData() == null && intent.getClipData() == null)) {
                    Deprecated_JSUtil.execCallback(this.c, this.d, StringUtil.format(DOMException.JSON_ERROR_INFO, -2, DOMException.MSG_USER_CANCEL), JSUtil.ERROR, true, false);
                } else {
                    JSONArray jSONArray = new JSONArray();
                    ClipData clipData = intent.getClipData();
                    if (clipData != null) {
                        int itemCount = clipData.getItemCount();
                        for (int i = 0; i < itemCount; i++) {
                            jSONArray.put(FileUtil.getPathFromUri(this.b.getActivity(), clipData.getItemAt(i).getUri()));
                        }
                    } else {
                        Uri data = intent.getData();
                        String pathFromUri = FileUtil.getPathFromUri(this.b.getActivity(), data);
                        if (PdrUtil.isEmpty(pathFromUri)) {
                            pathFromUri = data.toString();
                        }
                        jSONArray.put(pathFromUri);
                    }
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("files", jSONArray);
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                    Deprecated_JSUtil.execCallback(this.c, this.d, jSONObject.toString(), JSUtil.OK, true, false);
                }
            }
            return false;
        }
    }

    public class e implements AsyncTaskHandler.IAsyncTaskListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String[] f6741a;
        public final /* synthetic */ IWebview b;
        public final /* synthetic */ String c;
        public final /* synthetic */ int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ String f6742e;

        public e(FileFeatureImpl fileFeatureImpl, String[] strArr, IWebview iWebview, String str, int i, String str2) {
            this.f6741a = strArr;
            this.b = iWebview;
            this.c = str;
            this.d = i;
            this.f6742e = str2;
        }

        @Override // io.dcloud.common.adapter.util.AsyncTaskHandler.IAsyncTaskListener
        public void onCancel() {
        }

        @Override // io.dcloud.common.adapter.util.AsyncTaskHandler.IAsyncTaskListener
        public void onExecuteBegin() {
        }

        @Override // io.dcloud.common.adapter.util.AsyncTaskHandler.IAsyncTaskListener
        public void onExecuteEnd(Object obj) {
        }

        @Override // io.dcloud.common.adapter.util.AsyncTaskHandler.IAsyncTaskListener
        public Object onExecuting() {
            byte[] bArrDecode2bytes = Base64.decode2bytes(this.f6741a[1]);
            if (bArrDecode2bytes == null) {
                Deprecated_JSUtil.execCallback(this.b, this.c, StringUtil.format(DOMException.JSON_ERROR_INFO, 16, this.b.getContext().getString(R.string.dcloud_io_write_non_base64)).toString(), JSUtil.ERROR, true, false);
                return null;
            }
            DHFile.writeFile(bArrDecode2bytes, this.d, this.f6742e);
            JSUtil.execCallback(this.b, this.c, bArrDecode2bytes.length, JSUtil.OK, false);
            return null;
        }
    }

    public class f implements AsyncTaskHandler.IAsyncTaskListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f6743a;
        public final /* synthetic */ int b;
        public final /* synthetic */ int c;
        public final /* synthetic */ IWebview d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ String f6744e;

        public f(String str, int i, int i2, IWebview iWebview, String str2) {
            this.f6743a = str;
            this.b = i;
            this.c = i2;
            this.d = iWebview;
            this.f6744e = str2;
        }

        @Override // io.dcloud.common.adapter.util.AsyncTaskHandler.IAsyncTaskListener
        public void onCancel() {
        }

        @Override // io.dcloud.common.adapter.util.AsyncTaskHandler.IAsyncTaskListener
        public void onExecuteBegin() {
        }

        @Override // io.dcloud.common.adapter.util.AsyncTaskHandler.IAsyncTaskListener
        public void onExecuteEnd(Object obj) {
            if (obj != null) {
                Deprecated_JSUtil.execCallback(this.d, this.f6744e, String.valueOf(obj), JSUtil.OK, true, false);
            } else {
                FileFeatureImpl.this.a(10, this.d, this.f6744e);
            }
        }

        /* JADX WARN: Can't wrap try/catch for region: R(13:0|2|78|3|77|4|(2:(1:7)|8)(4:9|(10:10|80|11|(1:82)(1:28)|43|73|44|(2:71|46)|(1:50)|53)|13|14)|75|15|(2:69|17)|(1:21)|53|(1:(0))) */
        /* JADX WARN: Code restructure failed: missing block: B:24:0x005e, code lost:
        
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:26:0x0060, code lost:
        
            r4 = e;
         */
        /* JADX WARN: Removed duplicated region for block: B:50:0x00a4 A[Catch: IOException -> 0x00a0, TRY_LEAVE, TryCatch #7 {IOException -> 0x00a0, blocks: (B:46:0x009c, B:50:0x00a4), top: B:71:0x009c }] */
        /* JADX WARN: Removed duplicated region for block: B:63:0x00ba A[Catch: IOException -> 0x00b6, TRY_LEAVE, TryCatch #3 {IOException -> 0x00b6, blocks: (B:59:0x00b2, B:63:0x00ba), top: B:67:0x00b2 }] */
        /* JADX WARN: Removed duplicated region for block: B:67:0x00b2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:71:0x009c A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // io.dcloud.common.adapter.util.AsyncTaskHandler.IAsyncTaskListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Object onExecuting() throws java.lang.Throwable {
            /*
                r15 = this;
                r0 = 1
                r1 = 0
                r2 = 0
                java.lang.String r3 = r15.f6743a     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L75
                java.lang.Object r3 = io.dcloud.common.adapter.io.DHFile.createFileHandler(r3)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L75
                java.io.InputStream r3 = io.dcloud.common.adapter.io.DHFile.getInputStream(r3)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L75
                int r4 = r3.available()     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L70
                int r5 = r15.b     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L70
                if (r5 <= 0) goto L2c
                if (r5 < r4) goto L19
                int r5 = r4 + (-1)
            L19:
                int r4 = r15.c     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L70
                int r5 = r5 - r4
                int r5 = r5 + r0
                byte[] r6 = new byte[r5]     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L70
                long r7 = (long) r4     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L70
                r3.skip(r7)     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L70
                r3.read(r6, r1, r5)     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L70
                java.lang.String r4 = io.dcloud.common.util.Base64.encode(r6)     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L70
                r5 = r2
                goto L48
            L2c:
                java.io.ByteArrayOutputStream r4 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L70
                r4.<init>()     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L70
            L31:
                r5 = 204800(0x32000, float:2.86986E-40)
                byte[] r5 = new byte[r5]     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L68
                int r6 = r3.read(r5)     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L68
                r7 = -1
                if (r6 != r7) goto L62
                byte[] r5 = r4.toByteArray()     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L68
                java.lang.String r5 = io.dcloud.common.util.Base64.encode(r5)     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L68
                r14 = r5
                r5 = r4
                r4 = r14
            L48:
                java.lang.String r2 = io.dcloud.common.util.JSONUtil.toJSONableString(r4)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L60
                if (r3 == 0) goto L54
                r3.close()     // Catch: java.io.IOException -> L52
                goto L54
            L52:
                r0 = move-exception
                goto L5a
            L54:
                if (r5 == 0) goto Lab
                r5.close()     // Catch: java.io.IOException -> L52
                goto Lab
            L5a:
                r0.printStackTrace()
                goto Lab
            L5e:
                r0 = move-exception
                goto Laf
            L60:
                r4 = move-exception
                goto L78
            L62:
                r4.write(r5, r1, r6)     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L68
                goto L31
            L66:
                r0 = move-exception
                goto Lb0
            L68:
                r5 = move-exception
                r14 = r5
                r5 = r4
                r4 = r14
                goto L78
            L6d:
                r0 = move-exception
                r5 = r2
                goto Lad
            L70:
                r4 = move-exception
                goto L77
            L72:
                r0 = move-exception
                r5 = r2
                goto Lae
            L75:
                r4 = move-exception
                r3 = r2
            L77:
                r5 = r2
            L78:
                java.lang.String r6 = "{code:%d,message:'%s'}"
                r7 = 2
                java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch: java.lang.Throwable -> Lac
                r8 = 13
                java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch: java.lang.Throwable -> Lac
                r7[r1] = r8     // Catch: java.lang.Throwable -> Lac
                java.lang.String r1 = r4.getMessage()     // Catch: java.lang.Throwable -> Lac
                r7[r0] = r1     // Catch: java.lang.Throwable -> Lac
                java.lang.String r10 = io.dcloud.common.util.StringUtil.format(r6, r7)     // Catch: java.lang.Throwable -> Lac
                io.dcloud.common.DHInterface.IWebview r8 = r15.d     // Catch: java.lang.Throwable -> Lac
                java.lang.String r9 = r15.f6744e     // Catch: java.lang.Throwable -> Lac
                int r11 = io.dcloud.common.util.JSUtil.ERROR     // Catch: java.lang.Throwable -> Lac
                r12 = 1
                r13 = 0
                io.dcloud.common.util.JSUtil.execCallback(r8, r9, r10, r11, r12, r13)     // Catch: java.lang.Throwable -> Lac
                if (r3 == 0) goto La2
                r3.close()     // Catch: java.io.IOException -> La0
                goto La2
            La0:
                r0 = move-exception
                goto La8
            La2:
                if (r5 == 0) goto Lab
                r5.close()     // Catch: java.io.IOException -> La0
                goto Lab
            La8:
                r0.printStackTrace()
            Lab:
                return r2
            Lac:
                r0 = move-exception
            Lad:
                r2 = r3
            Lae:
                r3 = r2
            Laf:
                r4 = r5
            Lb0:
                if (r3 == 0) goto Lb8
                r3.close()     // Catch: java.io.IOException -> Lb6
                goto Lb8
            Lb6:
                r1 = move-exception
                goto Lbe
            Lb8:
                if (r4 == 0) goto Lc1
                r4.close()     // Catch: java.io.IOException -> Lb6
                goto Lc1
            Lbe:
                r1.printStackTrace()
            Lc1:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: io.dcloud.js.file.FileFeatureImpl.f.onExecuting():java.lang.Object");
        }
    }

    private void b(IWebview iWebview, String[] strArr, String str) {
        try {
            String str2 = strArr[0];
            if (!FileUtil.checkPathAccord(iWebview.getContext(), str2)) {
                a(15, iWebview, str);
                return;
            }
            boolean zCheckPrivateDir = iWebview.obtainApp().checkPrivateDir(str2);
            int iIntValue = Integer.valueOf(strArr[2]).intValue();
            if (strArr[1] != null && !zCheckPrivateDir) {
                AsyncTaskHandler.executeThreadTask(new e(this, strArr, iWebview, str, iIntValue, str2));
                return;
            }
            a(4, iWebview, str);
        } catch (Exception unused) {
            a(10, iWebview, str);
        }
    }

    private String c(String str) {
        if (str.startsWith(f6734a)) {
            return AbsoluteConst.MINI_SERVER_APP_WWW + str.substring(f6734a.length(), str.length());
        }
        if (str.startsWith(c)) {
            return AbsoluteConst.MINI_SERVER_APP_DOC + str.substring(c.length(), str.length());
        }
        if (str.startsWith(d)) {
            return "_documents/" + str.substring(d.length(), str.length());
        }
        if (!str.startsWith(f6735e)) {
            return null;
        }
        return "_downloads/" + str.substring(f6735e.length(), str.length());
    }

    private String d(String str) {
        boolean z;
        String strB = b(str);
        if (PdrUtil.isEmpty(strB)) {
            strB = String.valueOf(-1);
            z = true;
        } else {
            z = false;
        }
        return z ? str.startsWith(f6734a) ? String.valueOf(1) : str.startsWith(c) ? String.valueOf(2) : str.startsWith(d) ? String.valueOf(3) : str.startsWith(f6735e) ? String.valueOf(4) : strB : strB;
    }

    private boolean e(String str) {
        return str.endsWith(BaseInfo.REL_PRIVATE_WWW_DIR) || str.endsWith(BaseInfo.REL_PUBLIC_DOCUMENTS_DIR) || str.endsWith(BaseInfo.REL_PUBLIC_DOWNLOADS_DIR) || str.endsWith(BaseInfo.REL_PRIVATE_DOC_DIR) || str.endsWith(AbsoluteConst.MINI_SERVER_APP_WWW) || str.endsWith("_documents/") || str.endsWith("_downloads/") || str.endsWith(AbsoluteConst.MINI_SERVER_APP_DOC);
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void dispose(String str) {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:613:0x0c17, code lost:
    
        if (io.dcloud.common.adapter.io.DHFile.copyAssetsFile(r0, r2) != false) goto L614;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:105:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x01e6  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x024d  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x0394  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x039b  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x0425 A[Catch: JSONException -> 0x0457, TryCatch #17 {JSONException -> 0x0457, blocks: (B:216:0x03a2, B:219:0x03aa, B:221:0x03b2, B:223:0x03b8, B:227:0x03c3, B:229:0x03cf, B:231:0x03d8, B:232:0x03f5, B:247:0x0425, B:248:0x0450, B:244:0x041f, B:235:0x0402, B:237:0x040c, B:238:0x040f), top: B:647:0x03a2, inners: #14 }] */
    /* JADX WARN: Removed duplicated region for block: B:248:0x0450 A[Catch: JSONException -> 0x0457, TRY_LEAVE, TryCatch #17 {JSONException -> 0x0457, blocks: (B:216:0x03a2, B:219:0x03aa, B:221:0x03b2, B:223:0x03b8, B:227:0x03c3, B:229:0x03cf, B:231:0x03d8, B:232:0x03f5, B:247:0x0425, B:248:0x0450, B:244:0x041f, B:235:0x0402, B:237:0x040c, B:238:0x040f), top: B:647:0x03a2, inners: #14 }] */
    /* JADX WARN: Removed duplicated region for block: B:349:0x067b  */
    /* JADX WARN: Removed duplicated region for block: B:350:0x0682  */
    /* JADX WARN: Removed duplicated region for block: B:419:0x07d9  */
    /* JADX WARN: Removed duplicated region for block: B:420:0x07e0  */
    /* JADX WARN: Removed duplicated region for block: B:619:0x0c22  */
    /* JADX WARN: Removed duplicated region for block: B:620:0x0c3c  */
    /* JADX WARN: Type inference failed for: r11v0, types: [io.dcloud.common.DHInterface.IApp] */
    /* JADX WARN: Type inference failed for: r11v2, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r11v4 */
    /* JADX WARN: Type inference failed for: r11v5 */
    /* JADX WARN: Type inference failed for: r11v6, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r11v7, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r11v8 */
    /* JADX WARN: Type inference failed for: r11v9 */
    /* JADX WARN: Type inference failed for: r19v0 */
    /* JADX WARN: Type inference failed for: r19v1 */
    /* JADX WARN: Type inference failed for: r19v2, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r19v3 */
    /* JADX WARN: Type inference failed for: r20v0, types: [io.dcloud.js.file.FileFeatureImpl] */
    @Override // io.dcloud.common.DHInterface.IFeature
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String execute(io.dcloud.common.DHInterface.IWebview r21, java.lang.String r22, java.lang.String[] r23) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 3294
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.js.file.FileFeatureImpl.execute(io.dcloud.common.DHInterface.IWebview, java.lang.String, java.lang.String[]):java.lang.String");
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void init(AbsMgr absMgr, String str) {
    }

    private void a(IWebview iWebview, IApp iApp, String[] strArr, String str) {
        String str2 = strArr[0];
        if (!FileUtil.checkPathAccord(iWebview.getContext(), str2)) {
            a(15, iWebview, str);
            return;
        }
        boolean zCheckPrivateDir = iApp.checkPrivateDir(str2);
        int i = PdrUtil.parseInt(strArr[1], 0);
        int i2 = PdrUtil.parseInt(strArr[2], -1);
        if (zCheckPrivateDir && iApp.isOnAppRunningMode()) {
            a(10, iWebview, str);
        } else {
            AsyncTaskHandler.executeThreadTask(new f(str2, i2, i, iWebview, str));
        }
    }

    public static String b(String str) {
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(str);
        if (fileExtensionFromUrl != null) {
            return MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl);
        }
        return null;
    }

    private void a(String str, String str2, IWebview iWebview, String str3) {
        if (PdrUtil.isEmpty(str) || PdrUtil.isNetPath(str)) {
            return;
        }
        String strConvert2AbsFullPath = iWebview.obtainApp().convert2AbsFullPath(iWebview.obtainFullUrl(), str);
        if (strConvert2AbsFullPath.startsWith("apps/")) {
            strConvert2AbsFullPath = "/" + strConvert2AbsFullPath;
        }
        if (strConvert2AbsFullPath.startsWith("/android_asset/")) {
            strConvert2AbsFullPath = strConvert2AbsFullPath.replace("/android_asset", "");
        } else if (strConvert2AbsFullPath.startsWith("android_asset/")) {
            strConvert2AbsFullPath = strConvert2AbsFullPath.replace(AssetUriLoader.ASSET_PATH_SEGMENT, "");
        }
        String strCheckPrivateDirAndCopy2Temp = iWebview.obtainApp().checkPrivateDirAndCopy2Temp(strConvert2AbsFullPath);
        if (!(FileUtil.checkPathAccord(iWebview.getContext(), strCheckPrivateDirAndCopy2Temp) || FileUtil.isFilePathForPublic(iWebview.getContext(), strCheckPrivateDirAndCopy2Temp))) {
            a(15, iWebview, str2);
            return;
        }
        if (!new File(strCheckPrivateDirAndCopy2Temp).exists()) {
            Deprecated_JSUtil.execCallback(iWebview, str2, StringUtil.format(DOMException.JSON_ERROR_INFO, -4, DOMException.MSG_FILE_NOT_EXIST), JSUtil.ERROR, true, false);
            return;
        }
        try {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            if (FileUtil.checkPrivatePath(iWebview.getContext(), strCheckPrivateDirAndCopy2Temp)) {
                mediaMetadataRetriever.setDataSource(strCheckPrivateDirAndCopy2Temp);
            } else {
                Uri videoFileUri = FileUtil.getVideoFileUri(iWebview.getContext(), strCheckPrivateDirAndCopy2Temp);
                if (videoFileUri != null) {
                    mediaMetadataRetriever.setDataSource(iWebview.getContext(), videoFileUri);
                }
            }
            String strExtractMetadata = mediaMetadataRetriever.extractMetadata(9);
            String strExtractMetadata2 = mediaMetadataRetriever.extractMetadata(18);
            String strExtractMetadata3 = mediaMetadataRetriever.extractMetadata(19);
            JSONObject jSONObject = new JSONObject();
            if (str3.equals("getVideoInfo")) {
                if (strExtractMetadata2 != null && strExtractMetadata3 != null) {
                    jSONObject.put("resolution", strExtractMetadata2 + Operators.MUL + strExtractMetadata3);
                }
                if (strExtractMetadata2 != null) {
                    try {
                        jSONObject.put("width", Float.parseFloat(strExtractMetadata2));
                    } catch (Exception unused) {
                    }
                }
                if (strExtractMetadata3 != null) {
                    jSONObject.put("height", Float.parseFloat(strExtractMetadata3));
                }
                jSONObject.put(AbsoluteConst.JSON_KEY_SIZE, new File(strCheckPrivateDirAndCopy2Temp).length());
                if (TextUtils.isEmpty(mediaMetadataRetriever.extractMetadata(32))) {
                    MediaExtractor mediaExtractor = new MediaExtractor();
                    if (FileUtil.checkPrivatePath(iWebview.getContext(), strCheckPrivateDirAndCopy2Temp)) {
                        mediaExtractor.setDataSource(strCheckPrivateDirAndCopy2Temp);
                    } else {
                        Uri videoFileUri2 = FileUtil.getVideoFileUri(iWebview.getContext(), strCheckPrivateDirAndCopy2Temp);
                        if (videoFileUri2 != null) {
                            mediaExtractor.setDataSource(iWebview.getContext(), videoFileUri2, (Map<String, String>) null);
                        }
                    }
                    int trackCount = mediaExtractor.getTrackCount();
                    for (int i = 0; i < trackCount; i++) {
                        MediaFormat trackFormat = mediaExtractor.getTrackFormat(i);
                        try {
                            String string = trackFormat.getString("mime");
                            if (!TextUtils.isEmpty(string) && string.startsWith("video/")) {
                                jSONObject.put("fps", trackFormat.getInteger("frame-rate"));
                            }
                        } catch (Exception unused2) {
                        }
                    }
                } else {
                    try {
                        jSONObject.put("fps", Math.ceil((Float.parseFloat(r7) / Float.parseFloat(strExtractMetadata)) * 1000.0f));
                    } catch (Exception unused3) {
                    }
                }
                String strExtractMetadata4 = mediaMetadataRetriever.extractMetadata(24);
                byte b2 = -1;
                int iHashCode = strExtractMetadata4.hashCode();
                if (iHashCode != 1815) {
                    if (iHashCode != 48873) {
                        if (iHashCode == 49803 && strExtractMetadata4.equals("270")) {
                            b2 = 1;
                        }
                    } else if (strExtractMetadata4.equals("180")) {
                        b2 = 2;
                    }
                } else if (strExtractMetadata4.equals("90")) {
                    b2 = 0;
                }
                jSONObject.put(Constants.Name.ORIENTATION, b2 != 0 ? b2 != 1 ? b2 != 2 ? "up" : Constants.Value.DIRECTION_DOWN : "left" : "right");
                String strExtractMetadata5 = mediaMetadataRetriever.extractMetadata(12);
                if (!TextUtils.isEmpty(strExtractMetadata5)) {
                    jSONObject.put("type", strExtractMetadata5);
                }
                String strExtractMetadata6 = mediaMetadataRetriever.extractMetadata(20);
                if (!TextUtils.isEmpty(strExtractMetadata6)) {
                    try {
                        jSONObject.put("bitrate", Integer.valueOf(Integer.parseInt(strExtractMetadata6) / 1000));
                    } catch (Exception unused4) {
                    }
                }
            }
            try {
                jSONObject.put("duration", BigDecimal.valueOf(Float.parseFloat(strExtractMetadata) / 1000.0f).setScale(2, 4));
            } catch (Exception unused5) {
            }
            Deprecated_JSUtil.execCallback(iWebview, str2, jSONObject.toString(), JSUtil.OK, true, false);
        } catch (Exception e2) {
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("code", 13);
                jSONObject2.put("message", e2.getMessage());
            } catch (JSONException unused6) {
            }
            Deprecated_JSUtil.execCallback(iWebview, str2, jSONObject2.toString(), JSUtil.ERROR, true, false);
        }
    }

    private JSONObject a(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (str.startsWith(f6734a)) {
            jSONObject.put("type", 1);
            jSONObject.put("fsName", "PRIVATE_WWW");
            jSONObject.put("fsRoot", io.dcloud.js.file.a.a("PRIVATE_WWW", f6734a, c(str), true));
        } else if (str.startsWith(c)) {
            jSONObject.put("type", 2);
            jSONObject.put("fsName", "PRIVATE_DOCUMENTS");
            jSONObject.put("fsRoot", io.dcloud.js.file.a.a("PRIVATE_DOCUMENTS", c, c(str), true));
        } else if (str.startsWith(d)) {
            jSONObject.put("type", 3);
            jSONObject.put("fsName", "PUBLIC_DOCUMENTS");
            jSONObject.put("fsRoot", io.dcloud.js.file.a.a("PUBLIC_DOCUMENTS", d, c(str), true));
        } else if (str.startsWith(f6735e)) {
            jSONObject.put("type", 4);
            jSONObject.put("fsName", "PUBLIC_DOWNLOADS");
            jSONObject.put("fsRoot", io.dcloud.js.file.a.a("PUBLIC_DOWNLOADS", f6735e, c(str), true));
        } else if (str.startsWith(b)) {
            jSONObject.put("type", 1);
            jSONObject.put("fsName", "PRIVATE_WWW");
            jSONObject.put("fsRoot", io.dcloud.js.file.a.a("PRIVATE_WWW", b, c(str), true));
        } else if (PdrUtil.isDeviceRootDir(str)) {
            jSONObject.put("type", 5);
            jSONObject.put("fsName", "PUBLIC_DEVICE_ROOT");
            jSONObject.put("fsRoot", io.dcloud.js.file.a.a("PUBLIC_DEVICE_ROOT", DeviceInfo.sDeviceRootDir, c(str), true));
        }
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, IWebview iWebview, String str) {
        Deprecated_JSUtil.execCallback(iWebview, str, a(iWebview.getContext(), i), JSUtil.ERROR, true, false);
    }

    private String a(Context context, int i) {
        switch (i) {
            case 1:
                return StringUtil.format(DOMException.JSON_ERROR_INFO, Integer.valueOf(i), context.getString(R.string.dcloud_io_file_not_found));
            case 2:
                return StringUtil.format(DOMException.JSON_ERROR_INFO, Integer.valueOf(i), context.getString(R.string.dcloud_io_without_authorization));
            case 3:
                return StringUtil.format(DOMException.JSON_ERROR_INFO, Integer.valueOf(i), context.getString(R.string.dcloud_common_cancel));
            case 4:
                return StringUtil.format(DOMException.JSON_ERROR_INFO, Integer.valueOf(i), context.getString(R.string.dcloud_io_file_not_read));
            case 5:
                return StringUtil.format(DOMException.JSON_ERROR_INFO, Integer.valueOf(i), context.getString(R.string.dcloud_io_coding_error));
            case 6:
                return StringUtil.format(DOMException.JSON_ERROR_INFO, Integer.valueOf(i), context.getString(R.string.dcloud_io_no_modification_allowed));
            case 7:
                return StringUtil.format(DOMException.JSON_ERROR_INFO, Integer.valueOf(i), context.getString(R.string.dcloud_io_invalid_state));
            case 8:
                return StringUtil.format(DOMException.JSON_ERROR_INFO, Integer.valueOf(i), context.getString(R.string.dcloud_io_grammar_mistakes));
            case 9:
                return StringUtil.format(DOMException.JSON_ERROR_INFO, Integer.valueOf(i), context.getString(R.string.dcloud_io_invalid_modification));
            case 10:
                return StringUtil.format(DOMException.JSON_ERROR_INFO, Integer.valueOf(i), context.getString(R.string.dcloud_io_perform_error));
            case 11:
                return StringUtil.format(DOMException.JSON_ERROR_INFO, Integer.valueOf(i), context.getString(R.string.dcloud_io_type_mismatch));
            case 12:
                return StringUtil.format(DOMException.JSON_ERROR_INFO, Integer.valueOf(i), context.getString(R.string.dcloud_io_path_exists));
            case 13:
            default:
                return StringUtil.format(DOMException.JSON_ERROR_INFO, Integer.valueOf(i), context.getString(R.string.dcloud_io_unknown_error));
            case 14:
                return StringUtil.format(DOMException.JSON_ERROR_INFO, Integer.valueOf(i), context.getString(R.string.dcloud_io_path_not_exist));
            case 15:
                return StringUtil.format(DOMException.JSON_ERROR_INFO, Integer.valueOf(i), DOMException.MSG_PATH_NOT_PRIVATE_ERROR);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, IWebview iWebview, String str2) {
        String attribute;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            String str3 = null;
            if (!FileUtil.checkPrivatePath(iWebview.getContext(), str) && Build.VERSION.SDK_INT >= 24) {
                InputStream fileInputStream = FileUtil.getFileInputStream(iWebview.getContext(), str);
                if (fileInputStream != null) {
                    BitmapFactory.decodeStream(fileInputStream, null, options);
                    attribute = new ExifInterface(fileInputStream).getAttribute(IFeature.F_ORIENTATION);
                    fileInputStream.close();
                } else {
                    attribute = null;
                }
            } else {
                attribute = new ExifInterface(str).getAttribute(IFeature.F_ORIENTATION);
                BitmapFactory.decodeFile(str, options);
            }
            int i = options.outWidth;
            int i2 = options.outHeight;
            String lowerCase = options.outMimeType;
            if (!PdrUtil.isEmpty(lowerCase) && lowerCase.contains("/")) {
                String[] strArrSplit = lowerCase.split("/");
                lowerCase = strArrSplit[strArrSplit.length - 1];
            }
            if (!TextUtils.isEmpty(attribute)) {
                switch (attribute) {
                    case "2":
                        str3 = "up-mirrored";
                        break;
                    case "3":
                        str3 = Constants.Value.DIRECTION_DOWN;
                        break;
                    case "4":
                        str3 = "down-mirrored";
                        break;
                    case "5":
                        str3 = "left-mirrored";
                        break;
                    case "6":
                        str3 = "right";
                        break;
                    case "7":
                        str3 = "right-mirrored";
                        break;
                    case "8":
                        str3 = "left";
                        break;
                    case "0":
                    case "1":
                    default:
                        str3 = "up";
                        break;
                }
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("path", "file://" + str);
            jSONObject.put("width", i);
            jSONObject.put("height", i2);
            jSONObject.put(Constants.Name.ORIENTATION, str3);
            if (!PdrUtil.isEmpty(lowerCase)) {
                lowerCase = lowerCase.toLowerCase();
            }
            jSONObject.put("type", lowerCase);
            try {
                JSUtil.execCallback(iWebview, str2, jSONObject, JSUtil.OK, false);
            } catch (IOException e2) {
                e = e2;
                Deprecated_JSUtil.execCallback(iWebview, str2, StringUtil.format(DOMException.JSON_ERROR_INFO, 13, e.getMessage()), JSUtil.ERROR, true, false);
            } catch (JSONException e3) {
                e = e3;
                Deprecated_JSUtil.execCallback(iWebview, str2, StringUtil.format(DOMException.JSON_ERROR_INFO, 13, e.getMessage()), JSUtil.ERROR, true, false);
            }
        } catch (IOException e4) {
            e = e4;
        } catch (JSONException e5) {
            e = e5;
        }
    }

    private String[] a(IWebview iWebview, String[] strArr, String str) {
        if (strArr.length > 1 && !PdrUtil.isEmpty(strArr[1])) {
            try {
                return JSUtil.jsonArrayToStringArr(new JSONArray(strArr[1]));
            } catch (JSONException e2) {
                e2.printStackTrace();
                a(8, iWebview, str);
            }
        }
        return null;
    }
}
