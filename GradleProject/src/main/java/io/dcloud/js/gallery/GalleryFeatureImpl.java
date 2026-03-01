package io.dcloud.js.gallery;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Parcelable;
import android.text.TextUtils;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.dmcbig.mediapicker.PickerConfig;
import com.dmcbig.mediapicker.entity.Media;
import com.taobao.weex.common.Constants;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IActivityHandler;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IFeature;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.util.PermissionUtil;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.FileUtil;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import supwisdom.ne;

/* JADX INFO: loaded from: classes3.dex */
public class GalleryFeatureImpl implements IFeature {
    public static String b = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + "/Camera/";
    public static int c = 1001;
    public static int d = 1002;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static int f6746e = 1003;
    public static int f = 1004;
    public static IWebview g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ArrayList<BroadcastReceiver> f6747a;

    public class a extends PermissionUtil.Request {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String[] f6748a;
        public final /* synthetic */ IWebview b;
        public final /* synthetic */ String c;

        public a(String[] strArr, IWebview iWebview, String str) {
            this.f6748a = strArr;
            this.b = iWebview;
            this.c = str;
        }

        @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
        public void onDenied(String str) {
            String json = DOMException.toJSON(12, DOMException.MSG_NO_PERMISSION);
            Deprecated_JSUtil.execCallback(this.b, this.f6748a[0], json, JSUtil.ERROR, true, false);
        }

