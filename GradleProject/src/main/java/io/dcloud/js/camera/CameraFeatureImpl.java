package io.dcloud.js.camera;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.text.TextUtils;
import androidtranscoder.MediaTranscoder;
import androidtranscoder.format.MediaFormatStrategyPresets;
import androidx.core.content.FileProvider;
import com.dmcbig.mediapicker.entity.Media;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IFeature;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.io.DHFile;
import io.dcloud.common.adapter.util.ContentUriUtil;
import io.dcloud.common.adapter.util.PermissionUtil;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.util.CompressUtil;
import io.dcloud.common.util.DCFileUriData;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.FileUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.ThreadPool;
import io.dcloud.feature.gallery.imageedit.IMGEditActivity;
import io.dcloud.js.camera.CameraFeatureImpl;
import io.dcloud.js.camera.a;
import java.io.File;
import java.io.IOException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class CameraFeatureImpl implements IFeature {

    public class a extends PermissionUtil.StreamPermissionRequest {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ IWebview f6716a;
        public final /* synthetic */ String b;
        public final /* synthetic */ String c;
        public final /* synthetic */ a.C0195a d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ IApp f6717e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(CameraFeatureImpl cameraFeatureImpl, IApp iApp, IWebview iWebview, String str, String str2, a.C0195a c0195a, IApp iApp2) {
            super(iApp);
            this.f6716a = iWebview;
            this.b = str;
            this.c = str2;
            this.d = c0195a;
            this.f6717e = iApp2;
        }

        @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
        public void onDenied(String str) {
            Deprecated_JSUtil.execCallback(this.f6716a, this.b, DOMException.toJSON(11, DOMException.MSG_NO_PERMISSION), JSUtil.ERROR, true, false);
        }

        @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
        public void onGranted(String str) {
            try {
                if (JSUtil.checkOperateDirErrorAndCallback(this.f6716a, this.b, this.c)) {
                    Deprecated_JSUtil.execCallback(this.f6716a, this.b, DOMException.toJSON(-5, DOMException.MSG_IO_ERROR), JSUtil.ERROR, true, false);
                    return;
                }
                File file = new File(this.c);
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                DCFileUriData shareImageUri = FileUtil.getShareImageUri(this.f6716a.getContext(), file, this.d.a(), intent);
                this.f6717e.registerSysEventListener(new C0189a(shareImageUri), ISysEventListener.SysEventType.onActivityResult);
                intent.putExtra("output", shareImageUri.fileUri);
                this.f6716a.getActivity().startActivityForResult(intent, io.dcloud.js.camera.a.f6730e);
            } catch (Exception e2) {
                Deprecated_JSUtil.execCallback(this.f6716a, this.b, DOMException.toJSON(11, e2.getMessage()), JSUtil.ERROR, true, false);
            }
        }

        /* JADX INFO: renamed from: io.dcloud.js.camera.CameraFeatureImpl$a$a, reason: collision with other inner class name */
        public class C0189a implements ISysEventListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ DCFileUriData f6718a;

            public C0189a(DCFileUriData dCFileUriData) {
                this.f6718a = dCFileUriData;
            }

            @Override // io.dcloud.common.DHInterface.ISysEventListener
            public boolean onExecute(ISysEventListener.SysEventType sysEventType, Object obj) {
                Object[] objArr = (Object[]) obj;
                int iIntValue = ((Integer) objArr[0]).intValue();
                int iIntValue2 = ((Integer) objArr[1]).intValue();
                if (sysEventType == ISysEventListener.SysEventType.onActivityResult && iIntValue == io.dcloud.js.camera.a.f6730e) {
                    if (iIntValue2 == -1) {
                        ThreadPool.self().addThreadTask(new RunnableC0190a());
                    } else {
                        String json = DOMException.toJSON(11, "resultCode is wrong");
                        a aVar = a.this;
                        Deprecated_JSUtil.execCallback(aVar.f6716a, aVar.b, json, JSUtil.ERROR, true, false);
                    }
                    a.this.f6717e.unregisterSysEventListener(this, sysEventType);
                }
                return false;
            }

            /* JADX INFO: renamed from: io.dcloud.js.camera.CameraFeatureImpl$a$a$a, reason: collision with other inner class name */
            public class RunnableC0190a implements Runnable {

                /* JADX INFO: renamed from: io.dcloud.js.camera.CameraFeatureImpl$a$a$a$a, reason: collision with other inner class name */
                public class RunnableC0191a implements Runnable {
                    public RunnableC0191a() {
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        String json = DOMException.toJSON(-5, DOMException.MSG_IO_ERROR);
                        a aVar = a.this;
                        Deprecated_JSUtil.execCallback(aVar.f6716a, aVar.b, json, JSUtil.ERROR, true, false);
                    }
                }

                /* JADX INFO: renamed from: io.dcloud.js.camera.CameraFeatureImpl$a$a$a$b */
                public class b implements Runnable {
                    public b() {
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        String json = DOMException.toJSON(-5, DOMException.MSG_IO_ERROR);
                        a aVar = a.this;
                        Deprecated_JSUtil.execCallback(aVar.f6716a, aVar.b, json, JSUtil.ERROR, true, false);
                    }
                }

                /* JADX INFO: renamed from: io.dcloud.js.camera.CameraFeatureImpl$a$a$a$c */
                public class c implements ISysEventListener {

                    /* JADX INFO: renamed from: a, reason: collision with root package name */
                    public final /* synthetic */ String f6722a;

                    /* JADX INFO: renamed from: io.dcloud.js.camera.CameraFeatureImpl$a$a$a$c$a, reason: collision with other inner class name */
                    public class RunnableC0192a implements Runnable {

                        /* JADX INFO: renamed from: io.dcloud.js.camera.CameraFeatureImpl$a$a$a$c$a$a, reason: collision with other inner class name */
                        public class RunnableC0193a implements Runnable {
                            public RunnableC0193a() {
                            }

                            @Override // java.lang.Runnable
                            public void run() {
                                c cVar = c.this;
                                a aVar = a.this;
                                Deprecated_JSUtil.execCallback(aVar.f6716a, aVar.b, cVar.f6722a, JSUtil.OK, false, false);
                            }
                        }

                        public RunnableC0192a() {
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            a.this.f6717e.getActivity().runOnUiThread(new RunnableC0193a());
                        }
                    }

                    public c(String str) {
                        this.f6722a = str;
                    }

                    @Override // io.dcloud.common.DHInterface.ISysEventListener
                    public boolean onExecute(ISysEventListener.SysEventType sysEventType, Object obj) {
                        Object[] objArr = (Object[]) obj;
                        int iIntValue = ((Integer) objArr[0]).intValue();
                        int iIntValue2 = ((Integer) objArr[1]).intValue();
                        if (sysEventType == ISysEventListener.SysEventType.onActivityResult && iIntValue == io.dcloud.js.camera.a.g) {
                            if (iIntValue2 == -1) {
                                ThreadPool.self().addThreadTask(new RunnableC0192a());
                            } else {
                                String json = DOMException.toJSON(11, "resultCode is wrong");
                                a aVar = a.this;
                                Deprecated_JSUtil.execCallback(aVar.f6716a, aVar.b, json, JSUtil.ERROR, true, false);
                            }
                            a.this.f6717e.unregisterSysEventListener(this, sysEventType);
                        }
                        return false;
                    }
                }

                public RunnableC0190a() {
                }

                public static /* synthetic */ void a(a.C0195a c0195a, String str, IApp iApp, final IWebview iWebview, final String str2, final String str3) {
                    if (c0195a.f6733e) {
                        CompressUtil.compressImage(str, str, false);
                    }
                    iApp.getActivity().runOnUiThread(new Runnable() { // from class: supwisdom.eq1
                        @Override // java.lang.Runnable
                        public final void run() {
                            Deprecated_JSUtil.execCallback(iWebview, str2, str3, JSUtil.OK, false, false);
                        }
                    });
                }

                @Override // java.lang.Runnable
                public void run() {
                    String strConvert2RelPath;
                    String strA;
                    DCFileUriData dCFileUriData = C0189a.this.f6718a;
                    if (dCFileUriData.isReplace && DHFile.copyFile(dCFileUriData.fileReplacePath, dCFileUriData.filePath, true, false) != 1) {
                        a.this.f6717e.getActivity().runOnUiThread(new RunnableC0191a());
                        return;
                    }
                    C0189a c0189a = C0189a.this;
                    a aVar = a.this;
                    if (aVar.d.b) {
                        strA = io.dcloud.js.camera.b.a(c0189a.f6718a.filePath);
                        if (TextUtils.isEmpty(strA)) {
                            a.this.f6717e.getActivity().runOnUiThread(new b());
                            return;
                        }
                        strConvert2RelPath = a.this.f6717e.convert2RelPath(strA);
                    } else {
                        strConvert2RelPath = aVar.f6717e.convert2RelPath(c0189a.f6718a.filePath);
                        strA = C0189a.this.f6718a.filePath;
                    }
                    final String str = strA;
                    final String str2 = strConvert2RelPath;
                    JSONObject jSONObject = a.this.d.d;
                    if (jSONObject == null || !jSONObject.has("width") || !a.this.d.d.has("height")) {
                        C0189a.this.f6718a.clear();
                        ThreadPool threadPoolSelf = ThreadPool.self();
                        a aVar2 = a.this;
                        final a.C0195a c0195a = aVar2.d;
                        final IApp iApp = aVar2.f6717e;
                        final IWebview iWebview = aVar2.f6716a;
                        final String str3 = aVar2.b;
                        threadPoolSelf.addThreadTask(new Runnable() { // from class: supwisdom.fq1
                            @Override // java.lang.Runnable
                            public final void run() {
                                CameraFeatureImpl.a.C0189a.RunnableC0190a.a(c0195a, str, iApp, iWebview, str3, str2);
                            }
                        });
                        return;
                    }
                    Media media = new Media(C0189a.this.f6718a.filePath, "", System.currentTimeMillis(), 1, 1L, -1001, new File(C0189a.this.f6718a.filePath).getParent());
                    Intent intent = new Intent(a.this.f6717e.getActivity(), (Class<?>) IMGEditActivity.class);
                    intent.putExtra("IMAGE_URI", Uri.parse("file://" + media.path));
                    intent.putExtra("IMAGE_MEDIA_ID", media.id);
                    intent.putExtra("IMAGE_INDEX", 0);
                    intent.putExtra("IMAGE_CROP", a.this.d.d.toString());
                    intent.putExtra("IMAGE_SAVE_PATH", media.path);
                    a.this.f6717e.registerSysEventListener(new c(str2), ISysEventListener.SysEventType.onActivityResult);
                    C0189a.this.f6718a.clear();
                    a.this.f6717e.getActivity().startActivityForResult(intent, io.dcloud.js.camera.a.g);
                    a.this.f6717e.getActivity().overridePendingTransition(0, 0);
                }
            }
        }
    }

    public class b extends PermissionUtil.StreamPermissionRequest {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String[] f6725a;
        public final /* synthetic */ IApp b;
        public final /* synthetic */ IWebview c;
        public final /* synthetic */ String d;

        public class a implements ISysEventListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ String f6727a;
            public final /* synthetic */ a.C0195a b;
            public final /* synthetic */ String c;

            /* JADX INFO: renamed from: io.dcloud.js.camera.CameraFeatureImpl$b$a$a, reason: collision with other inner class name */
            public class C0194a implements MediaTranscoder.Listener {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                public final /* synthetic */ Dialog f6728a;

                public C0194a(Dialog dialog) {
                    this.f6728a = dialog;
                }

                @Override // androidtranscoder.MediaTranscoder.Listener
                public void onTranscodeCanceled() {
                    this.f6728a.dismiss();
                    a aVar = a.this;
                    CameraFeatureImpl.this.a(aVar.b.f6733e, aVar.f6727a);
                    String json = DOMException.toJSON(-2, DOMException.MSG_USER_CANCEL);
                    b bVar = b.this;
                    Deprecated_JSUtil.execCallback(bVar.c, bVar.d, json, JSUtil.ERROR, true, false);
                }

                @Override // androidtranscoder.MediaTranscoder.Listener
                public void onTranscodeCompleted() {
                    this.f6728a.dismiss();
                    a aVar = a.this;
                    CameraFeatureImpl.this.a(aVar.b.f6733e, aVar.f6727a);
                    a aVar2 = a.this;
                    String strConvert2RelPath = b.this.b.convert2RelPath(aVar2.c);
                    b bVar = b.this;
                    Deprecated_JSUtil.execCallback(bVar.c, bVar.d, strConvert2RelPath, JSUtil.OK, false, false);
                }

                @Override // androidtranscoder.MediaTranscoder.Listener
                public void onTranscodeFailed(Exception exc) {
                    this.f6728a.dismiss();
                    a aVar = a.this;
                    CameraFeatureImpl.this.a(aVar.b.f6733e, aVar.f6727a);
                    String json = DOMException.toJSON(-99, exc.getMessage());
                    b bVar = b.this;
                    Deprecated_JSUtil.execCallback(bVar.c, bVar.d, json, JSUtil.ERROR, true, false);
                }

                @Override // androidtranscoder.MediaTranscoder.Listener
                public void onTranscodeProgress(double d) {
                }
            }

            public a(String str, a.C0195a c0195a, String str2) {
                this.f6727a = str;
                this.b = c0195a;
                this.c = str2;
            }

            @Override // io.dcloud.common.DHInterface.ISysEventListener
            public boolean onExecute(ISysEventListener.SysEventType sysEventType, Object obj) {
                Object[] objArr = (Object[]) obj;
                int iIntValue = ((Integer) objArr[0]).intValue();
                int iIntValue2 = ((Integer) objArr[1]).intValue();
                if (sysEventType == ISysEventListener.SysEventType.onActivityResult && iIntValue == io.dcloud.js.camera.a.f) {
                    if (iIntValue2 != -1) {
                        b bVar = b.this;
                        Deprecated_JSUtil.execCallback(bVar.c, bVar.d, null, JSUtil.ERROR, false, false);
                    } else {
                        if (!new File(this.f6727a).exists() && DHFile.copyFile(ContentUriUtil.getImageAbsolutePath(b.this.b.getActivity(), ((Intent) objArr[2]).getData()), this.f6727a) != 1) {
                            b bVar2 = b.this;
                            Deprecated_JSUtil.execCallback(bVar2.c, bVar2.d, null, JSUtil.ERROR, false, false);
                            b.this.b.unregisterSysEventListener(this, sysEventType);
                            return false;
                        }
                        Dialog dialogA = null;
                        try {
                            if (this.b.f6733e) {
                                dialogA = io.dcloud.js.camera.b.a(b.this.c.getContext());
                                dialogA.show();
                                MediaTranscoder.getInstance().transcodeVideo(this.f6727a, this.c, MediaFormatStrategyPresets.createAndroid720pStrategy(2, 1.0d), new C0194a(dialogA));
                            } else {
                                String strConvert2RelPath = b.this.b.convert2RelPath(this.c);
                                b bVar3 = b.this;
                                Deprecated_JSUtil.execCallback(bVar3.c, bVar3.d, strConvert2RelPath, JSUtil.OK, false, false);
                            }
                        } catch (IOException e2) {
                            if (dialogA != null) {
                                dialogA.dismiss();
                            }
                            CameraFeatureImpl.this.a(this.b.f6733e, this.f6727a);
                            String json = DOMException.toJSON(-99, e2.getMessage());
                            b bVar4 = b.this;
                            Deprecated_JSUtil.execCallback(bVar4.c, bVar4.d, json, JSUtil.ERROR, true, false);
                        }
                    }
                    b.this.b.unregisterSysEventListener(this, sysEventType);
                }
                return false;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(IApp iApp, String[] strArr, IApp iApp2, IWebview iWebview, String str) {
            super(iApp);
            this.f6725a = strArr;
            this.b = iApp2;
            this.c = iWebview;
            this.d = str;
        }

        @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
        public void onDenied(String str) {
            Deprecated_JSUtil.execCallback(this.c, this.d, DOMException.toJSON(11, DOMException.MSG_NO_PERMISSION), JSUtil.ERROR, true, false);
        }

        @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
        public void onGranted(String str) {
            String str2;
            try {
                a.C0195a c0195aA = io.dcloud.js.camera.a.a(this.f6725a[1], false);
                String strConvert2AbsFullPath = this.b.convert2AbsFullPath(this.c.obtainFullUrl(), c0195aA.a());
                if (JSUtil.checkOperateDirErrorAndCallback(this.c, this.d, strConvert2AbsFullPath)) {
                    Deprecated_JSUtil.execCallback(this.c, this.d, DOMException.toJSON(-5, DOMException.MSG_IO_ERROR), JSUtil.ERROR, true, false);
                    return;
                }
                if (c0195aA.f6733e) {
                    str2 = strConvert2AbsFullPath + ".temp";
                } else {
                    str2 = strConvert2AbsFullPath;
                }
                File file = new File(str2);
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                this.b.registerSysEventListener(new a(str2, c0195aA, strConvert2AbsFullPath), ISysEventListener.SysEventType.onActivityResult);
                Intent intent = new Intent("android.media.action.VIDEO_CAPTURE");
                if (c0195aA.b() != 0) {
                    intent.putExtra("android.intent.extra.durationLimit", c0195aA.b());
                }
                if (Build.VERSION.SDK_INT >= 29 && file.exists()) {
                    intent.putExtra("output", FileProvider.getUriForFile(this.c.getContext(), this.c.getContext().getPackageName() + ".dc.fileprovider", file));
                }
                this.c.getActivity().startActivityForResult(intent, io.dcloud.js.camera.a.f);
            } catch (Exception e2) {
                Deprecated_JSUtil.execCallback(this.c, this.d, DOMException.toJSON(11, e2.getMessage()), JSUtil.ERROR, true, false);
            }
        }
    }

    public class c extends PermissionUtil.StreamPermissionRequest {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ io.dcloud.js.camera.a f6729a;
        public final /* synthetic */ String[] b;
        public final /* synthetic */ IWebview c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(CameraFeatureImpl cameraFeatureImpl, IApp iApp, io.dcloud.js.camera.a aVar, String[] strArr, IWebview iWebview) {
            super(iApp);
            this.f6729a = aVar;
            this.b = strArr;
            this.c = iWebview;
        }

        @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
        public void onDenied(String str) {
        }

        @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
        public void onGranted(String str) {
            this.f6729a.b();
            String[] strArr = this.b;
            if (strArr.length >= 3) {
                Deprecated_JSUtil.execCallback(this.c, strArr[2], this.f6729a.a(), JSUtil.OK, true, false);
            }
        }
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void dispose(String str) {
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public String execute(IWebview iWebview, String str, String[] strArr) {
        IApp iAppObtainApp = iWebview.obtainFrameView().obtainApp();
        String str2 = strArr[0];
        if (Build.VERSION.SDK_INT >= 24) {
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().build());
        }
        if (str.equals("captureImage")) {
            a.C0195a c0195aA = io.dcloud.js.camera.a.a(strArr[1], true);
            String strConvert2AbsFullPath = iAppObtainApp.convert2AbsFullPath(iWebview.obtainFullUrl(), c0195aA.a());
            if (!FileUtil.checkPrivatePath(iWebview.getContext(), strConvert2AbsFullPath) && FileUtil.getPathForPublicType(strConvert2AbsFullPath) == null) {
                Deprecated_JSUtil.execCallback(iWebview, str2, DOMException.toJSON(-5, DOMException.MSG_PATH_NOT_PRIVATE_ERROR), JSUtil.ERROR, true, false);
                return null;
            }
            PermissionUtil.usePermission(iAppObtainApp.getActivity(), PermissionUtil.PMS_CAMERA, new a(this, iAppObtainApp, iWebview, str2, strConvert2AbsFullPath, c0195aA, iAppObtainApp));
        } else if (str.equals("startVideoCapture")) {
            PermissionUtil.usePermission(iAppObtainApp.getActivity(), PermissionUtil.PMS_CAMERA, new b(iAppObtainApp, strArr, iAppObtainApp, iWebview, str2));
        } else if (str.equals("getCamera")) {
            io.dcloud.js.camera.a aVar = new io.dcloud.js.camera.a(PdrUtil.parseInt(strArr[1], 1));
            if (PermissionUtil.checkSelfPermission(iAppObtainApp.getActivity(), "android.permission.CAMERA") == 0) {
                aVar.b();
                return aVar.a();
            }
            PermissionUtil.usePermission(iAppObtainApp.getActivity(), PermissionUtil.PMS_CAMERA, new c(this, iAppObtainApp, aVar, strArr, iWebview));
        }
        return null;
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void init(AbsMgr absMgr, String str) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z, String str) {
        if (z) {
            try {
                if (str.endsWith(".temp")) {
                    new File(str).delete();
                }
            } catch (Exception unused) {
            }
        }
    }
}
