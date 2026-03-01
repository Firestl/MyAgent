package io.dcloud.feature.weex.adapter;

import android.text.TextUtils;
import com.taobao.weex.adapter.IWXJSExceptionAdapter;
import com.taobao.weex.common.WXJSExceptionInfo;
import io.dcloud.common.util.AppConsoleLogUtil;
import io.dcloud.common.util.BaseInfo;

/* JADX INFO: loaded from: classes3.dex */
public class JSExceptionAdapter implements IWXJSExceptionAdapter {
    @Override // com.taobao.weex.adapter.IWXJSExceptionAdapter
    public void onJSException(WXJSExceptionInfo wXJSExceptionInfo) {
        if (wXJSExceptionInfo == null || TextUtils.isEmpty(BaseInfo.sCurrentAppOriginalAppid) || !BaseInfo.sCurrentAppOriginalAppid.startsWith("__UNI__")) {
            return;
        }
        String strReplace = "reportJSException >>>> exception function:" + wXJSExceptionInfo.getFunction() + ", exception:" + wXJSExceptionInfo.getException();
        if (strReplace.endsWith("__ERROR")) {
            strReplace = strReplace.replace("__ERROR", "");
        }
        AppConsoleLogUtil.DCLog(strReplace, "ERROR");
    }
}