        @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
        public void onGranted(String str) {
            String[] strArr = this.f6748a;
            boolean zOptBoolean = false;
            if (strArr.length >= 2) {
                String str2 = strArr[1];
                if (!PdrUtil.isEmpty(str2)) {
                    try {
                        zOptBoolean = new JSONObject(str2).optBoolean("multiple", false);
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            if (zOptBoolean) {
                GalleryFeatureImpl.this.a(this.b, this.c, this.f6748a);
            } else {
                GalleryFeatureImpl.this.b(this.b, this.c, this.f6748a);
            }
        }
    }

    public class b extends PermissionUtil.StreamPermissionRequest {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f6749a;
        public final /* synthetic */ IApp b;
        public final /* synthetic */ IWebview c;
        public final /* synthetic */ String d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ String[] f6750e;

        public class a extends CustomTarget<byte[]> {
            public a() {
            }

            @Override // com.bumptech.glide.request.target.Target
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onResourceReady(byte[] bArr, Transition<? super byte[]> transition) throws Throwable {
                String downloadFilename = PdrUtil.getDownloadFilename(null, "image/*", b.this.f6749a);
                if (!downloadFilename.contains(Operators.DOT_STR)) {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
                    String str = options.outMimeType;
                    if (!PdrUtil.isEmpty(str)) {
                        String[] strArrSplit = str.split("/");
                        if (strArrSplit.length > 1 && !PdrUtil.isEmpty(strArrSplit[1]) && !strArrSplit[1].contains(Operators.MUL)) {
                            downloadFilename = downloadFilename + Operators.DOT_STR + strArrSplit[1];
                        }
                    }
                }
                String pathFromUri = GalleryFeatureImpl.b + downloadFilename;
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
                if (FileUtil.needMediaStoreOpenFile(b.this.b.getActivity())) {
                    Uri uriCopyMediaFileToDCIM = FileUtil.copyMediaFileToDCIM(b.this.c.getContext(), byteArrayInputStream, downloadFilename);
                    if (uriCopyMediaFileToDCIM != null) {
                        pathFromUri = FileUtil.getPathFromUri(b.this.c.getContext(), uriCopyMediaFileToDCIM);
                    } else {
                        String json = DOMException.toJSON(12, "SAVE ERROR");
                        b bVar = b.this;
                        Deprecated_JSUtil.execCallback(bVar.c, bVar.d, json, JSUtil.ERROR, true, false);
                    }
                } else {
                    FileUtil.writeStream2File(byteArrayInputStream, new File(pathFromUri));
                }
                String strConvert2WebviewFullPath = b.this.b.convert2WebviewFullPath(null, pathFromUri);
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("file", strConvert2WebviewFullPath);
                    jSONObject.put("path", strConvert2WebviewFullPath);
                    b bVar2 = b.this;
                    JSUtil.execCallback(bVar2.c, bVar2.d, jSONObject, JSUtil.OK, false);
                    b.this.c.getContext().sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.parse("file://" + pathFromUri)));
                } catch (JSONException unused) {
                }
            }

            @Override // com.bumptech.glide.request.target.Target
            public void onLoadCleared(Drawable drawable) {
            }

            @Override // com.bumptech.glide.request.target.CustomTarget, com.bumptech.glide.request.target.Target
            public void onLoadFailed(Drawable drawable) {
                String json = DOMException.toJSON(12, "UNKOWN ERROR");
                b bVar = b.this;
                Deprecated_JSUtil.execCallback(bVar.c, bVar.d, json, JSUtil.ERROR, true, false);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(GalleryFeatureImpl galleryFeatureImpl, IApp iApp, String str, IApp iApp2, IWebview iWebview, String str2, String[] strArr) {
            super(iApp);
            this.f6749a = str;
            this.b = iApp2;
            this.c = iWebview;
            this.d = str2;
            this.f6750e = strArr;
        }

        @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
        public void onDenied(String str) {
            Deprecated_JSUtil.execCallback(this.c, this.d, DOMException.toJSON(12, DOMException.MSG_NO_PERMISSION), JSUtil.ERROR, true, false);
        }

        /* JADX WARN: Removed duplicated region for block: B:27:0x00be  */
        @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onGranted(java.lang.String r8) {
            /*
                Method dump skipped, instruction units count: 308
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: io.dcloud.js.gallery.GalleryFeatureImpl.b.onGranted(java.lang.String):void");
        }
    }

    public class c implements ISysEventListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ IApp f6752a;
        public final /* synthetic */ IWebview b;
        public final /* synthetic */ String c;

        public c(IApp iApp, IWebview iWebview, String str) {
            this.f6752a = iApp;
            this.b = iWebview;
            this.c = str;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:36:0x00b1  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x00b3  */
        /* JADX WARN: Type inference failed for: r3v10, types: [java.lang.String[]] */
        /* JADX WARN: Type inference failed for: r3v11 */
        /* JADX WARN: Type inference failed for: r3v4 */
        /* JADX WARN: Type inference failed for: r3v5 */
        /* JADX WARN: Type inference failed for: r3v7, types: [int] */
        /* JADX WARN: Type inference failed for: r3v9, types: [java.lang.String[]] */
        /* JADX WARN: Type inference failed for: r8v0 */
        /* JADX WARN: Type inference failed for: r8v1, types: [java.lang.String[]] */
        /* JADX WARN: Type inference failed for: r8v2 */
        @Override // io.dcloud.common.DHInterface.ISysEventListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean onExecute(io.dcloud.common.DHInterface.ISysEventListener.SysEventType r11, java.lang.Object r12) {
            /*
                r10 = this;
                java.lang.Object[] r12 = (java.lang.Object[]) r12
                r0 = 0
                r1 = r12[r0]
                java.lang.Integer r1 = (java.lang.Integer) r1
                int r1 = r1.intValue()
                r2 = 1
                r3 = r12[r2]
                java.lang.Integer r3 = (java.lang.Integer) r3
                r3.intValue()
                r3 = 2
                r12 = r12[r3]
                android.content.Intent r12 = (android.content.Intent) r12
                io.dcloud.common.DHInterface.ISysEventListener$SysEventType r3 = io.dcloud.common.DHInterface.ISysEventListener.SysEventType.onActivityResult
                if (r11 != r3) goto Lb8
                io.dcloud.common.DHInterface.IApp r11 = r10.f6752a
                r11.unregisterSysEventListener(r10, r3)
                r11 = 0
                int r3 = io.dcloud.js.gallery.GalleryFeatureImpl.b()     // Catch: java.lang.Exception -> La6
                if (r1 != r3) goto L30
                java.lang.String r1 = "all_path"
                java.lang.String[] r1 = r12.getStringArrayExtra(r1)     // Catch: java.lang.Exception -> La6
                goto La4
            L30:
                int r3 = io.dcloud.js.gallery.GalleryFeatureImpl.c()     // Catch: java.lang.Exception -> La6
                if (r1 != r3) goto L92
                if (r12 == 0) goto La6
                android.content.ClipData r1 = r12.getClipData()     // Catch: java.lang.Exception -> La6
                if (r1 == 0) goto L60
                int r2 = r1.getItemCount()     // Catch: java.lang.Exception -> La6
                java.lang.String[] r3 = new java.lang.String[r2]     // Catch: java.lang.Exception -> La6
                r4 = 0
            L45:
                if (r4 >= r2) goto La7
                android.content.ClipData$Item r5 = r1.getItemAt(r4)     // Catch: java.lang.Exception -> L5e
                android.net.Uri r5 = r5.getUri()     // Catch: java.lang.Exception -> L5e
                io.dcloud.common.DHInterface.IWebview r6 = r10.b     // Catch: java.lang.Exception -> L5e
                android.app.Activity r6 = r6.getActivity()     // Catch: java.lang.Exception -> L5e
                java.lang.String r5 = io.dcloud.common.adapter.util.ContentUriUtil.getImageAbsolutePath(r6, r5)     // Catch: java.lang.Exception -> L5e
                r3[r4] = r5     // Catch: java.lang.Exception -> L5e
                int r4 = r4 + 1
                goto L45
            L5e:
                goto La7
            L60:
                android.net.Uri r1 = r12.getData()     // Catch: java.lang.Exception -> La6
                if (r1 == 0) goto La6
                android.net.Uri r1 = r12.getData()     // Catch: java.lang.Exception -> La6
                java.lang.String r1 = r1.getPath()     // Catch: java.lang.Exception -> La6
                boolean r1 = io.dcloud.common.util.PdrUtil.isDeviceRootDir(r1)     // Catch: java.lang.Exception -> La6
                if (r1 == 0) goto L7d
                android.net.Uri r1 = r12.getData()     // Catch: java.lang.Exception -> La6
                java.lang.String r1 = r1.getPath()     // Catch: java.lang.Exception -> La6
                goto L8b
            L7d:
                io.dcloud.common.DHInterface.IWebview r1 = r10.b     // Catch: java.lang.Exception -> La6
                android.app.Activity r1 = r1.getActivity()     // Catch: java.lang.Exception -> La6
                android.net.Uri r3 = r12.getData()     // Catch: java.lang.Exception -> La6
                java.lang.String r1 = io.dcloud.common.adapter.util.ContentUriUtil.getImageAbsolutePath(r1, r3)     // Catch: java.lang.Exception -> La6
            L8b:
                if (r1 == 0) goto La6
                java.lang.String[] r3 = new java.lang.String[r2]     // Catch: java.lang.Exception -> La6
                r3[r0] = r1     // Catch: java.lang.Exception -> L5e
                goto La7
            L92:
                int r2 = io.dcloud.js.gallery.GalleryFeatureImpl.d()     // Catch: java.lang.Exception -> La6
                if (r1 != r2) goto La6
                java.lang.String r1 = "select_result"
                java.util.ArrayList r1 = r12.getParcelableArrayListExtra(r1)     // Catch: java.lang.Exception -> La6
                io.dcloud.js.gallery.GalleryFeatureImpl r2 = io.dcloud.js.gallery.GalleryFeatureImpl.this     // Catch: java.lang.Exception -> La6
                java.lang.String[] r1 = r2.mediasToJSONArray(r1)     // Catch: java.lang.Exception -> La6
            La4:
                r3 = r1
                goto La7
            La6:
                r3 = r11
            La7:
                io.dcloud.js.gallery.GalleryFeatureImpl r4 = io.dcloud.js.gallery.GalleryFeatureImpl.this
                io.dcloud.common.DHInterface.IApp r5 = r10.f6752a
                io.dcloud.common.DHInterface.IWebview r6 = r10.b
                java.lang.String r7 = r10.c
                if (r12 == 0) goto Lb3
                r8 = r3
                goto Lb4
            Lb3:
                r8 = r11
            Lb4:
                r9 = 1
                io.dcloud.js.gallery.GalleryFeatureImpl.a(r4, r5, r6, r7, r8, r9)
            Lb8:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: io.dcloud.js.gallery.GalleryFeatureImpl.c.onExecute(io.dcloud.common.DHInterface.ISysEventListener$SysEventType, java.lang.Object):boolean");
        }
    }

    public class d extends BroadcastReceiver {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Activity f6753a;

        public d(Activity activity) {
            this.f6753a = activity;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String stringExtra = intent.getStringExtra("_onMaxedId");
            if (!TextUtils.isEmpty(stringExtra)) {
                GalleryFeatureImpl.onMaxed(this.f6753a, stringExtra);
                return;
            }
            if (GalleryFeatureImpl.this.f6747a != null) {
                GalleryFeatureImpl.this.f6747a.remove(this);
            }
            ne.a(this.f6753a).a(this);
        }
    }

    public class e implements ISysEventListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ IApp f6754a;
        public final /* synthetic */ IWebview b;
        public final /* synthetic */ String c;

        public e(IApp iApp, IWebview iWebview, String str) {
            this.f6754a = iApp;
            this.b = iWebview;
            this.c = str;
        }

        @Override // io.dcloud.common.DHInterface.ISysEventListener
        public boolean onExecute(ISysEventListener.SysEventType sysEventType, Object obj) {
            String[] strArrMediasToJSONArray;
            Uri data;
            Object[] objArr = (Object[]) obj;
            int iIntValue = ((Integer) objArr[0]).intValue();
            ((Integer) objArr[1]).intValue();
            Intent intent = (Intent) objArr[2];
            ISysEventListener.SysEventType sysEventType2 = ISysEventListener.SysEventType.onActivityResult;
            if (sysEventType == sysEventType2) {
                this.f6754a.unregisterSysEventListener(this, sysEventType2);
                if (iIntValue == GalleryFeatureImpl.c) {
                    String path = (intent == null || (data = intent.getData()) == null) ? null : PdrUtil.isDeviceRootDir(data.getPath()) ? data.getPath() : data.toString();
                    GalleryFeatureImpl.this.a(this.f6754a, this.b, this.c, path != null ? new String[]{path} : null, false);
                } else if (iIntValue == GalleryFeatureImpl.f) {
                    String str = (intent == null || (strArrMediasToJSONArray = GalleryFeatureImpl.this.mediasToJSONArray(intent.getParcelableArrayListExtra(PickerConfig.EXTRA_RESULT))) == null || strArrMediasToJSONArray.length <= 0) ? null : strArrMediasToJSONArray[0];
                    GalleryFeatureImpl.this.a(this.f6754a, this.b, this.c, str != null ? new String[]{str} : null, false);
                }
            }
            return false;
        }
    }

    public static void onMaxed(Context context, String str) {
        IWebview iWebview = g;
        if (iWebview != null) {
            JSUtil.execCallback(iWebview, str, "", JSUtil.OK, true);
            return;
        }
        Intent intent = new Intent("io.dcloud.streamapp.Gallery.onMax." + str);
        intent.putExtra("_onMaxedId", str);
        ne.a(context).a(intent);
    }

    public ArrayList<Media> JSONArrayToMedias(int i, JSONArray jSONArray) {
        ArrayList<Media> arrayList = null;
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            try {
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                File file = new File(URI.create(jSONArray.getString(i2)));
                if (file.exists()) {
                    arrayList.add(new Media(file.getPath(), file.getName(), 0L, i == 102 ? 3 : 1, file.length(), 0, ""));
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return arrayList;
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void dispose(String str) {
        ArrayList<BroadcastReceiver> arrayList = this.f6747a;
        if (arrayList != null && g != null) {
            Iterator<BroadcastReceiver> it = arrayList.iterator();
            while (it.hasNext()) {
                ne.a(g.getActivity()).a(it.next());
            }
            this.f6747a.clear();
        }
        g = null;
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public String execute(IWebview iWebview, String str, String[] strArr) {
        g = iWebview;
        if ("pick".equals(str)) {
            PermissionUtil.usePermission(iWebview.obtainApp().getActivity(), PermissionUtil.PMS_STORAGE, new a(strArr, iWebview, str));
            return null;
        }
        if (!"save".equals(str)) {
            return null;
        }
        IApp iAppObtainApp = iWebview.obtainFrameView().obtainApp();
        PermissionUtil.usePermission(iAppObtainApp.getActivity(), PermissionUtil.PMS_STORAGE, new b(this, iAppObtainApp, strArr[0], iAppObtainApp, iWebview, strArr[1], strArr));
        return null;
    }

    public Intent getMediaPickerIntent(Context context, String str, int i, JSONArray jSONArray, String str2, boolean z, String str3, JSONObject jSONObject, String str4, String str5, boolean z2) {
        Intent intent = new Intent();
        int i2 = (str.contains("video") && str.contains("image")) ? 101 : str.contains("video") ? 102 : 100;
        boolean zContains = str.contains("__Single__");
        intent.setClassName(context, "com.dmcbig.mediapicker.PickerActivity");
        intent.putExtra(PickerConfig.SELECT_MODE, i2);
        intent.putExtra(PickerConfig.SINGLE_SELECT, zContains);
        intent.putExtra(PickerConfig.COMPRESSED, z2);
        if (i > 0) {
            intent.putExtra(PickerConfig.MAX_SELECT_COUNT, i);
        }
        if (jSONArray != null) {
            intent.putExtra(PickerConfig.DEFAULT_SELECTED_LIST, JSONArrayToMedias(i2, jSONArray));
        }
        if (!TextUtils.isEmpty(str3)) {
            intent.putExtra(PickerConfig.SELECTED_MAX_CALLBACK_ID, str3);
        }
        if (!TextUtils.isEmpty(str2)) {
            intent.putExtra(PickerConfig.DONE_BUTTON_TEXT, str2);
        }
        if (!TextUtils.isEmpty(str4)) {
            intent.putExtra(PickerConfig.SIZE_TYPE, str4);
        }
        if (!TextUtils.isEmpty(str5)) {
            intent.putExtra(PickerConfig.DOC_PATH, str5);
        }
        if (jSONObject != null && jSONObject.has("width") && jSONObject.has("height")) {
            Pattern patternCompile = Pattern.compile("[^0-9]");
            try {
                int i3 = Integer.parseInt(patternCompile.matcher(jSONObject.optString("width")).replaceAll(""));
                int i4 = Integer.parseInt(patternCompile.matcher(jSONObject.optString("height")).replaceAll(""));
                if (i3 > 0 && i4 > 0) {
                    jSONObject.put("width", i3);
                    jSONObject.put("height", i4);
                    intent.putExtra(PickerConfig.IMAGE_CROP, jSONObject.toString());
                    intent.putExtra(PickerConfig.MAX_SELECT_COUNT, 1);
                }
            } catch (Exception unused) {
            }
        }
        intent.putExtra(PickerConfig.IMAGE_EDITABLE, z);
        return intent;
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void init(AbsMgr absMgr, String str) {
    }

    public String[] mediasToJSONArray(ArrayList<Parcelable> arrayList) {
        String[] strArr = null;
        for (int i = 0; i < arrayList.size(); i++) {
            Media media = (Media) arrayList.get(i);
            if (strArr == null) {
                strArr = new String[arrayList.size()];
            }
            strArr[i] = media.path;
        }
        return strArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(IWebview iWebview, String str, String[] strArr) {
        String str2;
        String strOptString;
        JSONObject jSONObject;
        String str3;
        boolean z;
        boolean zOptBoolean;
        String str4;
        try {
            String str5 = strArr[0];
            try {
                IApp iAppObtainApp = iWebview.obtainFrameView().obtainApp();
                try {
                    iAppObtainApp.registerSysEventListener(new e(iAppObtainApp, iWebview, str5), ISysEventListener.SysEventType.onActivityResult);
                    String str6 = "image/*";
                    if (PdrUtil.isEmpty(strArr[1])) {
                        strOptString = null;
                        jSONObject = null;
                        str3 = null;
                        z = true;
                        zOptBoolean = false;
                    } else {
                        JSONObject jSONObjectCreateJSONObject = JSONUtil.createJSONObject(strArr[1]);
                        String string = JSONUtil.getString(jSONObjectCreateJSONObject, Constants.Name.FILTER);
                        if ("video".equals(string)) {
                            str6 = "video/*";
                        } else if ("none".equals(string)) {
                            str6 = "image/*|video/*";
                        }
                        strOptString = jSONObjectCreateJSONObject.optString("confirmText");
                        boolean zOptBoolean2 = jSONObjectCreateJSONObject.optBoolean("editable", true);
                        JSONObject jSONObjectOptJSONObject = jSONObjectCreateJSONObject.optJSONObject("crop");
                        String strOptString2 = jSONObjectCreateJSONObject.optString("sizeType");
                        zOptBoolean = jSONObjectCreateJSONObject.optBoolean("videoCompress", false);
                        jSONObject = jSONObjectOptJSONObject;
                        str3 = strOptString2;
                        z = zOptBoolean2;
                    }
                    new Intent("android.intent.action.PICK");
                    String str7 = str6 + "__Single__";
                    if (strArr.length > 2) {
                        String str8 = strArr[2];
                        a(iWebview.getActivity(), str8);
                        str4 = str8;
                    } else {
                        str4 = null;
                    }
                    iWebview.getActivity().startActivityForResult(getMediaPickerIntent(iWebview.getContext(), str7, 1, null, strOptString, z, str4, jSONObject, str3, iWebview.obtainApp().convert2LocalFullPath(null, BaseInfo.REL_PRIVATE_DOC_DIR), zOptBoolean), f);
                } catch (Exception e2) {
                    e = e2;
                    str2 = str5;
                    e.printStackTrace();
                    Deprecated_JSUtil.execCallback(iWebview, str2, DOMException.toJSON(12, e.getMessage()), JSUtil.ERROR, true, false);
                }
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Exception e4) {
            e = e4;
            str2 = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(IWebview iWebview, String str, String[] strArr) {
        String str2;
        String str3;
        String str4;
        IApp iAppObtainApp;
        JSONArray jSONArrayOptJSONArray;
        boolean zOptBoolean;
        JSONObject jSONObjectOptJSONObject;
        String strOptString;
        boolean zOptBoolean2;
        String str5;
        String str6;
        int i;
        int i2;
        String str7;
        try {
            str3 = strArr[0];
            try {
                iAppObtainApp = iWebview.obtainFrameView().obtainApp();
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            str2 = null;
        }
        try {
            iAppObtainApp.registerSysEventListener(new c(iAppObtainApp, iWebview, str3), ISysEventListener.SysEventType.onActivityResult);
            Intent intent = new Intent();
            String str8 = "image/*";
            int iOptInt = -1;
            if (PdrUtil.isEmpty(strArr[1])) {
                str5 = null;
                jSONArrayOptJSONArray = null;
                zOptBoolean = true;
                jSONObjectOptJSONObject = null;
                strOptString = null;
                zOptBoolean2 = false;
            } else {
                try {
                    JSONObject jSONObjectCreateJSONObject = JSONUtil.createJSONObject(strArr[1]);
                    String string = JSONUtil.getString(jSONObjectCreateJSONObject, Constants.Name.FILTER);
                    if ("video".equals(string)) {
                        str8 = "video/*";
                    } else if ("none".equals(string)) {
                        str8 = "image/*|video/*";
                    }
                    iOptInt = jSONObjectCreateJSONObject.optInt("maximum", -1);
                    jSONArrayOptJSONArray = jSONObjectCreateJSONObject.optJSONArray("selected");
                    String strOptString2 = jSONObjectCreateJSONObject.optString("confirmText");
                    zOptBoolean = jSONObjectCreateJSONObject.optBoolean("editable", true);
                    jSONObjectOptJSONObject = jSONObjectCreateJSONObject.optJSONObject("crop");
                    strOptString = jSONObjectCreateJSONObject.optString("sizeType");
                    zOptBoolean2 = jSONObjectCreateJSONObject.optBoolean("videoCompress", false);
                    str5 = strOptString2;
                } catch (Exception e4) {
                    e = e4;
                    str2 = str3;
                    e.printStackTrace();
                    Deprecated_JSUtil.execCallback(iWebview, str2, DOMException.toJSON(12, e.getMessage()), JSUtil.ERROR, true, false);
                }
            }
            intent.setType(str8);
            try {
                if (Build.VERSION.SDK_INT >= 19) {
                    intent.setAction("android.intent.action.OPEN_DOCUMENT");
                    intent.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
                    intent.addCategory("android.intent.category.OPENABLE");
                    i2 = f6746e;
                    try {
                        Iterator<ResolveInfo> it = iWebview.getActivity().getPackageManager().queryIntentActivities(intent, 0).iterator();
                        while (it.hasNext()) {
                            try {
                                if ("com.android.documentsui".equals(it.next().activityInfo.packageName)) {
                                    break;
                                }
                            } catch (Exception e5) {
                                e = e5;
                                str4 = str3;
                                e.printStackTrace();
                                iWebview.getActivity().startActivityForResult(intent, i2);
                            }
                        }
                        if (strArr.length > 2) {
                            String str9 = strArr[2];
                            a(iWebview.getActivity(), str9);
                            str7 = str9;
                        } else {
                            str7 = null;
                        }
                        str4 = str3;
                        try {
                            intent = getMediaPickerIntent(iWebview.getContext(), str8, iOptInt, jSONArrayOptJSONArray, str5, zOptBoolean, str7, jSONObjectOptJSONObject, strOptString, iWebview.obtainApp().convert2LocalFullPath(null, BaseInfo.REL_PRIVATE_DOC_DIR), zOptBoolean2);
                            try {
                                i = f;
                            } catch (Exception e6) {
                                e = e6;
                                e.printStackTrace();
                            }
                        } catch (Exception e7) {
                            e = e7;
                            intent = intent;
                        }
                    } catch (Exception e8) {
                        e = e8;
                        str4 = str3;
                    }
                } else {
                    str4 = str3;
                    if (strArr.length > 2) {
                        String str10 = strArr[2];
                        intent.putExtra("_onMaxedId", str10);
                        a(iWebview.getActivity(), str10);
                        str6 = str10;
                    } else {
                        str6 = null;
                    }
                    intent = getMediaPickerIntent(iWebview.getContext(), str8, iOptInt, jSONArrayOptJSONArray, str5, zOptBoolean, str6, jSONObjectOptJSONObject, strOptString, iWebview.obtainApp().convert2LocalFullPath(null, BaseInfo.REL_PRIVATE_DOC_DIR), zOptBoolean2);
                    i = f;
                }
                i2 = i;
                iWebview.getActivity().startActivityForResult(intent, i2);
            } catch (Exception e9) {
                e = e9;
                str2 = str4;
                e.printStackTrace();
                Deprecated_JSUtil.execCallback(iWebview, str2, DOMException.toJSON(12, e.getMessage()), JSUtil.ERROR, true, false);
            }
        } catch (Exception e10) {
            e = e10;
            str4 = str3;
            str2 = str4;
            e.printStackTrace();
            Deprecated_JSUtil.execCallback(iWebview, str2, DOMException.toJSON(12, e.getMessage()), JSUtil.ERROR, true, false);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void a(Activity activity, String str) {
        if ((activity instanceof IActivityHandler) && ((IActivityHandler) activity).isMultiProcessMode()) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("io.dcloud.streamapp.Gallery.onMax." + str);
            d dVar = new d(activity);
            if (this.f6747a == null) {
                this.f6747a = new ArrayList<>();
            }
            this.f6747a.add(dVar);
            ne.a(activity).a(dVar, intentFilter);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(IApp iApp, IWebview iWebview, String str, String[] strArr, boolean z) {
        String str2;
        boolean z2;
        String strConvert2WebviewFullPath;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("multiple", z);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        JSONArray jSONArray = new JSONArray();
        if (strArr == null || strArr.length <= 0) {
            str2 = "User cancelled";
            z2 = false;
        } else {
            for (String str3 : strArr) {
                if (str3.startsWith("content://")) {
                    strConvert2WebviewFullPath = FileUtil.getPathFromUri(iApp.getActivity(), Uri.parse(str3));
                    if (!TextUtils.isEmpty(strConvert2WebviewFullPath) && !strConvert2WebviewFullPath.startsWith("file://")) {
                        strConvert2WebviewFullPath = "file://" + strConvert2WebviewFullPath;
                    }
                } else {
                    strConvert2WebviewFullPath = iApp.convert2WebviewFullPath(null, str3);
                }
                jSONArray.put(strConvert2WebviewFullPath);
            }
            try {
                jSONObject.put("files", jSONArray);
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
            z2 = true;
            str2 = "pickImage path wrong";
        }
        if (z2) {
            JSUtil.execCallback(iWebview, str, jSONObject, JSUtil.OK, false);
        } else {
            Deprecated_JSUtil.execCallback(iWebview, str, DOMException.toJSON(12, str2), JSUtil.ERROR, true, false);
        }
    }
}
