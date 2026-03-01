package io.dcloud.feature.pdr;

import androidtranscoder.VideoCompressor;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IFeature;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.FileUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.common.util.ZipUtils;
import java.io.File;
import java.util.HashMap;

/* JADX INFO: loaded from: classes3.dex */
public class ZipFeature implements IFeature {

    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f6578a = false;
        public String b = null;
        public String c;
        public String d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f6579e;
        public IWebview f;

        public a(ZipFeature zipFeature) {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (this.f6578a) {
                    Logger.d("compress mUnZipDirPath=" + this.d + ";mZipFilePath" + this.c);
                    File file = new File(this.d);
                    if (!file.exists()) {
                        Deprecated_JSUtil.excCallbackError(this.f, this.f6579e, StringUtil.format(DOMException.JSON_ERROR_INFO, 2, this.b + " open failed:ENOENT (No such file or directory)"), true);
                    }
                    if (JSUtil.checkOperateDirErrorAndCallback(this.f, this.f6579e, this.c)) {
                        return;
                    }
                    File file2 = new File(this.c);
                    File[] fileArrListFiles = file.isDirectory() ? file2.listFiles() : null;
                    if (file.isFile() || fileArrListFiles == null) {
                        fileArrListFiles = new File[]{file};
                    }
                    if (!FileUtil.checkPathAccord(this.f.getContext(), file.getPath(), file2.getPath())) {
                        Deprecated_JSUtil.excCallbackError(this.f, this.f6579e, StringUtil.format(DOMException.JSON_ERROR_INFO, 2, DOMException.MSG_PATH_NOT_PRIVATE_ERROR), true);
                        return;
                    }
                    ZipUtils.zipFiles(fileArrListFiles, file2);
                } else {
                    Logger.d("decompress mUnZipDirPath=" + this.d + ";mZipFilePath" + this.c);
                    if (JSUtil.checkOperateDirErrorAndCallback(this.f, this.f6579e, this.d)) {
                        return;
                    }
                    File file3 = new File(this.c);
                    if (!file3.exists()) {
                        Deprecated_JSUtil.excCallbackError(this.f, this.f6579e, StringUtil.format(DOMException.JSON_ERROR_INFO, 2, this.b + " open failed:ENOENT (No such file or directory)"), true);
                        return;
                    }
                    if (!FileUtil.checkPathAccord(this.f.getContext(), file3.getPath(), this.d)) {
                        Deprecated_JSUtil.excCallbackError(this.f, this.f6579e, StringUtil.format(DOMException.JSON_ERROR_INFO, 2, DOMException.MSG_PATH_NOT_PRIVATE_ERROR), true);
                        return;
                    }
                    ZipUtils.upZipFile(file3, this.d);
                }
                Deprecated_JSUtil.excCallbackSuccess(this.f, this.f6579e, "");
            } catch (Exception e2) {
                Deprecated_JSUtil.excCallbackError(this.f, this.f6579e, StringUtil.format(DOMException.JSON_ERROR_INFO, 2, e2.getMessage()), true);
            }
        }
    }

    private void a(a aVar) {
        new Thread(aVar).start();
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void dispose(String str) {
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public String execute(IWebview iWebview, String str, String[] strArr) {
        if (!PdrUtil.isEquals(str, "compress")) {
            if (!PdrUtil.isEquals(str, "decompress")) {
                if (PdrUtil.isEquals(str, "compressImage")) {
                    io.dcloud.feature.pdr.a.b(iWebview, strArr);
                    return null;
                }
                if (!PdrUtil.isEquals(str, "compressVideo")) {
                    return null;
                }
                VideoCompressor.getInstance().compressVideo(iWebview, strArr);
                return null;
            }
            a aVar = new a(this);
            aVar.f = iWebview;
            aVar.f6578a = false;
            IApp iAppObtainApp = iWebview.obtainFrameView().obtainApp();
            aVar.c = iAppObtainApp.convert2AbsFullPath(iWebview.obtainFullUrl(), iAppObtainApp.checkPrivateDirAndCopy2Temp(strArr[0]));
            aVar.d = iAppObtainApp.convert2AbsFullPath(iWebview.obtainFullUrl(), strArr[1]);
            aVar.f6579e = strArr[2];
            a(aVar);
            return null;
        }
        a aVar2 = new a(this);
        aVar2.f = iWebview;
        aVar2.f6578a = true;
        IApp iAppObtainApp2 = iWebview.obtainFrameView().obtainApp();
        aVar2.b = strArr[0];
        aVar2.d = iAppObtainApp2.convert2AbsFullPath(iWebview.obtainFullUrl(), strArr[0]);
        String str2 = strArr[1];
        if (str2 == null) {
            str2 = AbsoluteConst.MINI_SERVER_APP_DOC + System.currentTimeMillis();
        }
        if (!str2.endsWith(".zip")) {
            str2 = str2 + ".zip";
        }
        aVar2.c = iAppObtainApp2.convert2AbsFullPath(iWebview.obtainFullUrl(), str2);
        aVar2.f6579e = strArr[2];
        a(aVar2);
        return null;
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void init(AbsMgr absMgr, String str) {
        new HashMap(1);
    }
}
