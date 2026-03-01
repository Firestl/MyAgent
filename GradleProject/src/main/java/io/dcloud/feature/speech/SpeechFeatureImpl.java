package io.dcloud.feature.speech;

import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IFeature;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.util.PermissionUtil;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.StringUtil;
import java.util.HashMap;

/* JADX INFO: loaded from: classes3.dex */
public class SpeechFeatureImpl implements IFeature {
    public AbsSpeechEngine mSpeechEngine = null;
    public HashMap<String, String> mSpeechMap = null;
    public SpeechManager manager;

    @Override // io.dcloud.common.DHInterface.IFeature
    public void dispose(String str) {
        this.manager.stopRecognize(false);
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public String execute(final IWebview iWebview, final String str, final String[] strArr) {
        PermissionUtil.usePermission(iWebview.getActivity(), PermissionUtil.PMS_RECORD, new PermissionUtil.StreamPermissionRequest(iWebview.obtainApp()) { // from class: io.dcloud.feature.speech.SpeechFeatureImpl.1
            @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
            public void onDenied(String str2) {
                if ("startRecognize".equals(str)) {
                    Deprecated_JSUtil.execCallback(iWebview, strArr[0], StringUtil.format(DOMException.JSON_ERROR_INFO, 3, DOMException.MSG_NO_PERMISSION), JSUtil.ERROR, true, false);
                }
            }

            @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
            public void onGranted(String str2) {
                SpeechFeatureImpl.this.manager.execute(iWebview, str, strArr, SpeechFeatureImpl.this.mSpeechMap);
            }
        });
        return null;
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void init(AbsMgr absMgr, String str) {
        this.mSpeechMap = (HashMap) absMgr.processEvent(IMgr.MgrType.FeatureMgr, 4, str);
        this.manager = SpeechManager.getInstance();
    }
}
